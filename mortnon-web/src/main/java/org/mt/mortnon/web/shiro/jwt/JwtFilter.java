package org.mt.mortnon.web.shiro.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.mt.mortnon.service.login.LoginFactory;
import org.mt.mortnon.service.login.model.JwtToken;
import org.mt.mortnon.framework.enums.ErrorCodeEnum;
import org.mt.mortnon.framework.properties.JwtProperties;
import org.mt.mortnon.framework.utils.*;
import org.mt.mortnon.framework.vo.MortnonResult;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dongfangzan
 * @date 27.4.21 2:56 下午
 */
@Slf4j
public class JwtFilter extends AuthenticatingFilter {

    private final LoginFactory loginFactory;

    private final JwtProperties jwtProperties;

    public JwtFilter(LoginFactory loginFactory, JwtProperties jwtProperties) {
        this.loginFactory = loginFactory;
        this.jwtProperties = jwtProperties;
    }

    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        String token = JwtTokenUtil.getToken();

        ShiroAssertUtil.notBlank(token, ErrorCodeEnum.UNAUTHORIZED_ACCESS);
        ShiroAssertUtil.assertTrue(!JwtUtil.isExpired(token), ErrorCodeEnum.UNAUTHORIZED_ACCESS);

        JwtToken tokenObj = loginFactory.getConfigLoginStorageService().exists(token);
        // 校验存储中是否存在token
        ShiroAssertUtil.nonNull(tokenObj, ErrorCodeEnum.USER_IDENTITY_CHECK_FAILED);

        String username = JwtUtil.getUsername(token);
        String salt = jwtProperties.getSecret();
        if (jwtProperties.isSaltCheck()) {
            salt = loginFactory.getConfigLoginStorageService().generateSalt(username);
        }

        return JwtToken.build(token, username, salt, jwtProperties.getExpireSecond(), tokenObj.getLoginType());
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(servletRequest);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(servletResponse);
        // 返回401
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // 设置响应码为401或者直接输出消息
        String url = httpServletRequest.getRequestURI();
        log.warn("onAccessDenied url：{}", url);
        MortnonResult<Boolean> result = ResultUtil.fail(ErrorCodeEnum.UNAUTHORIZED_ACCESS);
        WebUtil.printJson(httpServletResponse, result);
        return false;
    }


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        String url = WebUtils.toHttp(request).getRequestURI();
        log.debug("isAccessAllowed url:{}", url);

        if (isLoginRequest(request, response)) {
            return true;
        }

        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) { //not found any token
            log.warn("Token不能为空", e);
        } catch (Exception e) {
            log.warn("访问错误", e);
        }

        return allowed || super.isPermissive(mappedValue);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
        String url = WebUtils.toHttp(request).getRequestURI();
        log.debug("鉴权成功,token:{},url:{}", token, url);
        // 刷新token
        JwtToken jwtToken = (JwtToken) token;
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        loginFactory.getConfigLoginStorageService().refreshToken(jwtToken, httpServletResponse);
        return true;
    }

}
