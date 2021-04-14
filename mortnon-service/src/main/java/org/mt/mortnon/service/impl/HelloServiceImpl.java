package org.mt.mortnon.service.impl;

import org.mt.mortnon.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * hello
 *
 * @date 2021-04-13 17:56:12
 * @author dongfangzan
 */
@Service
public class HelloServiceImpl implements HelloService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String hello() {
        LOGGER.info("测试service层日志打印");

        LOGGER.error("错误日志测试打印");

        return "Mortnon";
    }
}
