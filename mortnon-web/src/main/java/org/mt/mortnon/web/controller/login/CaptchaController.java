package org.mt.mortnon.web.controller.login;

import org.mt.mortnon.framework.utils.ResultUtil;
import org.mt.mortnon.framework.vo.MortnonResult;
import org.mt.mortnon.service.login.CaptchaService;
import org.mt.mortnon.service.login.model.MortnonCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码
 *
 * @author dongfangzan
 * @date 30.4.21 9:54 上午
 */
@RestController()
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取验证码
     */
    @RequestMapping("/get")
    public MortnonResult<MortnonCaptcha> captcha() throws Exception {
        return ResultUtil.success(captchaService.generateCaptcha());
    }
}
