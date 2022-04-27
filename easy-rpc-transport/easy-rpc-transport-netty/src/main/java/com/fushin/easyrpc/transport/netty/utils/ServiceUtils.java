package com.fushin.easyrpc.transport.netty.utils;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
public class ServiceUtils {
    public static String getServiceKey(Class clzz){
        String serviceKey = null;
        try{
            if(!clzz.isInterface()){
                throw new RuntimeException("不是接口类");
            }
            serviceKey = clzz.getName();
        }catch(Exception e){
            throw new RuntimeException(e);
        }
        return serviceKey;
    }
}
