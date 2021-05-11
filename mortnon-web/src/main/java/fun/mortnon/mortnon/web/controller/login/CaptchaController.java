package fun.mortnon.mortnon.web.controller.login;

import fun.mortnon.mortnon.framework.utils.ResultUtil;
import fun.mortnon.mortnon.framework.vo.MortnonResult;
import fun.mortnon.mortnon.service.login.CaptchaService;
import fun.mortnon.mortnon.service.login.model.MortnonCaptcha;
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
