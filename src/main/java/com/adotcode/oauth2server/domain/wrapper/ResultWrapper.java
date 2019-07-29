package com.adotcode.oauth2server.domain.wrapper;

import com.adotcode.oauth2server.domain.enums.ResultCodeEnum;
import com.adotcode.oauth2server.domain.exception.application.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 普通结果返回包装器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultWrapper<T> {

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Error {

    /**
     * 资源标识
     */
    private String uri;

    /**
     * 错误详情
     */
    private Object message;
  }

  /**
   * result code
   */
  private String code;

  /**
   * result message
   */
  private String message;

  /**
   * time stamp millis(js中long型会造成精度损失)
   */
  private final String timestamp = String.valueOf(System.currentTimeMillis());

  /**
   * result data
   */
  private T data;

  /**
   * error details
   */
  private Error error;

  /**
   * data construction
   *
   * @param data data
   */
  ResultWrapper(T data) {
    this.code = ResultCodeEnum.SUCCESS.value();
    this.message = ResultCodeEnum.SUCCESS.getReasonPhrase();
    this.data = data;
  }

  /**
   * no data result
   *
   * @param code code
   */
  private ResultWrapper(ResultCodeEnum code) {
    this.code = code.value();
    this.message = code.getReasonPhrase();
  }

  /**
   * no data result
   *
   * @param code code
   * @param message message
   */
  private ResultWrapper(String code, String message, Error error) {
    this.code = code;
    this.message = message;
    this.error = error;
  }

  /**
   * no data result
   *
   * @param code code
   * @param message custom message
   */
  private ResultWrapper(ResultCodeEnum code, String message) {
    this.code = code.value();
    this.message = message;
  }


  /**
   * error result
   *
   * @param code ResultCodeEnum
   * @param error error
   */
  private ResultWrapper(ResultCodeEnum code, Error error) {
    this.code = code.value();
    this.message = code.getReasonPhrase();
    this.error = error;
  }

  /**
   * ok result
   *
   * @return ResultWrapper
   */
  public static ResultWrapper ok() {
    return new ResultWrapper(ResultCodeEnum.SUCCESS);
  }

  /**
   * ok result
   *
   * @param data data
   * @param <T> object T
   * @return ResultWrapper<T>
   */
  public static <T> ResultWrapper<T> ok(T data) {
    return new ResultWrapper<>(ResultCodeEnum.SUCCESS.value(),
        ResultCodeEnum.SUCCESS.getReasonPhrase(), data, null);
  }

  /**
   * 错误结果返回
   *
   * @return ResultWrapper
   */
  public static ResultWrapper error() {
    return new ResultWrapper(ResultCodeEnum.ERROR, ResultCodeEnum.ERROR.getReasonPhrase());
  }

  /**
   * 错误结果返回
   *
   * @return ResultWrapper
   */
  public static ResultWrapper error(Error error) {
    return new ResultWrapper(ResultCodeEnum.ERROR, error);
  }

  /**
   * 错误结果返回
   *
   * @return ResultWrapper
   */
  public static ResultWrapper error(ResultCodeEnum code, Error error) {
    return new ResultWrapper(code, error);
  }


  /**
   * 错误结果返回
   *
   * @param e BaseException
   * @return ResultWrapper
   */
  public static ResultWrapper error(BaseException e, Error error) {
    return new ResultWrapper(e.getCode(), e.getMessage(), error);
  }

}
