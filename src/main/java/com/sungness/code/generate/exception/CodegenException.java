package com.sungness.code.generate.exception;


/**
 * 配置异常类，当配置文件解析异常时，抛出此异常。
 * Created by wanghongwei1 on 2015/04/09.
 */
public class CodegenException extends Exception {
    private static final long serialVersionUID = 1226430826318382469L;

    public CodegenException(String message) {
        super(message);
    }

    public CodegenException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public CodegenException(Throwable throwable) {
        super(throwable);
    }
}
