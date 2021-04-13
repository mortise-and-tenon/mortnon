package org.mt.mortnon.web.controller;

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

    /**
     * hello world
     * @return hello world
     */
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello Mortnon!";
    }
}
