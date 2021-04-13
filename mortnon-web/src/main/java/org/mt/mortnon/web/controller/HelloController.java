package org.mt.mortnon.web.controller;

import org.mt.mortnon.sys.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello world
 *
 * @date 2021-04-13 17:24:32
 * @author dongfangzan
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    /**
     * hello world
     * @return hello world
     */
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello " + helloService.hello() + "!";
    }
}
