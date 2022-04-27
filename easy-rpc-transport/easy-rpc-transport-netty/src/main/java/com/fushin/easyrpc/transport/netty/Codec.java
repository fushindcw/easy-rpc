package com.fushin.easyrpc.transport.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
public class Codec {

    public static class Encoder extends MessageToByteEncoder {

        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            out.writeCharSequence(msg.toString(), Charset.forName("UTF-8"));
        }
    }

    public static class Decoder extends  ByteToMessageDecoder{

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            System.out.println(in.toString(Charset.forName("UTF-8")));
        }
    }
}
