package org.mt.mortnon.framework.utils;

import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.framework.enums.ErrorCodeEnum;

import java.util.Objects;

/**
 * @author dongfangzan
 * @date 27.4.21 4:10 下午
 */
public class AssertI18nUtils extends AssertsUtil{

    /**
     * 断言非空
     *
     * @param obj           对象
     * @param errorCodeEnum 错误码
     */
    public static void nonNull(Object obj, ErrorCodeEnum errorCodeEnum) {
        assertTrue(Objects.nonNull(obj), errorCodeEnum, I18nUtil.getMessage(errorCodeEnum.getErrorCode()));
    }

    /**
     * 断言字符串非空
     *
     * @param str           字符串
     * @param errorCodeEnum 错误码
     */
    public static void notBlank(String str, ErrorCodeEnum errorCodeEnum) {
        assertTrue(StringUtils.isNotBlank(str), errorCodeEnum, I18nUtil.getMessage(errorCodeEnum.getErrorCode()));
    }

    /**
     * 断言为真
     *
     * @param isSuccess     表达式
     * @param errorCodeEnum 错误码
     */
    public static void assertTrue(boolean isSuccess, ErrorCodeEnum errorCodeEnum) {
        assertTrue(isSuccess, errorCodeEnum, I18nUtil.getMessage(errorCodeEnum.getErrorCode()));
    }
}
