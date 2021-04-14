package org.mt.mortnon.web.vo;

import java.io.Serializable;

/**
 * web层默认返回
 *
 * @author dongfangzan
 * @date 14.4.21 10:27 上午
 */
public class MortnonResult<T> implements Serializable {

    /**
     * uid
     * @ignore
     */
    private static final long serialVersionUID = -853188663210091249L;

    /**
     * 结果
     */
    private T data;

    /**
     * 错误码
     * @mock 00000
     */
    private String errorCode;

    /**
     * 错误描述
     * @mock success
     */
    private String message;

    /**
     * 是否成功
     * @mock true
     */
    private boolean success;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}