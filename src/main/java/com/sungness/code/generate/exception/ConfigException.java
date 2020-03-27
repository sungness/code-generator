package com.sungness.code.generate.exception;

/**
 * 配置异常类，当配置文件解析异常时，抛出此异常。
 * Created by wanghongwei1 on 2015/04/09.
 */
public class ConfigException extends RuntimeException {

    private static final long serialVersionUID = 1887165764491303081L;

    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
