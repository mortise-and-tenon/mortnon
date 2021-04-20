package org.mt.mortnon.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @author dongfangzan
 * @date 14.4.21 1:38 下午
 */
@Component
@Slf4j
public class ApiLogInterceptor implements HandlerInterceptor {

    /** 请求开始时间标识 */
    private static final String LOGGER_SEND_TIME="send_time";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long currentTime = System.currentTimeMillis();
        request.setAttribute(LOGGER_SEND_TIME, currentTime);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long costTime = System.currentTimeMillis() - (long) request.getAttribute(LOGGER_SEND_TIME);
        String success = Objects.nonNull(request.getAttribute(GlobalExceptionHandler.EXCEPTION_TAG))? "F" : "T";
        String host = request.getRemoteHost();
        String ip = request.getRemoteAddr();
        String localIp = request.getLocalAddr();
        String userAgent = request.getHeader("User-Agent");

        log.info("{},{},{}ms,{},{},{},{},{}",request.getMethod(), request.getRequestURI(), costTime, success, host, ip, localIp, userAgent);
    }
}
