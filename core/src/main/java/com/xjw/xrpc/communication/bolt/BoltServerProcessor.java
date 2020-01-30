package com.xjw.xrpc.communication.bolt;

import com.alipay.remoting.AsyncContext;
import com.alipay.remoting.BizContext;
import com.alipay.remoting.rpc.protocol.AbstractUserProcessor;
import com.xjw.xrpc.api.XProvider;
import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.core.ProviderManager;

public class BoltServerProcessor extends AbstractUserProcessor<Request> {
    public void handleRequest(BizContext bizContext, AsyncContext asyncContext, Request request) {

    }

    public Object handleRequest(BizContext bizContext, Request request) throws Exception {
        XProvider xProvider=ProviderManager.getProvider(request.getServiceName());
        //实际上没有传递Method,而是根据方法名和参数类型确定方法
        request.setMethod(xProvider.getConfig().getService().getDeclaredMethod(request.getMethodName(),request.getArgTypes()));

        return new Response(request.getMethod().invoke(xProvider.getConfig().getProxy(),request.getArgs()));
    }

    public String interest() {
        //???
        return Request.class.getName();
    }
}
