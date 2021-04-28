package org.mt.mortnon.framework.properties;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * app信息
 *
 * @author dongfangzan
 * @date 22.4.21 1:20 下午
 */
@Data
@Configuration
public class AppInfo {

    /** app 名称 */
    @Value("${mortnon.app.name}")
    private String name;

    /** app 版本 */
    @Value("${mortnon.app.project-version}")
    private String projectVersion;
}
