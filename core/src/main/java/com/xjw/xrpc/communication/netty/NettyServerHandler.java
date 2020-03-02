package com.xjw.xrpc.communication.netty;

import com.xjw.xrpc.api.XProvider;
import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.core.ProviderManager;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class NettyServerHandler extends SimpleChannelInboundHandler<Request> {
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {
        Response response=new Response();
        XProvider provider=ProviderManager.getProvider(request.getServiceName());
        if(provider==null){
            Exception e=new Exception("service not found");
            response.setException(e);
        }else {
            request.setMethod(provider.getConfig().getService().getDeclaredMethod(request.getMethodName(),request.getArgTypes()));
            try{
                Object result=request.getMethod().invoke(provider.getConfig().getProxy(),request.getArgs());
                response.setResult(result);
            }catch (Exception e){
                response.setException(e);
            }
        }
        channelHandlerContext.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
