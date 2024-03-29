package org.mt.mortnon.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.mt.mortnon.framework.enums.ErrorCodeEnum;
import org.mt.mortnon.framework.utils.AssertsUtil;
import org.mt.mortnon.framework.utils.I18nUtil;
import org.mt.mortnon.framework.utils.ResultUtil;
import org.mt.mortnon.framework.vo.MortnonResult;
import org.mt.mortnon.web.vo.DemoInput;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Demo
 *
 * @author dongfangzan
 * @date 22.4.21 11:07 上午
 */
@Slf4j
@RestController()
@RequestMapping("/demo")
public class DemoController {

    /**
     * hello world
     *
     * @apiNote hello world
     */
    @GetMapping("/hello")
    public MortnonResult<String> hello() {
        return ResultUtil.success("Hello Mortnon!");
    }

    /**
     * 表单提交demo
     *
     * @apiNote 验证input里的参数，验证成功返回输入参数
     */
    @PostMapping("/form")
    public MortnonResult<DemoInput> form(@Validated @RequestBody DemoInput input) {
        return ResultUtil.success(input);
    }

    /**
     * 验证错误
     *
     * @apiNote 验证异常处理，错误日志打印，并返回错误异常
     *
     * {
     *     "data": null,
     *     "errorCode": "B0001",
     *     "message": "故意做的系统异常",
     *     "success": false
     * }
     */
    @GetMapping("/error")
    public MortnonResult<Void> error() {
        // 打印在web-digest.log文件中
        log.info("调用异常测试日志");

        // 打印在common-error.log以及web-digest.log文件中
        log.error("错误：调用异常测试日志");

        // 打印在common-error.log以及web-digest.log文件中，阻断流程，返回下列
        AssertsUtil.assertTrue(false, ErrorCodeEnum.SYSTEM_ERROR, I18nUtil.getMessage(ErrorCodeEnum.SYSTEM_ERROR.getErrorCode()));

        return ResultUtil.success();
    }
}
