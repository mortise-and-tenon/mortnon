package org.mt.mortnon.web.controller;

import org.mt.mortnon.enums.ErrorCodeEnum;
import org.mt.mortnon.service.HelloService;
import org.mt.mortnon.utils.Asserts;
import org.mt.mortnon.web.utils.ResultUtil;
import org.mt.mortnon.web.vo.HelloInput;
import org.mt.mortnon.web.vo.HelloOutput;
import org.mt.mortnon.web.vo.MortnonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

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
        return ResultUtil.success("Hello " + helloService.hello() + "!  ");
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

    /**
     * 调用异常接口
     *
     * @return
     */
    @GetMapping("/exception")
    public MortnonResult<Void> exception() {

        LOGGER.info("调用异常测试日志");
        LOGGER.error("错误：调用异常测试日志");

        Asserts.assertTrue(false, ErrorCodeEnum.SYSTEM_ERROR, "故意做的系统异常");

        return ResultUtil.success();
    }
}
