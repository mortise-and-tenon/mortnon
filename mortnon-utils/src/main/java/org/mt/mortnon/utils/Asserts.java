package org.mt.mortnon.utils;

import org.mt.mortnon.enums.ErrorCodeEnum;
import org.mt.mortnon.exceptions.MortnonBaseException;

/**
 * 服务断言工具
 *
 * @author dongfangzan
 * @date 14.4.21 10:44 上午
 */
public class Asserts {

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


    public static void throwException(ErrorCodeEnum errorCodeEnum, String msg) {
        throw new MortnonBaseException(errorCodeEnum, msg);
    }
}
