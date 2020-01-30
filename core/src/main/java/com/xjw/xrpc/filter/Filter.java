package com.xjw.xrpc.filter;

import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.core.Invoker;

public interface Filter {
    //扩展功能通过实现filter方法，加入逻辑，影响处理流程
    public Response filter(Invoker invoker, Request request);

}
