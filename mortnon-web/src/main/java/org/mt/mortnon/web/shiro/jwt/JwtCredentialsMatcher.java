package org.mt.mortnon.web.shiro.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.mt.mortnon.framework.utils.JwtUtil;

/**
 * @author dongfangzan
 * @date 27.4.21 2:56 下午
 */
@Slf4j
public class JwtCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken authenticationToken, AuthenticationInfo authenticationInfo) {
        String token = authenticationToken.getCredentials().toString();
        String salt = authenticationInfo.getCredentials().toString();

        try {
            return JwtUtil.verifyToken(token, salt);
        } catch (Exception e) {
            log.error("JWT Token CredentialsMatch Exception:{},{}", e.getMessage(), e);
        }
        return false;
    }
}