package org.mt.mortnon.service.login.impl;

import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.mt.mortnon.framework.enums.ErrorCodeEnum;
import org.mt.mortnon.framework.utils.ShiroAssertUtil;
import org.mt.mortnon.service.login.PasswordLoginService;
import org.mt.mortnon.service.login.enums.LoginConstants;
import org.mt.mortnon.service.login.enums.LoginType;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;
import org.springframework.stereotype.Service;

/**
 * @author dongfangzan
 * @date 27.4.21 4:18 下午
 */
@Service(LoginConstants.LOGIN_TYPE_PASSWORD)
public class PasswordLoginServiceImpl extends AbstractLoginServiceImpl implements PasswordLoginService {

    @Override
    public LoginUser getLoginUserByName(String username) {
        return null;
    }

    @Override
    public SimpleAuthorizationInfo buildAuthorizationInfo(LoginUser loginUser) {
        return null;
    }


    @Override
    public JwtToken authAndLogin(String username, String password) {
        // 验证用户名 密码
        ShiroAssertUtil.assertTrue("admin".equals(username), ErrorCodeEnum.USER_IDENTITY_CHECK_FAILED);

        return login(new LoginUser().setUsername(username), LoginType.PASSWORD);
    }
}
