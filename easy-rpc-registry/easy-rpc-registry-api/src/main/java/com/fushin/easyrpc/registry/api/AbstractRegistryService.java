package com.fushin.easyrpc.registry.api;

/**
 * @author δΈζζ
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
