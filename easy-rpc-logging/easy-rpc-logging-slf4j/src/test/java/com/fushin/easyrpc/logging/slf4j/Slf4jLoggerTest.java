package com.fushin.easyrpc.logging.slf4j;

import com.fushin.easyrpc.core.logging.Logger;
import com.fushin.easyrpc.core.logging.LoggerFactory;
import org.junit.jupiter.api.Test;

/**
 * @author 丁成文
 * @date 2022/2/23
 */
public class Slf4jLoggerTest {
    private final static Logger LOG = LoggerFactory.getLogger(Slf4jLoggerTest.class);
    @Test
    public void loggerPrintTest(){
        LOG.info("hello slf4j");
    }
}
