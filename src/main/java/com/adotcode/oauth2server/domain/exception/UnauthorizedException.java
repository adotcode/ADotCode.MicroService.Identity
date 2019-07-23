package com.adotcode.oauth2server.domain.exception;

/**
 * 用户没有权限异常（令牌、用户名、密码错误）
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class UnauthorizedException extends BaseException {

    private static final long serialVersionUID = 6467571653798792346L;
    private static final String DEFAULT_CODE = "UNAUTHORIZED";
    private static final String DEFAULT_MESSAGE = "用户未授权.";

    /**
     * default message:用户未授权
     */
    public UnauthorizedException() {
        super(DEFAULT_CODE, DEFAULT_MESSAGE);
    }

    /**
     * custom message
     *
     * @param message message
     */
    public UnauthorizedException(String message) {
        super(DEFAULT_CODE, message);
    }

    /**
     * custom code & message
     *
     * @param code    code
     * @param message message
     */
    public UnauthorizedException(String code, String message) {
        super(code, message);
    }
}
