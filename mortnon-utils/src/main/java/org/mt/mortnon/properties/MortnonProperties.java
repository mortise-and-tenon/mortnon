package org.mt.mortnon.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * mortnon 配置
 *
 * @date 2021-04-13 21:41:23
 * @author dongfangzan
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mortnon")
public class MortnonProperties {
    /** 静态资源访问控制器 */
    private String resourceHandlers;

    /** 开启多租户 */
    @Value("${mortnon.multi-tenant.enable-multi-tenant}")
    private boolean enableMultiTenant;

    /** 默认租户id */
    @Value("${mortnon.multi-tenant.default-tenant-id}")
    private String defaultTenantId;

    /** 租户数据库列名 */
    @Value("${mortnon.multi-tenant.tenant-id-column}")
    private String tenantColumnName;

    /** 租户黑名单 */
    @Value("${mortnon.multi-tenant.tenant-black-list}")
    private List<String> tenantBlackList;
}
