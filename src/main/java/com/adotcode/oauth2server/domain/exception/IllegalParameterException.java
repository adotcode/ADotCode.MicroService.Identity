package com.adotcode.oauth2server.domain.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 非法参数异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class IllegalParameterException extends Exception {

    private static final long serialVersionUID = 6467571653798792346L;
    /**
     * message
     */
    @Setter
    @Getter
    private String message;

    /**
     * default message:非法的属性
     */
    public IllegalParameterException() {
        setMessage("非法参数.");
    }

    /**
     * custom message
     *
     * @param message 参数名
     */
    public IllegalParameterException(String message) {
        this.message = String.format("参数[%s]非法.", message);
    }
}
