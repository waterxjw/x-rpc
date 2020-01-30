package com.xjw.xrpc.api;

import com.xjw.xrpc.core.ServiceConfig;
import com.xjw.xrpc.core.XProviderInvocationHandller;
import com.xjw.xrpc.core.XProviderInvoker;

import java.lang.reflect.Proxy;

public class XProviderConfig extends ServiceConfig {
    //服务接口的实现
    private Object impl;


    //在接口实现基础上扩展功能得到的代理
    private Object proxy;

    public void setImpl(Object impl) {
        this.impl = impl;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(impl.getClass().getClassLoader(),new Class[]{getService()},new XProviderInvocationHandller(new XProviderInvoker(impl,this)));
    }

}
