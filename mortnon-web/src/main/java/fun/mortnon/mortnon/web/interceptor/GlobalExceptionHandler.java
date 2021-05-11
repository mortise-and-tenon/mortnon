package fun.mortnon.mortnon.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import fun.mortnon.mortnon.framework.constants.CharConstants;
import fun.mortnon.mortnon.framework.constants.MortnonConstants;
import fun.mortnon.mortnon.framework.enums.ErrorCodeEnum;
import fun.mortnon.mortnon.framework.exceptions.MortnonBaseException;
import fun.mortnon.mortnon.framework.exceptions.MortnonWebException;
import fun.mortnon.mortnon.framework.utils.I18nUtil;
import fun.mortnon.mortnon.framework.utils.ResultUtil;
import fun.mortnon.mortnon.framework.vo.MortnonResult;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     */
    @ExceptionHandler(value = Exception.class)
    public <T> MortnonResult<T> exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
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
     * 处理未登录的异常
     */
    @ExceptionHandler(value = MortnonWebException.class)
    public <T> MortnonResult<T> handleWebException(HttpServletResponse response, Exception e) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ErrorCodeEnum errorCodeEnum = ((MortnonWebException) e).getErrorCodeEnum();

        String msg = StringUtils.isBlank(e.getMessage()) ? errorCodeEnum.getDescription() : e.getMessage();
        return ResultUtil.fail(null, errorCodeEnum, msg);
    }

    /**
     * 无权限处理
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public MortnonResult<Void> handleForbidden(HttpServletResponse response, UnauthorizedException e) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        return ResultUtil.fail(null, ErrorCodeEnum.FORBIDDEN,
                I18nUtil.getMessage(ErrorCodeEnum.FORBIDDEN.getErrorCode()));
    }

    /**
     * 参数异常处理
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public <T> MortnonResult<T> argumentNotValidExceptionHandler(HttpServletRequest request,
                                                                 MethodArgumentNotValidException e) {
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();

        // 只展示注解上打印的参数
        String message = allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(CharConstants.SEMICOLON));
        return ResultUtil.fail(null, ErrorCodeEnum.PARAM_ERROR, I18nUtil.getMessage(message));
    }
}
