package com.adotcode.oauth2server.domain.exception;

/**
 * 禁止访问异常处理
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-22
 */
public class ForbiddenException extends BaseException {
    private static final long serialVersionUID = -7472953075828927558L;
    private static final String DEFAULT_CODE = "FORBIDDEN";
    private static final String DEFAULT_MESSAGE = "无权访问.";

    /**
     * default message:无权访问
     */
    public ForbiddenException() {
        super(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    /**
     * custom message
     *
     * @param message message
     */
    public ForbiddenException(String message) {
        super(DEFAULT_CODE, message);
    }


    /**
     * custom code & message
     *
     * @param code    code
     * @param message message
     */
    public ForbiddenException(String code, String message) {
        super(DEFAULT_CODE, message);
    }
}
