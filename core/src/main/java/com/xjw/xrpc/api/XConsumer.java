package com.xjw.xrpc.api;

import com.xjw.xrpc.core.XConsumerInvocationHandler;
import com.xjw.xrpc.core.XConsumerInvoker;

import java.lang.reflect.Proxy;

/**
 * 消费者API
 * 配合config使用，具体设置分离到config
 * @param <T>
 */
public class XConsumer<T> {

    private XConsumerConfig config;

    private T proxy;

    public XConsumer(XConsumerConfig config){
        this.config=config;
    }

    public T getProxy(){
        if (proxy!=null)return proxy;
        proxy=(T)Proxy.newProxyInstance(config.getService().getClassLoader(),new Class[]{config.getService()},new XConsumerInvocationHandler(new XConsumerInvoker(config)));
        return proxy;
    }
}
