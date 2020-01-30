package com.xjw.xrpc.communication;

/**
 * 通信层 客户端 的接口
 */
public interface XClient {

    public Response invoke(Request request);
}
