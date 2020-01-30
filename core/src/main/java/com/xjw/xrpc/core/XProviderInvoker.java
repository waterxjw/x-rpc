package com.xjw.xrpc.core;

import com.xjw.xrpc.api.XProviderConfig;
import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;

/**
 * 真正的invoker，执行impl的方法得到结果
 */
public class XProviderInvoker implements Invoker{

    private Object impl;

    private XProviderConfig config;

    public XProviderInvoker(Object impl,XProviderConfig config){
        this.impl=impl;
        this.config=config;
    }
    public Response invoke(Request request) throws Throwable {

        return new Response(request.getMethod().invoke(impl,request.getArgs())) ;
    }

    public ServiceConfig getConfig() {
        return config;
    }
}
