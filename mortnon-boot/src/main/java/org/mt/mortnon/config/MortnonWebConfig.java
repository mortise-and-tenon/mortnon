package org.mt.mortnon.config;

import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.properties.MortnonProperties;
import org.mt.mortnon.utils.IniUtil;
import org.mt.mortnon.web.interceptor.ApiLogInterceptor;
import org.mt.mortnon.web.interceptor.TenantInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Map;

/**
 * mortnon 配置
 *
 * @author dongfangzan
 * @date 2021-04-13 21:41:23
 */
@Configuration
public class MortnonWebConfig implements WebMvcConfigurer {

    /** 基础配置 */
    @Autowired
    private MortnonProperties mortnonProperties;

    /** api层日志拦截器 */
    @Autowired
    private ApiLogInterceptor apiLogInterceptor;

    /** 多租户拦截器 */
    @Autowired
    private TenantInterceptor tenantInterceptor;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 设置项目静态资源访问
        String resourceHandlers = mortnonProperties.getResourceHandlers();
        if (StringUtils.isNotBlank(resourceHandlers)) {
            Map<String, String> map = IniUtil.parseIni(resourceHandlers);
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String pathPatterns = entry.getKey();
                String resourceLocations = entry.getValue();
                registry.addResourceHandler(pathPatterns)
                        .addResourceLocations(resourceLocations);
            }
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 日志拦截器
        registry.addInterceptor(apiLogInterceptor);

        if (mortnonProperties.isEnableMultiTenant()) {
            // 租户拦截器
            registry.addInterceptor(tenantInterceptor);
        }
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 跨域设置
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
}
