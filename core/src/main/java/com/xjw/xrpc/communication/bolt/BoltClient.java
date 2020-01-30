package com.xjw.xrpc.communication.bolt;

import com.alipay.remoting.exception.RemotingException;
import com.alipay.remoting.rpc.RpcClient;
import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.communication.XClient;

public class BoltClient implements XClient {
    //只维持一个RpcClient
    private final static RpcClient client=new RpcClient();
    static {
        client.init();
    }
    public Response invoke(Request request) {
        try{
            return (Response)(client.invokeSync(formatUrl(request.getIp(),request.getPort()),request,2000));
        }catch (RemotingException e){
            e.printStackTrace();
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        return null;
    }
    private String formatUrl(String ip,int port){
        return ip+":"+port;
    }
}
