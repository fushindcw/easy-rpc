package com.fushin.easyrpc.registry.redis.listener;

import com.fushin.easyrpc.registry.redis.instance.RedisInstance;
import lombok.Getter;

import java.util.List;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
@Getter
public class InstanceEvent implements Event{
    private List<RedisInstance> redisInstanceList;

    public InstanceEvent(List<RedisInstance> instanceList){
        this.redisInstanceList = instanceList;
    }
}
