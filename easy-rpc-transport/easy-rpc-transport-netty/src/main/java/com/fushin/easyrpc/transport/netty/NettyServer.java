package com.fushin.easyrpc.transport.netty;

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
    private EventLoopGroup boss;
    private EventLoopGroup worker;
    private Integer port;
    private String host;

    public NettyServer(String host, Integer port){
        this.host = host;
        this.port = port;
    }

    private void doOpen(){
        
    }

    public static void main(String... args)throws Exception{
        NettyServer server = new NettyServer("127.0.0.1", 8080);
    }
}
