package com.xjw.xrpc.communication.netty;

import com.xjw.xrpc.communication.Request;
import com.xjw.xrpc.communication.Response;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer {
    private static final Logger LOGGER=LoggerFactory.getLogger(NettyServer.class);
    private NettyServer(){
        init();
    }
    private static NettyServer nettyServer=null;
    public static NettyServer getInstance(){
        if(nettyServer==null){
            synchronized (NettyServer.class){
                if(nettyServer==null){
                    nettyServer=new NettyServer();
                }
            }
        }
        return nettyServer;
    }
    private void init(){
        EventLoopGroup bossGroup=new NioEventLoopGroup();
        EventLoopGroup workerGroup=new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline=socketChannel.pipeline();
                    pipeline.addLast(new NettyDecoder(Request.class));
                    pipeline.addLast(new NettyEncoder(Response.class));
                    pipeline.addLast(new NettyServerHandler());
                }
            });
            bootstrap.option(ChannelOption.SO_BACKLOG, 1024);
            bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture future=bootstrap.bind("0.0.0.0",8082).sync();
            future.channel().closeFuture().sync();
        }catch (Exception e){
            LOGGER.error(e.toString());
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
