package fun.mortnon.mortnon.service.login;

import fun.mortnon.mortnon.service.login.model.MortnonCaptcha;

/**
 * @author dongfangzan
 * @date 30.4.21 10:26 上午
 */
public interface CaptchaService {

    /**
     * 生成验证码
     *
     * @return
     */
    MortnonCaptcha generateCaptcha();

    /**
     * 验证码是否验证通过
     *
     * @param captchaKey  验证码key
     * @param captchaCode 验证码值
     */
    boolean verifyCaptcha(String captchaKey, String captchaCode);
}