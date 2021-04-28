package org.mt.mortnon.service.login;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.mt.mortnon.service.login.enums.LoginType;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;

/**
 * @author dongfangzan
 * @date 27.4.21 3:09 下午
 */
public interface LoginService {

    /**
     * 根据用户名获取登录用户
     *
     * @param username 用户名
     * @return         登录用户
     */
    LoginUser getLoginUserByName(String username);

    /**
     * 根据LoginUser构建权限信息
     *
     * @param loginUser 登录用户
     * @return 权限信息
     */
    SimpleAuthorizationInfo buildAuthorizationInfo(LoginUser loginUser);

    /**
     * 认证后的登录，调用该方法完成最后的登录流程
     *
     * @param authorizedUser 已经认证过后的用户
     * @param loginType      登录类型
     * @return               token
     */
    JwtToken login(LoginUser authorizedUser, LoginType loginType);
}
