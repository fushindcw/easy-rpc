package com.fushin.easyrpc.transport;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
public interface Channel {
    /**
     * 获取url
     * @return
     */
    String getUrl();

    /**
     * 获取serviceKey
     * @return
     */
    String getServiceKey();

    /**
     * 发送数据
     * @param message
     */
    void send(String message);

    /**
     * 接受数据
     */
    void receive();
}
