package com.fushin.easyrpc.transport.netty;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
@Data
public class Request<T extends Serializable> implements Serializable {
    private String serviceKey;
    private T data;
}
