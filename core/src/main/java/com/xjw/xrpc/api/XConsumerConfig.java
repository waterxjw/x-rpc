package com.xjw.xrpc.api;

import com.xjw.xrpc.communication.Url;
import com.xjw.xrpc.core.ServiceConfig;

public class XConsumerConfig extends ServiceConfig {

    public Url getServiceUrl(){
        //return serviceRegister.getServiceUrl(getServiceName());
        Url url=new Url();
        return url;
    }
}
