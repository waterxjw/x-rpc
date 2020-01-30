package com.xjw.xrpc.api;

import com.xjw.xrpc.communication.Request;

/**
 * 钩子接口
 * 用户可定义自己的钩子，所谓AOP编程
 * 在config中可配置钩子
 */
public interface XHook {

    public void before(Request request);

    public void after(Request request);
}
