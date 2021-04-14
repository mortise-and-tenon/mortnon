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
     * 这是我第一个酷毙了的接口
     *
     * @apiNote 这是一个详细描述，很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长
     * 很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长很长的注释
     * @return hello world
     */
    @GetMapping("/hello")
    public MortnonResult<String> helloWorld() {
        return ResultUtil.success("Hello " + helloService.hello() + "!");
    }

    /**
     * 跟人打招呼的接口
     *
     * @param helloInput 呵呵
     * @return 返回
     */
    @PostMapping("/helloman")
    public MortnonResult<HelloOutput> helloman(@Validated @RequestBody HelloInput helloInput) {
        HelloOutput helloOutput = new HelloOutput();
        helloOutput.setName(helloInput.getName());
        helloOutput.setHello("hello");
        return ResultUtil.success(helloOutput);
    }
}
