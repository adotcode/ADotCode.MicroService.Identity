package com.adotcode.oauth2server.core.response;

import com.adotcode.oauth2server.core.enums.result.ResultCodeEnum;
import com.adotcode.oauth2server.core.util.http.RequestUtils;
import com.adotcode.oauth2server.core.util.i18n.I18nMessageUtils;
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
@NoArgsConstructor
public class HttpResult<T> {

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
   * error  details
   */
  private ErrorResult error;

  /**
   * data result
   *
   * @param data data
   */
  HttpResult(T data) {
    code = ResultCodeEnum.SUCCESS.value();
    message = I18nMessageUtils.translate(ResultCodeEnum.SUCCESS.reasonPhrase());
    this.data = data;
  }

  /**
   * data result
   *
   * @param code code
   * @param message message
   * @param data data
   * @param error errorWrapper
   */
  private HttpResult(String code, String message, T data, ErrorResult error) {
    this.code = code;
    this.message = message;
    this.data = data;
    this.error = error;
  }

  /**
   * data result
   *
   * @param code code
   * @param message message
   */
  private HttpResult(String code, String message) {
    this.code = code;
    this.message = message;
  }

  /**
   * no data result
   *
   * @param code code
   * @param message message
   * @param error errorWrapper
   */
  private HttpResult(String code, String message, ErrorResult error) {
    this.code = code;
    this.message = message;
    this.error = error;
  }

  /**
   * ok result
   *
   * @return HttpResult
   */
  public static HttpResult<Object> ok() {
    return new HttpResult<>(
        ResultCodeEnum.SUCCESS.value(),
        I18nMessageUtils.translate(ResultCodeEnum.SUCCESS.reasonPhrase()));
  }

  /**
   * ok result
   *
   * @param data data
   * @param <T> object T
   * @return HttpResult<T>
   */
  public static <T> HttpResult<T> ok(T data) {
    return new HttpResult<>(
        ResultCodeEnum.SUCCESS.value(),
        I18nMessageUtils.translate(ResultCodeEnum.SUCCESS.reasonPhrase()),
        data,
        null);
  }

  /**
   * 错误结果返回
   *
   * @return HttpResult
   */
  public static HttpResult<Object> error() {
    String message = I18nMessageUtils.translate(ResultCodeEnum.ERROR.reasonPhrase());
    ErrorResult errorResult = new ErrorResult(message);
    return new HttpResult<>(
        ResultCodeEnum.ERROR.value(),
        message,
        errorResult);
  }

  /**
   * 错误结果返回
   *
   * @return HttpResult
   */
  public static HttpResult<Object> error(ErrorResult errorResult) {
    return new HttpResult<>(
        ResultCodeEnum.ERROR.value(),
        I18nMessageUtils.translate(ResultCodeEnum.ERROR.reasonPhrase()),
        errorResult);
  }

  /**
   * 错误结果返回
   *
   * @return HttpResult
   */
  public static HttpResult<Object> error(String code, String message, ErrorResult errorResult) {
    return new HttpResult<>(code, message, errorResult);
  }

  /**
   * 错误信息
   */
  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  public static class ErrorResult {

    /**
     * 资源标识
     */
    private String uri = RequestUtils.getRequestAllUrl();

    /**
     * 错误详情
     */
    private Object message;

    private ErrorResult(Object message) {
      this.message = message;
    }

    /**
     * 获取一个实例
     *
     * @param message 错误信息
     * @return ErrorResult
     */
    public static ErrorResult newInstance(Object message) {
      return new ErrorResult(message);
    }
  }
}
