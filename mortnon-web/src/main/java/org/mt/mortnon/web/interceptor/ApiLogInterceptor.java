package org.mt.mortnon.web.interceptor;

import io.github.ljwlgl.util.NetworkUtil;
import lombok.extern.slf4j.Slf4j;
import org.mt.mortnon.constants.MortnonConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static org.mt.mortnon.constants.CharConstants.FALSE_TAG;
import static org.mt.mortnon.constants.CharConstants.TRUE_TAG;
import static org.springframework.http.HttpHeaders.USER_AGENT;

/**
 * @author dongfangzan
 * @date 14.4.21 1:38 下午
 */
@Component
@Slf4j
public class ApiLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        long currentTime = System.currentTimeMillis();
        request.setAttribute(MortnonConstants.LOGGER_SEND_TIME, currentTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        long costTime = System.currentTimeMillis() - (long) request.getAttribute(MortnonConstants.LOGGER_SEND_TIME);
        String success = Objects.nonNull(request.getAttribute(MortnonConstants.EXCEPTION_TAG)) ? FALSE_TAG : TRUE_TAG;
        String host = request.getRemoteHost();
        String ip = request.getRemoteAddr();
        String localIp = NetworkUtil.getLocalHostAddress();
        String localhost = NetworkUtil.getLocalHostName();
        String userAgent = request.getHeader(USER_AGENT);

        log.info("{},{},{}ms,{},{},{},{},{},{}",
                request.getMethod(), request.getRequestURI(), costTime, success, host, ip, localIp, localhost, userAgent);
    }
}
