package org.mt.mortnon.utils;

import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.enums.ErrorCodeEnum;
import org.mt.mortnon.vo.MortnonResult;

/**
 * 结果工具
 *
 * @author dongfangzan
 * @date 14.4.21 10:31 上午
 */
public class ResultUtil {

    /**
     * 返回成功
     *
     * @param t   结果数据
     * @param <T> 结果类型
     * @return 结果
     */
    public static <T> MortnonResult<T> success(T t) {
        MortnonResult<T> mortnonResult = success();
        mortnonResult.setData(t);
        return mortnonResult;
    }

    /**
     * 返回成功
     *
     * @param <T>
     * @return
     */
    public static <T> MortnonResult<T> success() {
        return new MortnonResult<T>()
                .setSuccess(true)
                .setErrorCode(ErrorCodeEnum.SUCCESS.getErrorCode())
                .setMessage(ErrorCodeEnum.SUCCESS.getDescription());
    }

    /**
     * 返回失败
     *
     * @param t
     * @param errorCodeEnum
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> MortnonResult<T> fail(T t, ErrorCodeEnum errorCodeEnum, String msg) {
        return new MortnonResult<T>()
                .setSuccess(false)
                .setErrorCode(errorCodeEnum.getErrorCode())
                .setMessage(StringUtils.isBlank(msg) ? errorCodeEnum.getDescription() : msg)
                .setData(t);
    }
}
