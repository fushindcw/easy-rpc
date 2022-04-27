package com.fushin.easyrpc.registry.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
@Setter
@Getter
@ToString
@Builder
public class ServiceInstance {
    private String serviceKey;
    private String ip;
    private Integer port;
    private Map<String, String> metadata = new HashMap<>();
}
