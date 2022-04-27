package com.fushin.easyrpc.core.extension;

import java.lang.annotation.*;

/**
 * @author 丁成文
 * @date 2022/4/25
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SPI {
    /**
     * 默认扩展名称
     * @return
     */
    String name() default "";
}
