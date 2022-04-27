package com.fushin.easyrpc.transport.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
public class ServiceHolder {
    private final Map<String, Object> serviceMap;
    private static ServiceHolder serviceHolder;

    private ServiceHolder(){
        serviceMap = new ConcurrentHashMap<>(64);
    }

    public static ServiceHolder newInstance(){
        if(null == serviceHolder){
            synchronized (ServiceHolder.class){
                if(null == serviceHolder){
                    serviceHolder = new ServiceHolder();
                }
            }
        }
        return serviceHolder;
    }

    public Object put(String serviceKey, Object instance){
        return serviceMap.putIfAbsent(serviceKey, instance);
    }

    public Object get(String serviceKey){
        return serviceMap.get(serviceKey);
    }
}
