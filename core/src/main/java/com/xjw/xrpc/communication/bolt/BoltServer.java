package com.xjw.xrpc.communication.bolt;

import com.alipay.remoting.rpc.RpcServer;
import com.xjw.xrpc.communication.XServer;

public class BoltServer implements XServer {
    private static RpcServer rpcServer;

    static {
        rpcServer=new RpcServer("0.0.0.0",8081,false);
        rpcServer.registerUserProcessor(new BoltServerProcessor());
        rpcServer.start();
    }
}
