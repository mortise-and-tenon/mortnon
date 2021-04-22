package org.mt.mortnon.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

/**
 * 错误码
 *
 * @author dongfangzan
 * @date 14.4.21 9:59 上午
 */
public enum ErrorCodeEnum {

    /**
     * 成功
     */
    SUCCESS("00000", "success"),

    /**
     * 用户端错误
     */
    USER_ERROR("A0001", "user end error"),

    /**
     * 参数异常
     */
    PARAM_ERROR("A0002", "param error"),

    /**
     * 用户注册错误
     */
    USER_REGISTER_ERROR("A0100", "user register error"),

    /**
     * 用户未统一隐私协议
     */
    DID_NOT_AGREE_PRIVACY_AGREEMENT("A0101", "user did not agree to privacy agreement"),

    /**
     * 用户名校验失败
     */
    USER_NAME_CHECK_FAILED("A0110", "username check failed"),

    /**
     * 用户名已存在
     */
    USERNAME_ALREADY_EXISTS("A0111", "username already exists"),

    /**
     * 系统异常
     */
    SYSTEM_ERROR("B0001", "system error"),
    ;

    ErrorCodeEnum(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    /**
     * 错误码
     */
    @JsonValue
    private final String errorCode;

    /**
     * 描述信息
     */
    private final String description;

    /**
     * 获取错误码
     *
     * @return 如A0001
     */
    public String getErrorCode() {
        return this.errorCode;
    }

    /**
     * 根据错误码获取枚举
     *
     * @param errorCode 错误码，如A0001
     * @return 枚举
     */
    public static ErrorCodeEnum getByErrorCode(String errorCode) {

        if (StringUtils.isBlank(errorCode)) {
            return null;
        }

        return Arrays.stream(ErrorCodeEnum.values())
                .filter(errorCodeEnum -> errorCode.equals(errorCodeEnum.errorCode))
                .findFirst().orElse(null);
    }

    public String getDescription() {
        return description;
    }
}
