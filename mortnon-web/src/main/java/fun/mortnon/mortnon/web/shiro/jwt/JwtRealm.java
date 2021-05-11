package fun.mortnon.mortnon.web.shiro.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import fun.mortnon.mortnon.framework.enums.ErrorCodeEnum;
import fun.mortnon.mortnon.framework.properties.JwtProperties;
import fun.mortnon.mortnon.framework.utils.ShiroAssertUtil;
import fun.mortnon.mortnon.service.login.LoginFactory;
import fun.mortnon.mortnon.service.login.LoginStorageService;
import fun.mortnon.mortnon.service.login.model.JwtToken;
import fun.mortnon.mortnon.service.login.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

/**
 * shiro 授权认证
 *
 * @author dongfangzan
 * @date 27.4.21 2:57 下午
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Autowired
    private LoginFactory loginFactory;

    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public boolean supports(AuthenticationToken token) {
        return Objects.nonNull(token) && token instanceof JwtToken;
    }

    /**
     * 授权认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.debug("doGetAuthorizationInfo principalCollection...");
        // 获取token
        JwtToken jwtToken = (JwtToken) principalCollection.getPrimaryPrincipal();
        String username = jwtToken.getUsername();

        // 根据登录方式获取登录服务
        LoginStorageService loginStorageService = loginFactory.getConfigLoginStorageService();
        LoginUser loginUser = loginFactory.getConfigLoginStorageService().getLoginUserByName(username);

        // 构建权限信息
        return loginStorageService.buildAuthorizationInfo(loginUser);
    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.debug("doGetAuthenticationInfo authenticationToken...");

        JwtToken jwtToken = (JwtToken) authenticationToken;

        ShiroAssertUtil.nonNull(jwtToken, ErrorCodeEnum.USER_IDENTITY_CHECK_FAILED);

        if (jwtProperties.isSaltCheck()) {
            // 需要校验salt时才校验
            ShiroAssertUtil.nonNull(jwtToken.getSalt(), ErrorCodeEnum.USER_IDENTITY_CHECK_FAILED);
        }

        return new SimpleAuthenticationInfo(jwtToken, jwtToken.getSalt(), getName());
    }
}
