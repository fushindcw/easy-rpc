package com.fushin.easyrpc.core.extension;

/**
 * 扩展点加载器
 * @author 丁成文
 * @date 2022/4/24
 */
public class ExtensionLoader<T extends Extension> {
    private static ExtensionLoader extensionLoader;
    private ExtensionLoader(){
    }

    public static ExtensionLoader newInstance(){
        if(null == extensionLoader){
            synchronized (ExtensionLoader.class){
                if(null == extensionLoader){
                    extensionLoader = new ExtensionLoader();
                }
            }
        }
        return extensionLoader;
    }

    public void register(T t){
        String extensionName = t.extensionName();
    }
}
