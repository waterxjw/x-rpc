package com.xjw.xrpc.api;

import com.xjw.xrpc.communication.bolt.BoltServer;
import com.xjw.xrpc.communication.netty.NettyServer;
import com.xjw.xrpc.core.ProviderManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XProvider {
    private static final Logger LOGGER=LoggerFactory.getLogger(XProvider.class);
    private XProviderConfig config;

    public XProvider(XProviderConfig config){
        this.config=config;
    }

    public XProviderConfig getConfig() {
        return config;
    }

    public void publish(){
        ProviderManager.putProvider(config.getServiceName(),this);

    }
    public static void start(){
        NettyServer.getInstance();
    }
}
