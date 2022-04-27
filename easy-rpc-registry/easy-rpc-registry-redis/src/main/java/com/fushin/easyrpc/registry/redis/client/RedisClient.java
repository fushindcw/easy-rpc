package com.fushin.easyrpc.registry.redis.client;

import com.alibaba.fastjson.JSON;
import com.fushin.easyrpc.registry.redis.instance.RedisInstance;
import com.fushin.easyrpc.registry.redis.listener.InstanceEvent;
import com.fushin.easyrpc.registry.redis.listener.NotifyListener;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
public class RedisClient {
    private Jedis jedis;

    public RedisClient(String ip, Integer port){
        DefaultJedisClientConfig clientConfig = DefaultJedisClientConfig.builder()
                .database(0)
                .build();
        jedis = new Jedis(ip, port, clientConfig);
    }

    public void register(String serviceKey, RedisInstance instance){
        long len = this.jedis.llen(serviceKey);
        List<String> serviceJsonList = jedis.lrange(serviceKey, 0L, len);
        List<RedisInstance> instanceList = serviceJsonList.stream()
                .map(item->JSON.parseObject(item, RedisInstance.class))
                .collect(Collectors.toList());
        if(!instanceList.contains(instance)){
            instanceList.add(instance);
        }
        this.jedis.del(serviceKey);
        this.jedis.lpush(serviceKey, toArray(instanceList));
        this.jedis.publish(serviceKey, JSON.toJSONString(instanceList));
    }

    private String[] toArray(List<RedisInstance> sourceList){
        return sourceList.stream().map(item->JSON.toJSONString(item)).toArray(String[]::new);
    }

    public void unRegister(String serviceKey, RedisInstance instance){

    }

    public void subscribe(String serviceKey, NotifyListener listener){
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                List<RedisInstance> instanceList = JSON.parseArray(message, RedisInstance.class);
                InstanceEvent event = new InstanceEvent(instanceList);
                listener.notify(event);
            }
        }, serviceKey);
    }
}
