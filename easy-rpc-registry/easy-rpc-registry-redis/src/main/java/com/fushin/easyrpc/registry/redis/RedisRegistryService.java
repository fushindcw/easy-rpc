package com.fushin.easyrpc.registry.redis;

import com.fushin.easyrpc.registry.api.RegistryConfig;
import com.fushin.easyrpc.registry.api.RegistryService;
import com.fushin.easyrpc.registry.api.ServiceInstance;
import com.fushin.easyrpc.registry.redis.client.RedisClient;
import com.fushin.easyrpc.registry.redis.instance.RedisInstance;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
public class RedisRegistryService implements RegistryService {
    private RedisClient redisClient;

    public RedisRegistryService(RegistryConfig registryConfig){
        this.redisClient = new RedisClient(registryConfig.getHost(), registryConfig.getPort());
    }
    @Override
    public void register(ServiceInstance serviceInstance) {
        RedisInstance redisInstance = new RedisInstance();
        redisInstance.setIp(serviceInstance.getIp());
        redisInstance.setPort(serviceInstance.getPort());
        this.redisClient.register(serviceInstance.getServiceKey(), redisInstance);
    }

    @Override
    public void unRegister(String serviceKey) {

    }

    @Override
    public void subscribe(String serviceKey, NotifyListener listener) {
        this.redisClient.subscribe(serviceKey, (event)->{
            List<RedisInstance> redisInstanceList = event.getRedisInstanceList();
            listener.notify(redisInstanceList.stream().map(item->ServiceInstance.builder()
                    .ip(item.getIp()).port(item.getPort()).build())
                    .collect(Collectors.toList()));
        });
    }
}
