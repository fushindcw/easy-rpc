package com.fushin.easyrpc.transport.netty;

import com.fushin.easyrpc.core.logging.Logger;
import com.fushin.easyrpc.core.logging.LoggerFactory;
import com.fushin.easyrpc.transport.Channel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
public class NettyChannel implements Channel {
//    private final static Logger log = LoggerFactory.getLogger(NettyChannel.class);
    private io.netty.channel.Channel channel;
    private String url;
    private String serviceKey;

    public NettyChannel(String url, String serviceKey, io.netty.channel.Channel channel){
        this.url = url;
        this.serviceKey = serviceKey;
        this.channel = channel;
    }
    @Override
    public String getUrl() {
        return this.url;
    }

    @Override
    public String getServiceKey() {
        return this.serviceKey;
    }

    @Override
    public void send(String message) {
//        log.info("channel is active: {}", this.channel.isActive());
//        log.info("channel is open: {}", this.channel.isOpen());
//        log.info("channel is register:{}", this.channel.isRegistered());
        this.channel.writeAndFlush(message);
    }

    @Override
    public void receive() {

    }
}
