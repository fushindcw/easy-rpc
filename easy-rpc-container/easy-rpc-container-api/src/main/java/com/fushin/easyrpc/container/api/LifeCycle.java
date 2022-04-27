package com.fushin.easyrpc.container.api;

/**
 * 生命周期接口
 * @author 丁成文
 * @date 2022/4/27
 */
public interface LifeCycle {
    /**
     * 开始
     */
    void start();

    /**
     * 结束
     */
    void stop();
}
