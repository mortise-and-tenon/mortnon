package org.mt.mortnon.service.login.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.mt.mortnon.framework.properties.CaptchaProperties;
import org.mt.mortnon.framework.properties.JwtProperties;
import org.mt.mortnon.service.login.LoginStorageService;
import org.mt.mortnon.service.login.enums.LoginConstants;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author dongfangzan
 * @date 27.4.21 4:17 下午
 */
@Service("redis")
public class RedisLoginStorageServiceImpl implements LoginStorageService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private CaptchaProperties captchaProperties;

    @Override
    public LoginUser getLoginUserByName(String username) {
        return (LoginUser) redisTemplate.opsForValue().get(String.format(LoginConstants.LOGIN_USER, username));
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
        return (JwtToken) redisTemplate.opsForValue().get(String.format(LoginConstants.LOGIN_TOKEN, tokenMd5));
    }

    @Override
    public String getSaltFromCache(String username) {
        return (String) redisTemplate.opsForValue().get(String.format(LoginConstants.LOGIN_SALT, username));
    }

    @Override
    public void saveToken(LoginUser loginUser, JwtToken jwtToken) {
        String tokenMd5 = DigestUtils.md5Hex(jwtToken.getToken());
        String tokenKey = String.format(LoginConstants.LOGIN_TOKEN, tokenMd5);

        // 1. login:token:${tokenMd5}:${jwtToken}
        redisTemplate.opsForValue().set(tokenKey, jwtToken,
                jwtProperties.getExpireSecond(), TimeUnit.SECONDS);

        // 2. login:user:${username}:${loginUser}
        redisTemplate.opsForValue().set(String.format(LoginConstants.LOGIN_USER,
                loginUser.getUsername()), loginUser,
                jwtProperties.getExpireSecond(), TimeUnit.SECONDS);

        if (jwtProperties.isSaltCheck()) {
            // 3. login:salt:${username}:${salt}
            redisTemplate.opsForValue().set(String.format(LoginConstants.LOGIN_SALT,
                    loginUser.getUsername()), jwtToken.getSalt(),
                    jwtProperties.getExpireSecond(), TimeUnit.SECONDS);
        }
    }

    @Override
    public void refreshToken(String oldToken, String username, JwtToken newJwtToken) {
        LoginUser loginUser = getLoginUserByName(username);

        deleteToken(oldToken, username);

        saveToken(loginUser, newJwtToken);
    }

    @Override
    public void deleteToken(String token, String username) {
        String tokenMd5 = DigestUtils.md5Hex(token);

        redisTemplate.delete(String.format(LoginConstants.LOGIN_TOKEN, tokenMd5));
        redisTemplate.delete(String.format(LoginConstants.LOGIN_USER, username));
        if (jwtProperties.isSaltCheck()) {
            redisTemplate.delete(String.format(LoginConstants.LOGIN_SALT, username));
        }
    }

    @Override
    public void saveVerifyCode(String key, String code) {
        redisTemplate.opsForValue().set(String.format(LoginConstants.VERIFY_CODE, key), code,
                captchaProperties.getExpireSeconds(), TimeUnit.SECONDS);
    }

    @Override
    public void deleteVerifyCode(String key) {
        redisTemplate.delete(String.format(LoginConstants.VERIFY_CODE, key));
    }

    @Override
    public String getVerifyCode(String key) {
        return (String) redisTemplate.opsForValue().get(String.format(LoginConstants.VERIFY_CODE, key));
    }
}
