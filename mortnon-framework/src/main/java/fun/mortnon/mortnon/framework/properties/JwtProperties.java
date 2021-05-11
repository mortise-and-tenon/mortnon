package fun.mortnon.mortnon.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT属性配置
 *
 * @author geekidea
 * @date 2019-05-22
 **/
@Data
@Component
@ConfigurationProperties(prefix = "mortnon.jwt")
public class JwtProperties {

    /**
     * token名称,默认名称为：token，可自定义
     */
    private String tokenName = "token";

    /**
     * 密码
     */
    private String secret = "666666";

    /**
     * 签发人
     */
    private String issuer;

    /**
     * 主题
     */
    private String subject;

    /**
     * 签发的目标
     */
    private String audience;

    /**
     * token失效时间,默认1小时，60*60=3600
     */
    private Long expireSecond = 60L*60L;

    /**
     * 是否刷新token，默认为true
     */
    private boolean refreshToken = true;

    /**
     * 刷新token倒计时，默认10分钟，10*60=600
     */
    private Integer refreshTokenCountdown;

    /**
     * 是否进行盐值校验
     */
    private boolean saltCheck;

    /**
     * 默认登录存储类型
     */
    private String loginStorageType;

}
