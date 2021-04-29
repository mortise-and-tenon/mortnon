package org.mt.mortnon.service.login.impl;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.mt.mortnon.dal.sys.entity.SysUser;
import org.mt.mortnon.framework.enums.ErrorCodeEnum;
import org.mt.mortnon.framework.utils.AssertI18nUtils;
import org.mt.mortnon.service.login.PasswordLoginService;
import org.mt.mortnon.service.login.enums.LoginConstants;
import org.mt.mortnon.service.login.enums.LoginType;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;
import org.mt.mortnon.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dongfangzan
 * @date 27.4.21 4:18 下午
 */
@Slf4j
@Service(LoginConstants.LOGIN_TYPE_PASSWORD)
public class PasswordLoginServiceImpl extends AbstractLoginServiceImpl implements PasswordLoginService {

    /** 用户服务 */
    @Autowired
    private SysUserService sysUserService;

    @Override
    public JwtToken authAndLogin(String username, String password) {

        // 1 认证并获取用户
        SysUser sysUser = authAndGetUser(username, password);

        // 2 验证完成，构建登录用户
        LoginUser loginUser = new LoginUser()
                .setId("" + sysUser.getId())
                .setUsername(sysUser.getUsername())
                .setSalt(sysUser.getSalt())
                .setLoginType(LoginType.PASSWORD)
                // 角色码
                .setRoles(Sets.newHashSet())
                // 权限码
                .setPermissionCodes(Sets.newHashSet("permission"));

        // 3 完成
        JwtToken token = login(loginUser, LoginType.PASSWORD);

        // 4 认证完成后处理
        log.info("用户{}, 认证并登录成功, 返回token：{}", loginUser.getUsername(), token.getToken());
        return token;
    }

    /**
     * 认证并获取用户
     *
     * @param username 用户名
     * @param password 密码
     * @return         系统用户
     */
    public SysUser authAndGetUser(String username, String password) {
        AssertI18nUtils.notBlank(username, ErrorCodeEnum.PARAM_ERROR);
        AssertI18nUtils.notBlank(password, ErrorCodeEnum.PARAM_ERROR);

        SysUser sysUser = sysUserService.getUserByUsername(username);
        AssertI18nUtils.nonNull(sysUser, ErrorCodeEnum.INVALID_USERNAME_OR_PASSWORD);

        // 验证密码
        String sysUserPassword = DigestUtils.sha256Hex(password + sysUser.getSalt());
        AssertI18nUtils.assertTrue(sysUserPassword.equals(sysUser.getPassword()), ErrorCodeEnum.INVALID_USERNAME_OR_PASSWORD);

        return sysUser;
    }
}
