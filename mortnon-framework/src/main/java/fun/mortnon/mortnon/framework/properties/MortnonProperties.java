package fun.mortnon.mortnon.framework.properties;

import com.google.common.collect.Lists;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import fun.mortnon.mortnon.framework.constants.CharConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * mortnon 配置
 *
 * @date 2021-04-13 21:41:23
 * @author dongfangzan
 */
@Data
@Configuration
public class MortnonProperties {
    /** 静态资源访问控制器 */
    @Value("${mortnon.resource-handlers}")
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
    private String tenantBlackList;

    /**
     * 获取租户黑名单
     *
     * @return 租户黑名单表
     */
    public List<String> getTenantBlackList() {
        if (StringUtils.isBlank(tenantBlackList)) {
            return null;
        }

        return Lists.newArrayList(tenantBlackList.split(CharConstants.COMMA));
    }
}
