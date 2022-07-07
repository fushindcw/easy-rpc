package com.fushin.easyrpc.transport.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author 丁成文
 * @date 2022/4/22
 */
@Slf4j
public class NettyServer {
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private Integer port;
    private String host;

    static{
        System.setProperty("easy.logger","slf4j");
    }


    public NettyServer(String host, Integer port){
        this.host = host;
        this.port = port;
    }

    public void start()throws Exception{
        boss = new NioEventLoopGroup();
        worker = new NioEventLoopGroup();
        ServerBootstrap b = new ServerBootstrap();
        b.group(this.boss, this.worker)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .option(ChannelOption.SO_REUSEADDR, true)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    Codec codec = new Codec();
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline()
                                .addLast("encoder", codec.encoder())
                                .addLast("decoder", codec.decoder())
                                .addLast("handler", new NettyServerHandler());
                    }
                });
        ChannelFuture f = b.bind(this.host, this.port).sync();
        f.awaitUninterruptibly();
        log.info("server is started");
    }

    public void destroy(){
        this.boss.shutdownGracefully();
        this.worker.shutdownGracefully();
    }

    public static void main(String... args)throws Exception{
        NettyServer server = new NettyServer("127.0.0.1", 8080);
        server.start();
        System.in.read();
    }
}
