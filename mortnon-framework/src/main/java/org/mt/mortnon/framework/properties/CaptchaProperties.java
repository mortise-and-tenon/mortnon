package org.mt.mortnon.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dongfangzan
 * @date 30.4.21 10:21 上午
 */
@Data
@Component
@ConfigurationProperties(prefix = "mortnon.captcha")
public class CaptchaProperties {

    private boolean enable = true;

    private String type = "arithmetic";

    private long expireSeconds = 600L;

    private int width = 130;

    private int height = 48;

    private int length = 5;
}
