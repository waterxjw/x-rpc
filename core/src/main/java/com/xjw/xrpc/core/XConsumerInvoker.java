package com.xjw.xrpc.core;

import com.xjw.xrpc.api.XConsumerConfig;
import com.xjw.xrpc.api.XHook;
import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.communication.XClient;
import com.xjw.xrpc.communication.bolt.BoltClient;
import com.xjw.xrpc.communication.netty.NettyClient;

/**
 * 消费者端真正、最终的invoker
 */
public class XConsumerInvoker implements Invoker{
    private XConsumerConfig xConsumerConfig;


    public XConsumerInvoker(XConsumerConfig xConsumerConfig){
        this.xConsumerConfig=xConsumerConfig;
    }

    public Response invoke(Request request) throws Throwable {
        XClient xClient=new NettyClient();
        XHook hook=xConsumerConfig.getHook();
        if(hook!=null)hook.before(request);
        Response response=xClient.invoke(request);
        if(hook!=null)hook.after(request);
        return response;
    }

    public ServiceConfig getConfig() {
        return xConsumerConfig;
    }
}
