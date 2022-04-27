package com.fushin.easyrpc.registry.api;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
public class RegistryException extends RuntimeException{
    public RegistryException(Throwable t){
        super(t);
    }
}
