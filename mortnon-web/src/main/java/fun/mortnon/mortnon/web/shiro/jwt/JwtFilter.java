package fun.mortnon.mortnon.web.shiro.jwt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import fun.mortnon.mortnon.service.login.LoginFactory;
import fun.mortnon.mortnon.service.login.LoginStorageService;
import fun.mortnon.mortnon.service.login.enums.LoginConstants;
import fun.mortnon.mortnon.service.login.model.JwtToken;
import fun.mortnon.mortnon.framework.enums.ErrorCodeEnum;
import fun.mortnon.mortnon.framework.properties.JwtProperties;
import fun.mortnon.mortnon.framework.utils.*;
import fun.mortnon.mortnon.framework.vo.MortnonResult;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.util.Date;

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
            salt = loginFactory.getConfigLoginStorageService().getSaltFromCache(username);
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
        refreshToken(jwtToken, httpServletResponse);
        return true;
    }

    private void refreshToken(JwtToken jwtToken, HttpServletResponse response) throws Exception {
        if (!needToRefreshToken(jwtToken)) {
            return;
        }

        LoginStorageService loginStorageService = loginFactory.getConfigLoginStorageService();
        JwtToken exists = loginStorageService.exists(jwtToken.getToken());
        if (null == exists) {
            response.setStatus(LoginConstants.SC_INVALID_TOKEN);
            MortnonResult<Boolean> result = ResultUtil.fail(ErrorCodeEnum.TOKEN_EXPIRE);
            WebUtil.printJson(response, result);
            return;
        }

        String username = jwtToken.getUsername();
        String salt = jwtToken.getSalt();
        Long expireSecond = jwtProperties.getExpireSecond();
        // 生成新token字符串
        String newToken = JwtUtil.generateToken(username, salt, Duration.ofSeconds(expireSecond));
        // 生成新JwtToken对象
        JwtToken newJwtToken = JwtToken.build(newToken, username, salt, expireSecond, exists.getLoginType());
        loginStorageService.refreshToken(exists.getToken(), username, newJwtToken);

        response.setHeader(jwtToken.getToken(), newToken);
        response.setStatus(LoginConstants.SC_JWT_REFRESH_TOKEN);
    }

    private boolean needToRefreshToken(JwtToken jwtToken) {
        if (null == jwtToken) {
            return false;
        }

        String token = jwtToken.getToken();
        if (StringUtils.isBlank(token)) {
            return false;
        }

        // 判断是否刷新token
        boolean isRefreshToken = jwtProperties.isRefreshToken();
        if (!isRefreshToken) {
            return false;
        }

        // 获取过期时间
        Date expireDate = JwtUtil.getExpireDate(token);
        // 获取倒计时
        Integer countdown = jwtProperties.getRefreshTokenCountdown();
        // 如果(当前时间+倒计时) > 过期时间，则刷新token
        boolean refresh = DateUtils.addSeconds(new Date(), countdown).after(expireDate);

        if (!refresh) {
            return false;
        }

        return true;
    }

}
