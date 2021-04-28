package org.mt.mortnon.service.login.impl;

import org.mt.mortnon.service.login.LoginStorageService;
import org.mt.mortnon.service.login.enums.LoginConstants;
import org.mt.mortnon.service.login.enums.LoginType;
import org.mt.mortnon.service.login.model.JwtToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author dongfangzan
 * @date 27.4.21 4:17 下午
 */
@Service(LoginConstants.LOCAL)
public class LocalLoginStorageServiceImpl implements LoginStorageService {
    @Override
    public JwtToken exists(String token) {
        return new JwtToken().setUsername("admin").setLoginType(LoginType.PASSWORD);
    }

    @Override
    public String generateSalt(String username) {
        return null;
    }

    @Override
    public void saveToken(JwtToken jwtToken) {

    }

    @Override
    public void refreshToken(JwtToken jwtToken, HttpServletResponse httpServletResponse) {

    }
}
