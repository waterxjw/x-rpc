package com.xjw.xrpc.communication.netty;

import com.xjw.xrpc.util.SerializationUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class NettyDecoder extends ByteToMessageDecoder {
    private Class<?> genericClass;

    public NettyDecoder(Class<?> genericClass) {
        this.genericClass = genericClass;
    }
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        byteBuf.markReaderIndex();
        //约定前四个字节为数据长度
        int dataLength = byteBuf.readInt();
        if (byteBuf.readableBytes() < dataLength) {
            //不符合约定
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] data = new byte[dataLength];
        byteBuf.readBytes(data);
        list.add(SerializationUtil.deserialize(data, genericClass));
    }
}
