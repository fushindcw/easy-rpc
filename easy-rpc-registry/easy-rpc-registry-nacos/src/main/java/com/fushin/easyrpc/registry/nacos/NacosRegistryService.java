package com.fushin.easyrpc.registry.nacos;

import com.alibaba.nacos.api.naming.pojo.Instance;
import com.alibaba.nacos.client.naming.NacosNamingService;
import com.fushin.easyrpc.registry.api.RegistryConfig;
import com.fushin.easyrpc.registry.api.RegistryException;
import com.fushin.easyrpc.registry.api.RegistryService;
import com.fushin.easyrpc.registry.api.ServiceInstance;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
public class NacosRegistryService implements RegistryService {
    private NacosNamingService nacosNamingService;

    public NacosRegistryService(RegistryConfig registryConfig){
        try {
            nacosNamingService = new NacosNamingService(registryConfig.getAddress());
        }catch (Exception e){
            throw new RegistryException(e);
        }
    }
    @Override
    public void register(ServiceInstance serviceInstance) {
        String serviceName = serviceInstance.getServiceKey();
        Instance instance = new Instance();
        instance.setInstanceId(serviceName);
        instance.setIp(serviceInstance.getIp());
        instance.setPort(serviceInstance.getPort());
        instance.setServiceName(serviceName);
        instance.setMetadata(serviceInstance.getMetadata());
        try {
            this.nacosNamingService.registerInstance(serviceName, instance);
        }catch (Exception e){
            throw new RegistryException(e);
        }
    }

    @Override
    public void unRegister() {

    }
}
