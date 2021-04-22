package org.mt.mortnon.framework.utils;

import org.mt.mortnon.framework.enums.ErrorCodeEnum;
import org.mt.mortnon.framework.exceptions.MortnonBaseException;

/**
 * 服务断言工具
 *
 * @author dongfangzan
 * @date 14.4.21 10:44 上午
 */
public class AssertsUtil {

    /**
     * 断言为真
     *
     * @param isSuccess     表达式
     * @param errorCodeEnum 错误码
     * @param msg           消息
     */
    public static void assertTrue(boolean isSuccess, ErrorCodeEnum errorCodeEnum, String msg) {
        if (!isSuccess) {
            throwException(errorCodeEnum, msg);
        }
    }


    /**
     * 根据错误码抛出异常
     *
     * @param errorCodeEnum 错误码
     * @param msg           错误信息
     */
    public static void throwException(ErrorCodeEnum errorCodeEnum, String msg) {
        throw new MortnonBaseException(errorCodeEnum, msg);
    }
}
