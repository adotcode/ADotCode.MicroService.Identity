package com.adotcode.oauth2server.domain.exception.application;

import com.adotcode.oauth2server.domain.enums.ResultCodeEnum;

/**
 * 用户没有权限异常（令牌、用户名、密码错误）
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
public class UnAuthorizedException extends BaseException {

  private static final long serialVersionUID = 6467571653798792346L;

  /**
   * default message:用户未授权
   */
  public UnAuthorizedException() {
    super(ResultCodeEnum.UNAUTHORIZED);
  }

  /**
   * custom message
   *
   * @param message message
   */
  public UnAuthorizedException(String message) {
    super(ResultCodeEnum.UNAUTHORIZED, message);
  }
}
