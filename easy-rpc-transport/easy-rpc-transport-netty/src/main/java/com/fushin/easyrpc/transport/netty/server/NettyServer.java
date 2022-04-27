package com.fushin.easyrpc.transport.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.nio.charset.Charset;

/**
 * @author 丁成文
 * @date 2022/4/22
 */
public class NettyServer {
    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();
    public NettyServer()throws Exception{
        try {
            ServerBootstrap b = new ServerBootstrap()
                    .group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MyServerHandler());
                        }
                    }).option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(8099).sync();
            f.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String... args)throws Exception{
        NettyServer server = new NettyServer();
    }

    public static class MyServerHandler extends ChannelDuplexHandler{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf b = (ByteBuf) msg;
            System.out.println(b.toString(Charset.forName("UTF-8")));
            super.channelRead(ctx, msg);
        }
    }
}
