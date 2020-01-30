package com.xjw.xrpc.core;

import com.xjw.xrpc.api.XConsumerConfig;
import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Url;
import com.xjw.xrpc.filter.Filter;
import com.xjw.xrpc.filter.FilterChain;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XProviderInvocationHandller implements InvocationHandler {

    private Invoker wrappedInvoker;

    private XProviderInvoker originInvoker;

    public XProviderInvocationHandller(XProviderInvoker invoker){
        originInvoker=invoker;
        List<Filter> filters=new ArrayList<Filter>();
        //在这里加入生产者端应加入的filter
        //filters.add()
        FilterChain filterChain=new FilterChain(originInvoker,filters);
        this.wrappedInvoker=filterChain.getWrappedInvoker();
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request=buildRequest(method,args);
        //由包装好的invoker执行
        return wrappedInvoker.invoke(request).getResult();
    }

    private Request buildRequest(Method method,Object[] args){
        //有点蠢……在服务端先把request解开，在这里又包成request。不过为了复用invoker filter chain 那一套接口，只好这样了
        Request request=new Request();
        request.setMethod(method);
        request.setArgs(args);
        return request;
    }
}
