package com.xjw.xrpc.core;

import com.xjw.xrpc.api.XHook;
import com.xjw.xrpc.register.ServiceRegister;

/**
 * 服务配置类
 * 消费者和生产者都需要服务的相关配置，因此抽出共同部分
 */
public abstract class ServiceConfig {

    //服务的接口
    private Class<?> service;
    //因为消费者和生产者要使用的注册发现器的功能不同，不得不把这玩意暴露给子类
    protected ServiceRegister serviceRegister=null;
    //钩子，用于AOP
    private XHook hook=null;

    public XHook getHook() {
        return hook;
    }

    public void setHook(XHook hook) {
        this.hook = hook;
    }

    public Class<?> getService() {
        return service;
    }

    public void setService(Class<?> service) {
        this.service = service;
    }

    public String getServiceName(){
        //好像不是很必要？毕竟直接都getservice了，不过本着迪米特法则，还是这样写吧
        return service.getName();
    }
}
