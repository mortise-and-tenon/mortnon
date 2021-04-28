package org.mt.mortnon.service.login;

import org.mt.mortnon.service.login.model.JwtToken;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dongfangzan
 * @date 27.4.21 4:16 下午
 */
public interface LoginStorageService {

    /**
     * 判断token 是否存在
     *
     * @param token token
     * @return      true-存在 false-不存在
     */
    JwtToken exists(String token);

    /**
     * 生成盐值
     *
     * @param username
     * @return
     */
    String generateSalt(String username);


    /**
     * 保存token信息
     *
     * @param jwtToken
     */
    void saveToken(JwtToken jwtToken);

    /**
     * 刷新token
     *
     * @param jwtToken
     * @param httpServletResponse
     */
    void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse);
}
