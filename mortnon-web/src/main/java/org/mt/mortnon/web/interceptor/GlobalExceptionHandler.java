package org.mt.mortnon.web.interceptor;

import org.mt.mortnon.enums.ErrorCodeEnum;
import org.mt.mortnon.exceptions.MortnonBaseException;
import org.mt.mortnon.web.utils.ResultUtil;
import org.mt.mortnon.web.vo.MortnonResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * web层统一异常拦截器
 *
 * @author dongfangzan
 * @date 14.4.21 11:06 上午
 */
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 统一异常处理
     *
     * @param request
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public <T> MortnonResult<T> exceptionHandler(HttpServletRequest request, Exception e) {
        // 打印错误异常 TODO

        // 封装错误码
        ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.SYSTEM_ERROR;

        if (e instanceof MortnonBaseException) {
            errorCodeEnum = ((MortnonBaseException) e).getErrorCodeEnum();
        }
        return ResultUtil.fail(null, errorCodeEnum, errorCodeEnum.getDescription());
    }

    /**
     * 参数异常处理
     *
     * @param request
     * @param e
     * @param <T>
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public <T> MortnonResult<T> argumentNotValidExceptionHandler(HttpServletRequest request,
                                                                 MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        // 只展示注解上打印的参数
        String message = allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(";"));
        return ResultUtil.fail(null, ErrorCodeEnum.PARAM_ERROR, message);
    }
}
