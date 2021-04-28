package org.mt.mortnon.web.controller.login;

import lombok.extern.slf4j.Slf4j;
import org.mt.mortnon.framework.utils.JwtTokenUtil;
import org.mt.mortnon.service.login.LoginFactory;
import org.mt.mortnon.service.login.PasswordLoginService;
import org.mt.mortnon.service.login.enums.LoginType;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.framework.utils.ResultUtil;
import org.mt.mortnon.framework.vo.MortnonResult;
import org.mt.mortnon.web.vo.login.PasswordLoginInput;
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

    /**
     * 用户名密码登录
     *
     * @apiNote 用户名密码登录
     */
    @PostMapping("/password")
    public MortnonResult<String> passwordLogin(@Validated @RequestBody PasswordLoginInput input,
                                               HttpServletResponse response) {
        PasswordLoginService loginService = (PasswordLoginService) loginFactory.getLoginService(LoginType.PASSWORD);

        JwtToken token = loginService.authAndLogin(input.getUsername(), input.getPassword());

        response.setHeader(JwtTokenUtil.getTokenName(), token.getToken());
        return ResultUtil.success(token.getToken());
    }
}
