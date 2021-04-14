package org.mt.mortnon.web.controller;

import org.mt.mortnon.sys.service.HelloService;
import org.mt.mortnon.web.utils.ResultUtil;
import org.mt.mortnon.web.vo.HelloInput;
import org.mt.mortnon.web.vo.HelloOutput;
import org.mt.mortnon.web.vo.MortnonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * Hello Mortnon
     *
     * @apiNote 你好 Mortnon
     * @return
     */
    @GetMapping("/hello")
    public MortnonResult<String> helloWorld() {
        return ResultUtil.success("Hello " + helloService.hello() + "!");
    }

    /**
     * Hello Mortnon with validate
     *
     * @param helloInput
     * @return 返回
     */
    @PostMapping("/hello")
    public MortnonResult<HelloOutput> hello(@Validated @RequestBody HelloInput helloInput) {
        HelloOutput helloOutput = new HelloOutput();
        helloOutput.setName(helloInput.getName());
        helloOutput.setHello("hello");
        return ResultUtil.success(helloOutput);
    }
}
