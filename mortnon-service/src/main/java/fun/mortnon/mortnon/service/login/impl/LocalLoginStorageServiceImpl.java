package fun.mortnon.mortnon.service.login.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import fun.mortnon.mortnon.framework.cache.LocalCacheManager;
import fun.mortnon.mortnon.framework.properties.JwtProperties;
import fun.mortnon.mortnon.framework.utils.JacksonUtil;
import fun.mortnon.mortnon.service.login.LoginStorageService;
import fun.mortnon.mortnon.service.login.enums.LoginConstants;
import fun.mortnon.mortnon.service.login.model.JwtToken;
import fun.mortnon.mortnon.service.login.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void refreshToken(String oldToken, String username, JwtToken newJwtToken) {
        LoginUser loginUser = getLoginUserByName(username);

        deleteToken(oldToken, username);

        saveToken(loginUser, newJwtToken);
    }

    @Override
    public void deleteToken(String token, String username) {
        String tokenMd5 = DigestUtils.md5Hex(token);

        localCacheManager.remove(String.format(LoginConstants.LOGIN_TOKEN, tokenMd5));
        localCacheManager.remove(String.format(LoginConstants.LOGIN_USER, username));
        if (jwtProperties.isSaltCheck()) {
            localCacheManager.remove(String.format(LoginConstants.LOGIN_SALT, username));
        }
    }

    @Override
    public void saveVerifyCode(String key, String code) {
        localCacheManager.getCaptchaCache().put(String.format(LoginConstants.VERIFY_CODE, key), code);
    }

    @Override
    public void deleteVerifyCode(String key) {
        localCacheManager.remove(String.format(LoginConstants.VERIFY_CODE, key));
    }

    @Override
    public String getVerifyCode(String key) {
        return localCacheManager.getCaptchaCache().get(key);
    }
}
