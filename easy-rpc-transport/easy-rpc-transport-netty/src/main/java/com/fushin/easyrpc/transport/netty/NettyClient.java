package com.fushin.easyrpc.transport.netty;

import com.fushin.easyrpc.core.logging.Logger;
import com.fushin.easyrpc.core.logging.LoggerFactory;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author 丁成文
 * @date 2022/4/22
 */
public class NettyClient {
    private final static Logger log = LoggerFactory.getLogger(NettyClient.class);
    private String url;
    private String serviceKey;
    private Channel channel;
    private EventLoopGroup workerGroup = new NioEventLoopGroup();

    static{
        System.setProperty("easy.logger","slf4j");
    }

    public NettyClient(String url, String serviceKey){
        this.url = url;
        this.serviceKey = serviceKey;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void connect()throws Exception{
        MyClientHandler clientHandler = new MyClientHandler(this);
        try {
            Bootstrap b = new Bootstrap(); // (1)
            b.group(workerGroup); // (2)
            b.channel(NioSocketChannel.class); // (3)
            b.option(ChannelOption.SO_KEEPALIVE, true); // (4)
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline()
                            .addLast("encode", new Codec.Encoder())
                            .addLast("decode", new Codec.Decoder())
                            .addLast("client-idle-handler", new IdleStateHandler(60 * 1000, 0, 0, MILLISECONDS))
                            .addLast(clientHandler);
                }
            });
            ChannelFuture f = b.connect("127.0.0.1", 8099); // (5)
            boolean ret = f.awaitUninterruptibly(10000, MILLISECONDS);
            if(ret && f.isSuccess()){
                io.netty.channel.Channel newChannel = f.channel();
                this.channel = newChannel;
            }
        } finally {
//            workerGroup.shutdownGracefully();
        }
    }

    public void send(String msg){
        this.channel.writeAndFlush(msg);
    }

    public void disconnect(){
        this.workerGroup.shutdownGracefully();
    }

    public static void main(String... args)throws Exception{
        NettyClient client = new NettyClient("1", "testService");
        client.connect();
//        Thread.sleep(5000L);
        log.info("main:{}",Thread.currentThread().getName());
        client.send("dcw");
        client.disconnect();
    }

//    @ChannelHandler.Sharable
    public static class MyClientHandler extends ChannelDuplexHandler{
        private final static Logger log = LoggerFactory.getLogger(MyClientHandler.class);
        private NettyClient client;
        public MyClientHandler(NettyClient client){
            this.client = client;
        }

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            log.info("myClientHandler:{}",Thread.currentThread().getName());
//            Channel channel = ChannelHolder.getOrAddChannel(this.client.getServiceKey(), ctx.channel());
//            channel.send("dcw");
//            this.client.setChannel(channel);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            System.out.println(msg);
            super.channelRead(ctx, msg);
        }

        @Override
        public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
            super.write(ctx, msg, promise);
        }
    }
}
