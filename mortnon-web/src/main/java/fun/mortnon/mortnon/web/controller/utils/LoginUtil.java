package fun.mortnon.mortnon.web.controller.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import fun.mortnon.mortnon.framework.utils.JwtTokenUtil;
import fun.mortnon.mortnon.framework.utils.JwtUtil;
import fun.mortnon.mortnon.service.login.LoginFactory;
import fun.mortnon.mortnon.service.login.model.LoginUser;
import org.springframework.stereotype.Component;

/**
 * @author dongfangzan
 * @date 29.4.21 4:17 下午
 */
@Slf4j
@Component
public class LoginUtil {

    private static LoginFactory loginFactory;

    public LoginUtil(LoginFactory loginFactory) {
        LoginUtil.loginFactory = loginFactory;
    }

    /**
     * 获取当前登录用户
     *
     * @return 获取当前登录用户
     */
    public static LoginUser getLoginUser() {
        String token = JwtTokenUtil.getToken();
        String username = JwtUtil.getUsername(token);
        if (StringUtils.isBlank(username)) {
            return null;
        }

        return loginFactory.getConfigLoginStorageService().getLoginUserByName(username);
    }

    /**
     * 获取当前登录用户id
     *
     * @return id
     */
    public static String getUserId() {
        LoginUser loginUser = getLoginUser();
        if (null == loginUser) {
            return null;
        }

        return loginUser.getId();
    }

    /**
     * 获取当前用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        LoginUser loginUser = getLoginUser();
        if (null == loginUser) {
            return null;
        }

        return loginUser.getUsername();
    }
}
