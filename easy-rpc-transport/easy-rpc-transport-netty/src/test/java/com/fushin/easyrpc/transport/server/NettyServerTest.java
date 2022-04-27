package com.fushin.easyrpc.transport.server;

import com.fushin.easyrpc.api.TestService;
import com.fushin.easyrpc.core.logging.Logger;
import com.fushin.easyrpc.core.logging.LoggerFactory;
import com.fushin.easyrpc.impl.TestServiceImpl;
import com.fushin.easyrpc.transport.netty.ServiceHolder;
import com.fushin.easyrpc.transport.netty.utils.ServiceUtils;
import org.junit.jupiter.api.Test;

/**
 * @author 丁成文
 * @date 2022/4/22
 */
public class NettyServerTest {
    static{
        System.setProperty("easy.logger","slf4j");
    }
    private final static Logger LOG = LoggerFactory.getLogger(NettyServerTest.class);
    private ServiceHolder serviceHolder = ServiceHolder.newInstance();
    @Test
    public void test1(){
        TestService testService = new TestServiceImpl();
        String serviceKey = ServiceUtils.getServiceKey(TestService.class);
        LOG.info("serviceKey:{}", serviceKey);
        serviceHolder.put(serviceKey, testService);
        LOG.info("test1");
    }
}
