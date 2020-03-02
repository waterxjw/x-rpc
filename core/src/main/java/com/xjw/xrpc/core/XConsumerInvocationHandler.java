package com.xjw.xrpc.core;

import com.xjw.xrpc.api.XConsumerConfig;
import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.communication.Url;
import com.xjw.xrpc.filter.Filter;
import com.xjw.xrpc.filter.FilterChain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XConsumerInvocationHandler implements InvocationHandler {
    //被包装好的invoker。原始的invoker可以被多个filter串起来包装，以此灵活的进行扩展
    private Invoker wrappedInvoker;
    //原始invoker，负责请求的最终发送
    private XConsumerInvoker originInvoker;


    public XConsumerInvocationHandler(XConsumerInvoker xConsumerInvoker){
        this.originInvoker=xConsumerInvoker;
        List<Filter> filters=new ArrayList<Filter>();
        //在这里加入消费者端应加入的filter
        //filters.add()
        FilterChain filterChain=new FilterChain(originInvoker,filters);
        this.wrappedInvoker=filterChain.getWrappedInvoker();
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request=buildRequest(method,args);
        //由包装好的invoker执行
        Response response=wrappedInvoker.invoke(request);
        if(response.hasException()){
            throw response.getException();
        }else{
            return response.getResult();
        }
    }

    private Request buildRequest(Method method,Object[] args){
        Request request=new Request();
        request.setServiceName(originInvoker.getConfig().getServiceName());
        request.setMethodName(method.getName());
        request.setArgs(args);
        request.setArgTypes(method.getParameterTypes());
        Url url=((XConsumerConfig)originInvoker.getConfig()).getServiceUrl();
        request.setIp(url.getIp());
        request.setPort(url.getPort());
        return request;
    }
}
