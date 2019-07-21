package com.adotcode.oauth2server.domain.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 未登录异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class NotLoggedInException extends Exception {

    private static final long serialVersionUID = 6467571653798792346L;
    /**
     * message
     */
    @Setter
    @Getter
    private String message;

    /**
     * default message:未登录
     */
    public NotLoggedInException() {
        setMessage("未登录.");
    }

    /**
     * custom message
     *
     * @param message message
     */
    public NotLoggedInException(String message) {
        this.message = message;
    }
}
