package com.fushin.easyrpc.core.extension;

/**
 * 扩展接口
 * @author 丁成文
 * @date 2022/4/25
 */
public interface Extension {
    /**
     * 扩展名
     * @return
     */
    String extensionName();

    /**
     * 扩展点顺序
     * @return
     */
    default Integer order(){
        return 0;
    }
}
