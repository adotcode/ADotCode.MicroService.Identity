package com.adotcode.oauth2server.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 基础异常抽象类,继承RuntimeException不受检查异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-23
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseException extends RuntimeException {

    private static final long serialVersionUID = 9053394735482735363L;

    /**
     * exception code
     */
    private String code;

    /**
     * exception message
     */
    private String message;

    /**
     * time stamp millis(js中long型会造成精度损失)
     */
    private final String timestamp = String.valueOf(System.currentTimeMillis());

    /**
     * 入参 构造
     *
     * @param code    exception code
     * @param message exception message
     */
    protected BaseException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 入参 构造
     *
     * @param code    exception code
     * @param message exception message
     * @param e       exception
     */
    protected BaseException(String code, String message, Exception e) {
        super(e);
        this.code = code;
        this.message = message;
    }
}
