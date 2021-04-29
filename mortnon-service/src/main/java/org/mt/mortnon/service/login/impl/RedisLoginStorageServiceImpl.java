package org.mt.mortnon.service.login.impl;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.mt.mortnon.service.login.LoginStorageService;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dongfangzan
 * @date 27.4.21 4:17 下午
 */
@Service("redis")
public class RedisLoginStorageServiceImpl implements LoginStorageService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public LoginUser getLoginUserByName(String username) {
        return null;
    }

    @Override
    public SimpleAuthorizationInfo buildAuthorizationInfo(LoginUser loginUser) {
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        // 从缓存中读出权限码，并加入
        simpleAuthorizationInfo.setStringPermissions(loginUser.getPermissionCodes());
        simpleAuthorizationInfo.setRoles(loginUser.getRoles());
        return simpleAuthorizationInfo;
    }

    @Override
    public JwtToken exists(String token) {
        return null;
    }

    @Override
    public String getSaltFromCache(String username) {
        return null;
    }

    @Override
    public void saveToken(LoginUser loginUser, JwtToken jwtToken) {

    }

    @Override
    public void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse) {

    }
}
