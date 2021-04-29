package org.mt.mortnon.service.login.impl;

import lombok.Setter;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.mt.mortnon.framework.properties.JwtProperties;
import org.mt.mortnon.framework.properties.ShiroProperties;
import org.mt.mortnon.framework.utils.JwtUtil;
import org.mt.mortnon.framework.utils.SaltUtil;
import org.mt.mortnon.service.login.LoginFactory;
import org.mt.mortnon.service.login.LoginService;
import org.mt.mortnon.service.login.LoginStorageService;
import org.mt.mortnon.service.login.enums.LoginType;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.service.login.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

/**
 * 用于给子类继承，子类只关心认证过程即可
 *
 * @author dongfangzan
 * @date 27.4.21 5:24 下午
 */
@Setter
public abstract class AbstractLoginServiceImpl implements LoginService {

    @Autowired
    protected JwtProperties jwtProperties;

    @Autowired
    protected ShiroProperties shiroProperties;

    @Autowired
    protected LoginFactory loginFactory;

    @Override
    public JwtToken login(LoginUser authorizedUser, LoginType loginType) {
        // 写入存储
        // 获取当前系统配置的存储
        LoginStorageService loginStorageService = loginFactory.getConfigLoginStorageService();

        // 生成盐
        String salt = SaltUtil.getSalt(authorizedUser.getSalt(), jwtProperties);

        // 生成token
        String token = JwtUtil.generateToken(authorizedUser.getUsername(),
                salt, Duration.ofSeconds(jwtProperties.getExpireSecond()));

        // 生成jwtToken
        JwtToken jwtToken = JwtToken.build(token, authorizedUser.getUsername(),
                salt, jwtProperties.getExpireSecond(), loginType);

        // 判断shiro是否启动
        if (shiroProperties.isEnable()) {
            Subject subject = SecurityUtils.getSubject();
            subject.login(jwtToken);
        }

        // 保存token到存储中
        loginStorageService.saveToken(authorizedUser, jwtToken);

        return JwtToken.build(token, authorizedUser.getUsername(), salt, jwtProperties.getExpireSecond(), loginType);
    }
}
