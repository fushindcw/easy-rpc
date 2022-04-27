package com.fushin.easyrpc.impl;

import com.fushin.easyrpc.api.TestService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 丁成文
 * @date 2022/4/24
 */
@Slf4j
public class TestServiceImpl implements TestService {
    @Override
    public String test(String msg) {
        log.info(msg);
        return "hello, msg";
    }
}
