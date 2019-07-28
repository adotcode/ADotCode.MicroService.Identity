package com.adotcode.oauth2server.domain.enums;

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
  SUCCESS("OK", "Success"),

  /**
   * error
   */
  ERROR("ERROR", "Error"),

  /**
   * 无权访问.
   */
  FORBIDDEN("FORBIDDEN", "无权访问."),

  /**
   * 用户未授权.
   */
  UNAUTHORIZED("UNAUTHORIZED", "用户未授权."),

  /**
   * 非法参数.
   */
  ILLEGAL_PARAMETER("ILLEGAL_PARAMETER", "非法参数."),

  /**
   * 非法的属性.
   */
  ILLEGAL_PROPERTIES("ILLEGAL_PROPERTIES", "非法的属性."),

  /**
   * 对象为Null或空.
   */
  NULL_OR_EMPTY("NULL_OR_EMPTY", "对象为Null或空."),

  /**
   * 约束冲突 （@Valid）
   */
  CONSTRAINT_VIOLATION("CONSTRAINT_VIOLATION", "约束冲突."),

  /**
   * 参数验证不通过（@Valid）
   */
  METHOD_ARGUMENT_NOT_VALID("METHOD_ARGUMENT_NOT_VALID", "参数验证不通过.");


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
  public String getReasonPhrase() {
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
