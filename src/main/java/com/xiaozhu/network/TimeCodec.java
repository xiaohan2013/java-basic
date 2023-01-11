package com.xiaozhu.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;

import java.util.List;

public class TimeCodec extends ByteToMessageCodec {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List list) throws Exception {
        if(byteBuf.readableBytes() < 4) {
            return;
        }

        list.add(byteBuf.readBytes(4));
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt((int)((UnixTime)o).value());
    }
}
