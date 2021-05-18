package fun.mortnon.mortnon.web.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import fun.mortnon.mortnon.framework.enums.ErrorCodeEnum;
import fun.mortnon.mortnon.framework.properties.CaptchaProperties;
import fun.mortnon.mortnon.framework.properties.JwtProperties;
import fun.mortnon.mortnon.framework.utils.AssertI18nUtils;
import fun.mortnon.mortnon.framework.utils.CookieUtil;
import fun.mortnon.mortnon.framework.utils.JwtTokenUtil;
import fun.mortnon.mortnon.framework.utils.ResultUtil;
import fun.mortnon.mortnon.framework.vo.MortnonResult;
import fun.mortnon.mortnon.service.login.CaptchaService;
import fun.mortnon.mortnon.service.login.LoginFactory;
import fun.mortnon.mortnon.service.login.PasswordLoginService;
import fun.mortnon.mortnon.service.login.enums.LoginType;
import fun.mortnon.mortnon.service.login.model.JwtToken;
import fun.mortnon.mortnon.service.login.model.LoginUser;
import fun.mortnon.mortnon.web.controller.utils.LoginUtil;
import fun.mortnon.mortnon.web.vo.login.PasswordLoginInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录
 *
 * @author dongfangzan
 * @date 27.4.21 5:10 下午
 */
@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginFactory loginFactory;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private CaptchaProperties captchaProperties;

    @Autowired
    private CaptchaService captchaService;

    /**
     * 用户名密码登录
     *
     * @apiNote 用户名密码登录
     */
    @PostMapping("/password")
    public MortnonResult<String> passwordLogin(@Validated @RequestBody PasswordLoginInput input,
                                               HttpServletResponse response) {
        checkVerifyCode(input.getCode(), input.getVerifyToken());

        PasswordLoginService loginService = (PasswordLoginService) loginFactory
                .getLoginService(LoginType.PASSWORD);

        JwtToken token = loginService.authAndLogin(input.getUsername(), input.getPassword());

        // 设置在cookie中
        response.setHeader(JwtTokenUtil.getTokenName(), token.getToken());
        CookieUtil.addCookie(response, jwtProperties.getTokenName(), token.getToken(), -1);
        return ResultUtil.success(token.getToken());
    }

    private void checkVerifyCode(String code, String verifyToken) {

        if (!captchaProperties.isEnable()) {
            return;
        }

        AssertI18nUtils.assertTrue(captchaService.verifyCaptcha(verifyToken, code),
                ErrorCodeEnum.VERIFY_CODE_ERROR);
    }

    /**
     * 验证是否登录成功
     *
     * @return
     */
    @GetMapping("/check")
    public MortnonResult<Boolean> checkLogin() {
        return ResultUtil.success(true);
    }

    /**
     * 获取登录用户信息
     *
     * @return 登录用户信息
     */
    @GetMapping("/loginUser")
    public MortnonResult<LoginUser> getLoginUser() {
        return ResultUtil.success(LoginUtil.getLoginUser());
    }

    /**
     * 需要权限码
     *
     * @return
     */
    @RequiresPermissions("permission")
    @GetMapping("/permission")
    public MortnonResult<Boolean> needPermission() {
        return ResultUtil.success(true);
    }

    /**
     * 需要权限码Other
     *
     * @return
     */
    @RequiresPermissions("permissionOther")
    @GetMapping("/permissionOther")
    public MortnonResult<Boolean> needPermissionOther() {
        return ResultUtil.success(true);
    }
}