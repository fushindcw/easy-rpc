package com.fushin.easyrpc.transport.netty;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageCodec;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
public class Codec {

    public ChannelHandler encoder(){
        return new Encoder();
    }

    public ChannelHandler decoder(){
        return new Decoder();
    }

    private static class Encoder extends MessageToByteEncoder {

        @Override
        protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Hessian2Output output = new Hessian2Output(baos);
            output.writeObject(msg);
            output.flush();
            byte[] bytes = baos.toByteArray();
            out.writeBytes(bytes);
            output.close();
            baos.close();
        }
    }

    private static class Decoder extends  ByteToMessageDecoder{

        @Override
        protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
            int length = in.readableBytes();
            ByteBuf byteBuf = in.readBytes(length);
            byte[] bytes = new byte[length];
            byteBuf.readBytes(bytes);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            Hessian2Input input = new Hessian2Input(bais);
            Object obj = input.readObject();
            out.add(obj);
            input.close();
            bais.close();
        }
    }
}
