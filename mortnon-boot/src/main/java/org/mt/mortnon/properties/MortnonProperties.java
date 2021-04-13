package org.mt.mortnon.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * mortnon 配置
 *
 * @date 2021-04-13 21:41:23
 * @author dongfangzan
 */
@Component
@ConfigurationProperties(prefix = "mortnon")
public class MortnonProperties {
    /** 静态资源访问控制器 */
    private String resourceHandlers;

    public String getResourceHandlers() {
        return resourceHandlers;
    }

    public void setResourceHandlers(String resourceHandlers) {
        this.resourceHandlers = resourceHandlers;
    }
}
