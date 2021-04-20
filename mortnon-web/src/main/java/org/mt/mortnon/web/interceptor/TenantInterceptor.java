package org.mt.mortnon.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.constants.MortnonConstants;
import org.mt.mortnon.constants.MortnonContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dongfangzan
 * @date 20.4.21 3:03 下午
 */
@Slf4j
@Component
public class TenantInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置租户
        String tenant = request.getParameter(MortnonConstants.TENANT_ID);
        if (StringUtils.isBlank(tenant)) {
            tenant = MortnonConstants.DEFAULT_TENANT_ID;
        }
        MortnonContextHolder.setTenantId(tenant);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 清除租户
        MortnonContextHolder.clear();
    }
}
