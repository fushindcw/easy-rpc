package com.fushin.easyrpc.transport.netty;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author 丁成文
 * @date 2022/7/7
 */
public class ChannelHolder {
    private static Map<String, CompletableFuture> map = new HashMap<>();

    public static void put(String key, CompletableFuture future){
        map.put(key, future);
    }

    public static CompletableFuture remove(String key){
        CompletableFuture future = map.get(key);
        map.remove(key);
        return future;
    }
}