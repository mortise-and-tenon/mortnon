package org.mt.mortnon.service.login.enums;

/**
 * @author dongfangzan
 * @date 28.4.21 10:43 上午
 */
public class LoginConstants {

    public static final String REDIS = "redis";

    public static final String LOCAL = "local";

    public static final String LOGIN_TYPE_PASSWORD = "password";

    /**
     * 登录用户token信息key
     * login:token:tokenMd5
     */
    public static final String LOGIN_TOKEN = "login:token:%s";

    /**
     * 登录用户信息key
     * login:user:username
     */
    public static final String LOGIN_USER = "login:user:%s";

    /**
     * 登录用户盐值信息key
     * login:salt:username
     */
    public static final String LOGIN_SALT= "login:salt:%s";
}
