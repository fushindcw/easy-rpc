package com.fushin.easyrpc.registry.api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.net.URL;

/**
 * @author 丁成文
 * @date 2022/4/27
 */
@Setter
@Getter
@ToString
@Builder
public class RegistryConfig {
    /**
     * 协议
     */
    private String protocol;
    /**
     * ip
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 地址
     */
    private String address;

    public void setAddress(String address) {
        this.address = address;
        try {
            URL url = new URL(address);
            this.protocol = url.getProtocol();
            this.host = url.getHost();
            this.port = url.getPort();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
