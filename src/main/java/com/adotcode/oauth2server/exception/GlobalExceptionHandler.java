package com.adotcode.oauth2server.exception;

import com.adotcode.oauth2server.common.enums.result.ResultCodeEnum;
import com.adotcode.oauth2server.common.util.i18n.I18nMessageUtils;
import com.adotcode.oauth2server.exception.application.BaseException;
import com.adotcode.oauth2server.exception.application.ForbiddenException;
import com.adotcode.oauth2server.exception.application.GenericException;
import com.adotcode.oauth2server.exception.application.IllegalParameterException;
import com.adotcode.oauth2server.exception.application.IllegalPropertiesException;
import com.adotcode.oauth2server.exception.application.NullOrEmptyException;
import com.adotcode.oauth2server.exception.application.ParseFailedException;
import com.adotcode.oauth2server.exception.application.UnAuthorizedException;
import com.adotcode.oauth2server.model.wrapper.ResultWrapper;
import com.adotcode.oauth2server.model.wrapper.ResultWrapper.ErrorWrapper;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常统一处理器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-21
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  /**
   * 默认异常处理(未匹配到任何预知异常，服务器内部错误)
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ResultWrapper onException(Exception e) {
    log.error("Internal Server Error.", e);
    return wrapperErrorResult(e);
  }

  /**
   * 用户未授权异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(UnAuthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ResultWrapper onUnauthorizedException(UnAuthorizedException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 无权访问异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public ResultWrapper onForbiddenException(ForbiddenException e) {
    return wrapperErrorResult(e);
  }

  /**
   * null或空异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(NullOrEmptyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onNullOrEmptyException(NullOrEmptyException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 非法属性异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(IllegalPropertiesException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onIllegalPropertiesException(IllegalPropertiesException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 非法参数异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(IllegalParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onIllegalParameterException(IllegalParameterException e) {
    return wrapperErrorResult(e);
  }

  /**
   * validation 异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ResultWrapper onConstraintViolationException(ConstraintViolationException e) {
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    if (!CollectionUtils.isEmpty(constraintViolations)) {
      List<String> errorMessage = constraintViolations
          .stream()
          .map(error -> I18nMessageUtils.translate(error.getMessage()))
          .collect(Collectors.toList());
      return wrapperErrorResult(ResultCodeEnum.CONSTRAINT_VIOLATION, errorMessage);
    }
    return wrapperErrorResult(e);
  }

  /**
   * validation 异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ResultWrapper onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    List<FieldError> objectErrors = e.getBindingResult().getFieldErrors();
    if (!CollectionUtils.isEmpty(objectErrors)) {
      List<String> errorMessage = objectErrors
          .stream()
          .map(error -> String.format("[%s]%s", error.getField(),
              I18nMessageUtils.translate(error.getDefaultMessage(), error.getArguments())))
          .collect(Collectors.toList());
      return wrapperErrorResult(ResultCodeEnum.METHOD_ARGUMENT_NOT_VALID, errorMessage);
    }
    return wrapperErrorResult(e);
  }


  /**
   * Request Parameter 异常处理
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ResultWrapper onMissingServletRequestParameterException(
      MissingServletRequestParameterException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 通用异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(GenericException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onGenericException(GenericException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 转失败异常
   *
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ParseFailedException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onParseFailedException(ParseFailedException e) {
    return wrapperErrorResult(e);
  }

  /**
   * 错误信息返回辅助
   *
   * @param e Exception
   * @return ResultWrapper
   */
  private ResultWrapper wrapperErrorResult(Exception e) {
    if (e instanceof MissingServletRequestParameterException) {
      MissingServletRequestParameterException missingServletRequestParameterException =
          (MissingServletRequestParameterException) e;
      String message = I18nMessageUtils.translate("exception.parameter.required.not.present",
          missingServletRequestParameterException.getParameterName());
      return ResultWrapper.error(ErrorWrapper.newInstance(message));
    }
    return ResultWrapper
        .error(ErrorWrapper.newInstance(I18nMessageUtils.translate(e.getMessage())));
  }

  /**
   * 错误信息返回辅助
   *
   * @param code 错误代码
   * @param errorObj 错误消息描述
   * @return ResultWrapper
   */
  private ResultWrapper wrapperErrorResult(ResultCodeEnum code, Object errorObj) {
    return ResultWrapper.error(code.value(), I18nMessageUtils.translate(code.reasonPhrase()),
        ErrorWrapper.newInstance(errorObj));
  }

  /**
   * 错误信息返回辅助
   *
   * @param e BaseException
   * @return ResultWrapper
   */
  private ResultWrapper wrapperErrorResult(BaseException e) {
    return ResultWrapper.error(e.getCode(), I18nMessageUtils.translate(e.getMessage()),
        ErrorWrapper.newInstance(I18nMessageUtils.translate(e.getMessage())));
  }
}
