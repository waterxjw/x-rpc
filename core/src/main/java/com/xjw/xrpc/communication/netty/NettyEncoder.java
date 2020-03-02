package com.xjw.xrpc.communication.netty;

import com.xjw.xrpc.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class NettyEncoder extends MessageToByteEncoder {

    private Class<?> genericClass;

    public NettyEncoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        byte[] data = SerializationUtil.serialize(o);
        byteBuf.writeInt(data.length);
        byteBuf.writeBytes(data);
    }


}
