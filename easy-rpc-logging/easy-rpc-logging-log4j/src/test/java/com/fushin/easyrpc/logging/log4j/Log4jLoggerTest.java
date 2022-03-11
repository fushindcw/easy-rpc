package com.fushin.easyrpc.logging.log4j;

import com.fushin.easyrpc.core.logging.Logger;
import com.fushin.easyrpc.core.logging.LoggerFactory;
import org.junit.jupiter.api.Test;

/**
 * @author 丁成文
 * @date 2022/2/23
 */
public class Log4jLoggerTest {
    private final static Logger LOG = LoggerFactory.getLogger(Log4jLoggerTest.class);
    @Test
    public void loggerPrintTest(){
        LOG.info("hello log4j");
    }
}
