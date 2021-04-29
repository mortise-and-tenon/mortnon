package org.mt.mortnon.service.login.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.mt.mortnon.framework.cache.LocalCacheManager;
import org.mt.mortnon.framework.properties.JwtProperties;
import org.mt.mortnon.framework.utils.JacksonUtil;
import org.mt.mortnon.service.login.LoginStorageService;
import org.mt.mortnon.service.login.enums.LoginConstants;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dongfangzan
 * @date 27.4.21 4:17 下午
 */
@Slf4j
@Service(LoginConstants.LOCAL)
public class LocalLoginStorageServiceImpl implements LoginStorageService {

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private LocalCacheManager localCacheManager;

    @Override
    public LoginUser getLoginUserByName(String username) {
        String userStr = localCacheManager.getValue(String.format(LoginConstants.LOGIN_USER, username));
        return JacksonUtil.jsonToObject(userStr, LoginUser.class);
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
        String tokenMd5 = DigestUtils.md5Hex(token);
        String tokenStr = localCacheManager.getValue(String.format(LoginConstants.LOGIN_TOKEN, tokenMd5));
        return JacksonUtil.jsonToObject(tokenStr, JwtToken.class);
    }

    @Override
    public String getSaltFromCache(String username) {
        return localCacheManager.getValue(String.format(LoginConstants.LOGIN_SALT, username));
    }

    @Override
    public void saveToken(LoginUser loginUser, JwtToken jwtToken) {
        String tokenMd5 = DigestUtils.md5Hex(jwtToken.getToken());
        String tokenKey = String.format(LoginConstants.LOGIN_TOKEN, tokenMd5);

        // 1. login:token:${tokenMd5}:${jwtToken}
        localCacheManager.putValue(tokenKey, JacksonUtil.objectToJson(jwtToken));

        // 2. login:user:${username}:${loginUser}
        localCacheManager.putValue(String.format(LoginConstants.LOGIN_USER,
                loginUser.getUsername()), JacksonUtil.objectToJson(loginUser));

        if (jwtProperties.isSaltCheck()) {
            // 3. login:salt:${username}:${salt}
            localCacheManager.putValue(String.format(LoginConstants.LOGIN_SALT,
                    loginUser.getUsername()), jwtToken.getSalt());
        }
    }

    @Override
    public void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse) {
        // 内存缓存不用做任何事情
    }
}
