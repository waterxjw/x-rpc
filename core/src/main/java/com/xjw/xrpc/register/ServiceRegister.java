package com.xjw.xrpc.register;

import com.xjw.xrpc.communication.Url;

/**
 * 服务的注册与发现接口
 * （这块不太熟，随便定个接口吧，大概以后还要改……）
 */
public interface ServiceRegister {

    void register(String serviceName, Url url);

    void unregister(String serviceName, Url url);

    Url getServiceUrl(String serviceName);
}
