package com.xjw.xrpc.core;

import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;

public interface Invoker {

    public Response invoke(Request request) throws Throwable;

    public ServiceConfig getConfig();
}
