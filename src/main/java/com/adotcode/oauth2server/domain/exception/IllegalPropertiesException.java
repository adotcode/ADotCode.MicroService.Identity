package com.adotcode.oauth2server.domain.exception;

/**
 * 非法属性异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class IllegalPropertiesException extends BaseException {

    private static final long serialVersionUID = 6467571653798792346L;
    private static final String DEFAULT_CODE = "ILLEGAL_PROPERTIES";
    private static final String DEFAULT_MESSAGE = "非法的属性.";

    /**
     * default message:非法的属性
     */
    public IllegalPropertiesException() {
        super(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    /**
     * custom message
     *
     * @param message 属性名
     */
    public IllegalPropertiesException(String message) {
        super(DEFAULT_CODE, String.format("属性[%s]非法.", message));
    }

    /**
     * custom code & message
     *
     * @param code    code
     * @param message message
     */
    public IllegalPropertiesException(String code, String message) {
        super(code, message);
    }
}
