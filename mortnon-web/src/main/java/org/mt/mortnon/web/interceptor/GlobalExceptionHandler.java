package org.mt.mortnon.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mt.mortnon.constants.CharConstants;
import org.mt.mortnon.constants.MortnonConstants;
import org.mt.mortnon.enums.ErrorCodeEnum;
import org.mt.mortnon.exceptions.MortnonBaseException;
import org.mt.mortnon.utils.ResultUtil;
import org.mt.mortnon.vo.MortnonResult;
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
@Slf4j
@ResponseBody
@RestControllerAdvice
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
        // 将错误信息保存到上下文
        request.setAttribute(MortnonConstants.EXCEPTION_TAG, e);

        // 打印错误异常
        log.error("error:", e);

        // 封装错误码
        ErrorCodeEnum errorCodeEnum = ErrorCodeEnum.SYSTEM_ERROR;
        if (e instanceof MortnonBaseException) {
            errorCodeEnum = ((MortnonBaseException) e).getErrorCodeEnum();
        }

        // 返回错误消息
        String msg = StringUtils.isBlank(e.getMessage()) ? errorCodeEnum.getDescription() : e.getMessage();
        return ResultUtil.fail(null, errorCodeEnum, msg);
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
                .collect(Collectors.joining(CharConstants.SEMICOLON));
        return ResultUtil.fail(null, ErrorCodeEnum.PARAM_ERROR, message);
    }
}
