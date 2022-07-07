package com.fushin.easyrpc.transport.netty;

import com.fushin.easyrpc.transport.Response;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * @author 丁成文
 * @date 2022/7/7
 */
@ChannelHandler.Sharable
@Slf4j
public class NettyClientHandler extends ChannelDuplexHandler {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端建立连接");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("客户端接受到消息");
        Response resp = (Response) msg;
        String id = resp.getId();
        CompletableFuture future = ChannelHolder.remove(id);
        future.complete(resp);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        log.info("客户端发送数据");
        super.write(ctx, msg, promise);
    }
}
