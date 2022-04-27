package com.fushin.easyrpc.registry.api;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
public abstract class AbstractRegistryService implements  RegistryService{
    protected String ip;
    protected Integer port;

    protected AbstractRegistryService(String ip, Integer port){
        this.ip = ip;
        this.port = port;
    }
}
