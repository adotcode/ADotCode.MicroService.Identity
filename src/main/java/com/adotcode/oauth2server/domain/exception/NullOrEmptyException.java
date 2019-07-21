package com.adotcode.oauth2server.domain.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Null或空异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class NullOrEmptyException extends Exception {

    private static final long serialVersionUID = 6467571653798792346L;
    /**
     * message
     */
    @Setter
    @Getter
    private String message;

    /**
     * default message:参数为null或空
     */
    public NullOrEmptyException() {
        setMessage("参数为null或空.");
    }

    /**
     * custom message
     *
     * @param message message
     */
    public NullOrEmptyException(String message) {
        this.message = message;
    }
}
