package com.fushin.easyrpc.registry.nacos;

import com.fushin.easyrpc.registry.api.RegistryConfig;
import com.fushin.easyrpc.registry.api.RegistryService;
import com.fushin.easyrpc.registry.api.ServiceInstance;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 
 * @author 丁成文
 * @date 2022/4/27
 */
class NacosRegistryServiceTest {
    RegistryConfig config = RegistryConfig.builder()
            .address("127.0.0.1:8848")
            .build();
    @Test
    void registerInstanceTest(){
        ServiceInstance instance = ServiceInstance.builder()
                .serviceKey("test1")
                .ip("127.0.0.1")
                .port(20080)
                .metadata(new HashMap<>())
                .build();
        RegistryService registryService = new NacosRegistryService(config);
        registryService.register(instance);
    }

    @Test
    void unRegisterInstanceTest(){
        RegistryService registryService = new NacosRegistryService(config);
        registryService.unRegister("test1");
    }

    @Test
    void subscribeInstanceTest(){
        RegistryService registryService = new NacosRegistryService(config);
        registryService.subscribe("test1", (instanceList -> {
            if(instanceList.isEmpty()){
                System.out.println("清空了");
            }else{
                System.out.println(instanceList);
            }
        }));
    }
}
