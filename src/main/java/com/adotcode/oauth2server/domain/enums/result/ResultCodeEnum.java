package com.adotcode.oauth2server.domain.enums.result;

import org.springframework.lang.Nullable;

/**
 * API统一返回状态码枚举
 *
 * @author risfeng
 * @date 2019/07/24
 */
public enum ResultCodeEnum {

  /**
   * ok
   */
  SUCCESS("OK", "result.code.ok"),

  /**
   * errorWrapper
   */
  ERROR("ERROR", "result.code.error"),

  /**
   * 无权访问.
   */
  FORBIDDEN("FORBIDDEN", "result.code.forbidden"),

  /**
   * 用户未授权.
   */
  UNAUTHORIZED("UNAUTHORIZED", "result.code.unauthorized"),

  /**
   * 非法参数.
   */
  ILLEGAL_PARAMETER("ILLEGAL_PARAMETER", "result.code.illegalParameter"),

  /**
   * 非法的属性.
   */
  ILLEGAL_PROPERTIES("ILLEGAL_PROPERTIES", "result.code.illegalProperties"),

  /**
   * 对象为Null或空.
   */
  NULL_OR_EMPTY("NULL_OR_EMPTY", "result.code.nullOrEmpty"),

  /**
   * 约束冲突 （@Valid）
   */
  CONSTRAINT_VIOLATION("CONSTRAINT_VIOLATION", "result.code.constraintViolation"),

  /**
   * 参数验证不通过（@Valid）
   */
  METHOD_ARGUMENT_NOT_VALID("METHOD_ARGUMENT_NOT_VALID", "result.code.methodArgumentNotValid");


  /**
   * code
   */
  private final String code;

  /**
   * reason phrase
   */
  private final String reasonPhrase;

  /**
   * 构造
   *
   * @param code code
   * @param reasonPhrase reason phrase
   */
  ResultCodeEnum(String code, String reasonPhrase) {
    this.code = code;
    this.reasonPhrase = reasonPhrase;
  }

  /**
   * Return the String value of this status code.
   */
  public String value() {
    return this.code;
  }

  /**
   * Return the reason phrase of this status code.
   */
  public String reasonPhrase() {
    return this.reasonPhrase;
  }

  /**
   * Return the enum constant of this type with the specified string value.
   *
   * @param code the string value of the enum to be returned
   * @return the enum constant with the specified string value
   * @throws IllegalArgumentException if this enum has no constant for the specified string value
   */
  public static ResultCodeEnum of(String code) {
    ResultCodeEnum resultCodeEnum = resolve(code);
    if (resultCodeEnum == null) {
      throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
    return resultCodeEnum;
  }

  /**
   * Resolve the given code to an {@code ResultCodeEnum}, if possible.
   *
   * @param code the code (potentially non-standard)
   * @return the corresponding {@code ResultCodeEnum}, or {@code null} if not found
   */
  @Nullable
  public static ResultCodeEnum resolve(String code) {
    for (ResultCodeEnum resultCodeEnum : values()) {
      if (resultCodeEnum.value().equals(code)) {
        return resultCodeEnum;
      }
    }
    return null;
  }

  /**
   * Return a string representation of this status code.
   */
  @Override
  public String toString() {
    return this.code + " " + name();
  }
}
