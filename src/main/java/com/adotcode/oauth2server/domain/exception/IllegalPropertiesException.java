package com.adotcode.oauth2server.domain.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 非法属性异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class IllegalPropertiesException extends Exception {

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
    public IllegalPropertiesException() {
        setMessage("非法的属性.");
    }

    /**
     * custom message
     *
     * @param message 属性名
     */
    public IllegalPropertiesException(String message) {
        this.message = String.format("属性[%s]非法.", message);
    }
}
