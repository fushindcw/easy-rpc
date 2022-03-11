package com.fushin.easyrpc.core.logging;

/**
 * @author 丁成文
 * @date 2022/2/22
 */
public class NoSuchLoggerException extends RuntimeException{
    public NoSuchLoggerException(){
        super("没有找到对应的日志组件");
    }

    public NoSuchLoggerException(String loggerName){
        super(String.format("没有找到对应的日志组件[%s]", loggerName));
    }
}
