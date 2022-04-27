package com.fushin.easyrpc.registry.redis.instance;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
@Setter
@Getter
@ToString
public class RedisInstance implements Serializable {
    private String ip;
    private Integer port;

    @Override
    public boolean equals(Object obj) {
        RedisInstance target = (RedisInstance) obj;
        return this.ip.equals(target.getIp()) && this.port.equals(target.getPort());
    }
}
