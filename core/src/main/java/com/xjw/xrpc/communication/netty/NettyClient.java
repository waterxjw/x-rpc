package com.xjw.xrpc.communication.netty;

import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import com.xjw.xrpc.communication.XClient;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyClient extends SimpleChannelInboundHandler<Response> implements XClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(NettyClient.class);
    private Response response;
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) throws Exception {
        this.response=response;
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("api caught exception", cause);
        ctx.close();
    }
    public Response invoke(Request request) {
        EventLoopGroup group=new NioEventLoopGroup();
        try{
            Bootstrap bootstrap=new Bootstrap();
            bootstrap.group(group);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline=socketChannel.pipeline();
                    pipeline.addLast(new NettyEncoder(Request.class));
                    pipeline.addLast(new NettyDecoder(Response.class));
                    pipeline.addLast(NettyClient.this);
                }
            });
            bootstrap.option(ChannelOption.TCP_NODELAY,true);
            ChannelFuture future=bootstrap.connect(request.getIp(),request.getPort()).sync();
            Channel channel=future.channel();
            channel.writeAndFlush(request).sync();
            channel.closeFuture().sync();
            return response;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
        return null;
    }
}
