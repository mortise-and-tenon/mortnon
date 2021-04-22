package org.mt.mortnon.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.constants.MortnonConstants;
import org.mt.mortnon.constants.MortnonContextHolder;
import org.mt.mortnon.utils.CookieUtil;
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
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        setTenantId(request);

        return true;
    }

    /**
     * 设置租户
     *
     * @param request
     */
    private void setTenantId(HttpServletRequest request) {
        // 什么都没有取默认值
        String tenant = MortnonConstants.DEFAULT_TENANT_ID;

        // 在cookie中取
        String cookieTenant = CookieUtil.getCookieValue(request, MortnonConstants.TENANT_ID);
        if (StringUtils.isNoneBlank(cookieTenant)) {
            tenant = cookieTenant;
        }

        // 设置租户，在参数里取
        if (StringUtils.isNotBlank(request.getParameter(MortnonConstants.TENANT_ID))) {
            tenant = request.getParameter(MortnonConstants.TENANT_ID);
        }

        // 设置租户id
        MortnonContextHolder.setTenantId(tenant);
    }


    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        // 清除租户
        MortnonContextHolder.clear();
    }
}
