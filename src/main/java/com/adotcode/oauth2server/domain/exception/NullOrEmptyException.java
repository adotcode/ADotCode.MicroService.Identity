package com.adotcode.oauth2server.domain.exception;

/**
 * Null或空异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class NullOrEmptyException extends BaseException {

    private static final long serialVersionUID = 6467571653798792346L;
    private static final String DEFAULT_CODE = "NULL_OR_EMPTY";
    private static final String DEFAULT_MESSAGE = "对象为Null或空.";

    /**
     * default message:参数为null或空
     */
    public NullOrEmptyException() {
        super(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    /**
     * custom message
     *
     * @param message message
     */
    public NullOrEmptyException(String message) {
        super(DEFAULT_CODE, message);
    }

    /**
     * custom  code & message
     *
     * @param code    code
     * @param message message
     */
    public NullOrEmptyException(String code, String message) {
        super(code, message);
    }
}
