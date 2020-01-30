package com.xjw.xrpc.filter;

import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.core.Invoker;
import com.xjw.xrpc.core.ServiceConfig;

/**
 * 加装filter的invoker，仍然包装成invoker
 */
public class FilterInvoker implements Invoker {

    private Filter filter;

    private Invoker originInvoker;

    public FilterInvoker(Filter filter,Invoker originInvoker){
        this.filter=filter;
        this.originInvoker=originInvoker;
    }

    public Response invoke(Request request) throws Throwable {
        return filter.filter(originInvoker,request);
    }

    public ServiceConfig getConfig() {
        return originInvoker.getConfig();
    }
}
