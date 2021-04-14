package org.mt.mortnon.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.enums.ErrorCodeEnum;
import org.mt.mortnon.web.vo.MortnonResult;

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
        MortnonResult<T> mortnonResult = new MortnonResult<>();
        mortnonResult.setSuccess(true);
        mortnonResult.setErrorCode(ErrorCodeEnum.SUCCESS.getErrorCode());
        mortnonResult.setMessage(ErrorCodeEnum.SUCCESS.getDescription());
        mortnonResult.setData(t);

        return mortnonResult;
    }

    public static <T> MortnonResult<T> fail(T t, ErrorCodeEnum errorCodeEnum, String msg) {
        MortnonResult<T> mortnonResult = new MortnonResult<>();
        mortnonResult.setSuccess(false);
        mortnonResult.setErrorCode(errorCodeEnum.getErrorCode());
        mortnonResult.setMessage(msg);
        if (StringUtils.isBlank(msg)) {
            mortnonResult.setMessage(errorCodeEnum.getDescription());
        }
        mortnonResult.setData(t);
        return mortnonResult;
    }
}
