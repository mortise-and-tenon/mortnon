package fun.mortnon.mortnon.service.login.impl;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import fun.mortnon.mortnon.framework.properties.CaptchaProperties;
import fun.mortnon.mortnon.service.login.CaptchaService;
import fun.mortnon.mortnon.service.login.LoginFactory;
import fun.mortnon.mortnon.service.login.LoginStorageService;
import fun.mortnon.mortnon.service.login.enums.LoginConstants;
import fun.mortnon.mortnon.service.login.model.MortnonCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author dongfangzan
 * @date 30.4.21 10:30 上午
 */
@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private CaptchaProperties captchaProperties;

    @Autowired
    private LoginFactory loginFactory;

    @Override
    public MortnonCaptcha generateCaptcha() {
        MortnonCaptcha mortnonCaptcha = new MortnonCaptcha();
        Captcha captcha = new SpecCaptcha();
        if (captchaProperties.getType().equals(LoginConstants.CAPTCHA_TYPE_DEFAULT)) {
            captcha = new SpecCaptcha();
            captcha.setLen(captchaProperties.getLength());
        }

        if (captchaProperties.getType().equals(LoginConstants.CAPTCHA_TYPE_GIF)) {
            captcha = new GifCaptcha();
            captcha.setLen(captchaProperties.getLength());
        }

        if (captchaProperties.getType().equals(LoginConstants.CAPTCHA_TYPE_CHINESE)) {
            captcha = new ChineseCaptcha();
            // 汉字4位
            captcha.setLen(4);
        }

        if (captchaProperties.getType().equals(LoginConstants.CAPTCHA_TYPE_ARITHMETIC)) {
            captcha = new ArithmeticCaptcha();
            // 算术2位即可
            captcha.setLen(2);
        }

        captcha.setWidth(captchaProperties.getWidth());
        captcha.setHeight(captchaProperties.getHeight());


        mortnonCaptcha.setCaptchaKey(UUID.randomUUID().toString());
        mortnonCaptcha.setCaptchaImage(captcha.toBase64());

        LoginStorageService configLoginStorageService = loginFactory.getConfigLoginStorageService();

        configLoginStorageService.saveVerifyCode(mortnonCaptcha.getCaptchaKey(), captcha.text());

        return mortnonCaptcha;
    }

    @Override
    public boolean verifyCaptcha(String captchaKey, String captchaCode) {

        if (StringUtils.isBlank(captchaKey) || StringUtils.isBlank(captchaCode)) {
            return false;
        }

        LoginStorageService configLoginStorageService = loginFactory.getConfigLoginStorageService();
        String verifyCode = configLoginStorageService.getVerifyCode(captchaKey);
        if (StringUtils.isBlank(verifyCode)) {
            return false;
        }

        boolean result = captchaCode.equalsIgnoreCase(verifyCode);

        // 验证通过后清除验证码
        if (result) {
            configLoginStorageService.deleteVerifyCode(captchaKey);
        }

        return result;
    }
}
