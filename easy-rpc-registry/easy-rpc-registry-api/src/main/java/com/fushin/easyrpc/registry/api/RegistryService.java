package com.fushin.easyrpc.registry.api;

import java.util.List;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
public interface RegistryService {
    /**
     * 服务注册
     * @param serviceInstance
     */
    void register(ServiceInstance serviceInstance);

    /**
     * 服务注销
     * @param serviceKey
     */
    void unRegister(String serviceKey);

    /**
     * 服务订阅
     * @param serviceKey
     * @return
     */
    void subscribe(String serviceKey, NotifyListener listener);

    /**
     * 实例变化
     */
    interface NotifyListener{
        void notify(List<ServiceInstance> instanceList);
    }
}
