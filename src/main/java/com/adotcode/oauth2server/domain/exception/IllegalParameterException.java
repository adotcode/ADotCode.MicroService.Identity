package com.adotcode.oauth2server.domain.exception;

/**
 * 非法参数异常
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class IllegalParameterException extends BaseException {

    private static final long serialVersionUID = 6467571653798792346L;
    private static final String DEFAULT_CODE = "ILLEGAL_PARAMETER";
    private static final String DEFAULT_MESSAGE = "非法参数.";

    /**
     * default message:非法的属性
     */
    public IllegalParameterException() {
        super(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    /**
     * custom message
     *
     * @param message 参数名
     */
    public IllegalParameterException(String message) {
        super(DEFAULT_CODE, String.format("参数[%s]非法.", message));
    }

    /**
     * custom code & message
     *
     * @param code    code
     * @param message message
     */
    public IllegalParameterException(String code, String message) {
        super(code, message);
    }
}
