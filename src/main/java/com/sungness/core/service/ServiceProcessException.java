/**
 * Service层处理异常类，异常封装了可能存在的相关字段、key、错误提示信息
 */
package com.sungness.core.service;

/**
 * @author wanghongwei
 * @since v2.0 2014-03-19
 */
public class ServiceProcessException extends Exception {

    private static final long serialVersionUID = 4324348262759503346L;

    private String field;

    private String errorCode;

    private String defaultMessage;

    /**
     * 异常构造方法
     * @param field the field name (may be {@code null} or empty String)
     * @param errorCode error code, interpretable as a message key
     * @param defaultMessage fallback default message
     */
    public ServiceProcessException(
            String field, String errorCode, String defaultMessage) {
        super(defaultMessage);
        this.field = field;
        this.errorCode = errorCode;
        this.defaultMessage = defaultMessage;
    }

    /**
     * 异常构造方法
     * @param field the field name (may be {@code null} or empty String)
     * @param errorCode error code, interpretable as a message key
     */
    public ServiceProcessException(String field, String errorCode) {
        super("Service处理异常");
        this.field = field;
        this.errorCode = errorCode;
    }

    public ServiceProcessException(String defaultMessage, Throwable throwable) {
        super(defaultMessage, throwable);
    }

    public ServiceProcessException(String defaultMessage) {
        super(defaultMessage);
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
