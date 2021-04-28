package org.mt.mortnon.framework.utils;

import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.framework.enums.ErrorCodeEnum;
import org.mt.mortnon.framework.exceptions.MortnonWebException;

import java.util.Objects;

/**
 * @author dongfangzan
 * @date 27.4.21 4:51 下午
 */
public class ShiroAssertUtil{

    /**
     * 断言非空
     *
     * @param obj           对象
     * @param errorCodeEnum 错误码
     */
    public static void nonNull(Object obj, ErrorCodeEnum errorCodeEnum) {
        assertTrue(Objects.nonNull(obj), errorCodeEnum);
    }

    /**
     * 断言字符串非空
     *
     * @param str           字符串
     * @param errorCodeEnum 错误码
     */
    public static void notBlank(String str, ErrorCodeEnum errorCodeEnum) {
        assertTrue(StringUtils.isNotBlank(str), errorCodeEnum);
    }

    /**
     * 断言为真
     *
     * @param isSuccess     表达式
     * @param errorCodeEnum 错误码
     */
    public static void assertTrue(boolean isSuccess, ErrorCodeEnum errorCodeEnum) {
        if (!isSuccess) {
            throwException(errorCodeEnum, I18nUtil.getMessage(errorCodeEnum.getErrorCode()));
        }
    }

    /**
     * 根据错误码抛出异常
     * @param msg           错误信息
     */
    public static void throwException(ErrorCodeEnum errorCodeEnum, String msg) {
        throw new MortnonWebException(errorCodeEnum, msg);
    }
}
