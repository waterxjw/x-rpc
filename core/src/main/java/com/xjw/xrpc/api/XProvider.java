package com.xjw.xrpc.api;

import com.xjw.xrpc.communication.bolt.BoltServer;
import com.xjw.xrpc.core.ProviderManager;

public class XProvider {



    private XProviderConfig config;

    public XProvider(XProviderConfig config){
        this.config=config;
    }

    public XProviderConfig getConfig() {
        return config;
    }

    public void publish(){
        ProviderManager.putProvider(config.getServiceName(),this);
        new BoltServer();
    }
}
