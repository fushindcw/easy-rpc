package com.fushin.easyrpc.transport;

import lombok.Data;

import java.io.Serializable;

/**
 * @author δΈζζ
 * @date 2022/7/7
 */
@Data
public class Response implements Serializable {
    private String id;
    private Object data;
}
