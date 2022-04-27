package com.fushin.easyrpc.registry.api;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
public interface RegistryService {
    void register(ServiceInstance serviceInstance);

    void unRegister();

    interface NotifyListener{

    }
}
