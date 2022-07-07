package com.fushin.easyrpc.transport.netty;

import com.fushin.easyrpc.transport.Request;
import com.fushin.easyrpc.transport.Response;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 丁成文
 * @date 2022/7/7
 */
@ChannelHandler.Sharable
@Slf4j
public class NettyServerHandler extends ChannelDuplexHandler {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("服务端建立连接");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("服务端接受到消息>>>{}", msg);
        Request req = (Request) msg;
        String id = req.getId();
        Response resp = new Response();
        resp.setId(id);
        resp.setData("hello, apply");
        ctx.writeAndFlush(resp);
//        super.channelRead(ctx, msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("服务端发送数据");
        super.write(ctx, msg, promise);
    }
}
