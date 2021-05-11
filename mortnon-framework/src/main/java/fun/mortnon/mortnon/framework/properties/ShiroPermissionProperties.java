package fun.mortnon.mortnon.framework.properties;

import lombok.Data;

/**
 * @author dongfangzan
 * @date 26.4.21 2:38 下午
 */
@Data
public class ShiroPermissionProperties {
    /**
     * 路径
     */
    private String url;
    /**
     * 路径数组
     */
    private String[] urls;

    /**
     * 权限
     */
    private String permission;
}
