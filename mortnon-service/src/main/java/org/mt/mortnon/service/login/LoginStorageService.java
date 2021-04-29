package org.mt.mortnon.service.login;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dongfangzan
 * @date 27.4.21 4:16 下午
 */
public interface LoginStorageService {

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
     * 判断token 是否存在
     *
     * @param token token
     * @return      true-存在 false-不存在
     */
    JwtToken exists(String token);

    /**
     * 从缓存中获取cache
     *
     * @param username
     * @return
     */
    String getSaltFromCache(String username);


    /**
     * 保存token信息
     *
     * @param loginUser 登录用户
     * @param jwtToken  token
     */
    void saveToken(LoginUser loginUser, JwtToken jwtToken);

    /**
     * 刷新token
     *
     * @param jwtToken
     * @param httpServletResponse
     */
    void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse);
}
