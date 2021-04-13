package org.mt.mortnon.sys.service.impl;

import org.mt.mortnon.sys.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * hello
 *
 * @date 2021-04-13 17:56:12
 * @author dongfangzan
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello() {
        return "Mortnon";
    }
}
