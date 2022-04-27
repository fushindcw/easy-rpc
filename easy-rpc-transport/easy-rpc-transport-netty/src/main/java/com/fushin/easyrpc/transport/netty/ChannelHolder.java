package com.fushin.easyrpc.transport.netty;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
public class ChannelHolder {
    private static Map<String, NettyChannel> CHANNEL_MAP = new ConcurrentHashMap<>(64);

    public static NettyChannel getOrAddChannel(String serviceKey, Channel ch){
        NettyChannel nettyChannel;
        if(CHANNEL_MAP.containsKey(serviceKey)){
            nettyChannel = CHANNEL_MAP.get(serviceKey);
        }else {
            nettyChannel = new NettyChannel(null,serviceKey, ch);
            CHANNEL_MAP.putIfAbsent(serviceKey, nettyChannel);
        }
        return nettyChannel;
    }

    public static NettyChannel get(String serviceKey){
        return CHANNEL_MAP.get(serviceKey);
    }
}
