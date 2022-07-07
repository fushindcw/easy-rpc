package com.fushin.easyrpc.transport.netty;

import com.fushin.easyrpc.core.logging.Logger;
import com.fushin.easyrpc.core.logging.LoggerFactory;
import com.fushin.easyrpc.transport.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * @author 丁成文
 * @date 2022/4/22
 */
public class NettyClient {
    private final static Logger log = LoggerFactory.getLogger(NettyClient.class);
    private String url;
    private String serviceKey;
    private EventLoopGroup worker = new NioEventLoopGroup();

    static{
        System.setProperty("easy.logger","slf4j");
    }

    public NettyClient(){
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public Channel connect()throws Exception{
            Bootstrap b = new Bootstrap();
            b.group(this.worker);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_REUSEADDR, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    Codec codec = new Codec();
                    ch.pipeline()
                            .addLast("encode", codec.encoder())
                            .addLast("decode", codec.decoder())
                            .addLast("client-idle-handler", new IdleStateHandler(60 * 1000, 0, 0, MILLISECONDS))
                            .addLast("handler", new NettyClientHandler());
                }
            });
            ChannelFuture f = b.connect("127.0.0.1", 8080).sync();
            return f.channel();
    }

    public void destroy(){
        this.worker.shutdownGracefully();
    }

    public static void main(String[] args)throws Exception {
        NettyClient client = new NettyClient();
        Channel c = client.connect();
        Request request = new Request();
        String id = "dingchw";
        request.setId(id);
        c.writeAndFlush(request);

        CompletableFuture future = new CompletableFuture();
        ChannelHolder.put(id, future);
        System.out.println("响应结果>>>{}" + future.get());
        client.destroy();
    }

}
