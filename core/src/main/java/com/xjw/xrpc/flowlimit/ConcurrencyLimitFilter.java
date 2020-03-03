package com.xjw.xrpc.flowlimit;

import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.core.Invoker;
import com.xjw.xrpc.filter.Filter;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConcurrencyLimitFilter implements Filter {

    private static ConcurrencyLimitFilter instance=null;

    private static final int PERMIT_NUM=10;

    private static final int WAIT_SECONDS=1;

    private ConcurrencyLimitFilter(){

    }
    public static ConcurrencyLimitFilter getInstance(){
        if(instance==null){
            synchronized (ConcurrencyLimitFilter.class){
                if(instance==null){
                    instance=new ConcurrencyLimitFilter();
                }
            }
        }
        return instance;
    }

    private ConcurrentHashMap<String,Semaphore> semaphoreMap=new ConcurrentHashMap<String, Semaphore>();

    public Response filter(Invoker invoker, Request request) throws Throwable{
        Semaphore semaphore=getSemaphore(request.getServiceName());
        if(semaphore.tryAcquire(WAIT_SECONDS, TimeUnit.SECONDS)){
            try{
                return invoker.invoke(request);
            }finally {
                semaphore.release();
            }
        }else{

            throw new Exception("server busy");
        }
    }

    private Semaphore getSemaphore(String serviceName){
        Semaphore semaphore=semaphoreMap.get(serviceName);
        if (semaphore==null){
            semaphore=new Semaphore(PERMIT_NUM,true);
            Semaphore old=semaphoreMap.putIfAbsent(serviceName,semaphore);
            if(old!=null){
                semaphore=old;
            }
        }
        return semaphore;
    }
}
