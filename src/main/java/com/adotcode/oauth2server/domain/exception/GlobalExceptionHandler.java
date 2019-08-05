package com.adotcode.oauth2server.domain.exception;

import com.adotcode.oauth2server.domain.enums.result.ResultCodeEnum;
import com.adotcode.oauth2server.domain.exception.application.BaseException;
import com.adotcode.oauth2server.domain.exception.application.ForbiddenException;
import com.adotcode.oauth2server.domain.exception.application.GenericException;
import com.adotcode.oauth2server.domain.exception.application.IllegalParameterException;
import com.adotcode.oauth2server.domain.exception.application.IllegalPropertiesException;
import com.adotcode.oauth2server.domain.exception.application.NullOrEmptyException;
import com.adotcode.oauth2server.domain.exception.application.UnAuthorizedException;
import com.adotcode.oauth2server.domain.wrapper.ResultWrapper;
import com.adotcode.oauth2server.domain.wrapper.ResultWrapper.Error;
import com.adotcode.oauth2server.util.i18n.I18nMessageUtils;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ResponseBody
  public ResultWrapper onException(HttpServletRequest request, Exception e) {
    log.error("服务器内部错误.", e);
    return wrapperErrorResult(request, e);
  }

  /**
   * 用户未授权异常处理
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(UnAuthorizedException.class)
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ResponseBody
  public ResultWrapper onUnauthorizedException(HttpServletRequest request,
      UnAuthorizedException e) {
    return wrapperErrorResult(request, e);
  }

  /**
   * 无权访问异常处理
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ForbiddenException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ResponseBody
  public ResultWrapper onForbiddenException(HttpServletRequest request, ForbiddenException e) {
    return wrapperErrorResult(request, e);
  }

  /**
   * null或空异常处理
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(NullOrEmptyException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onNullOrEmptyException(HttpServletRequest request, NullOrEmptyException e) {
    return wrapperErrorResult(request, e);
  }

  /**
   * 非法属性异常
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(IllegalPropertiesException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onIllegalPropertiesException(HttpServletRequest request,
      IllegalPropertiesException e) {
    return wrapperErrorResult(request, e);
  }

  /**
   * 非法参数异常
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(IllegalParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onIllegalParameterException(HttpServletRequest request,
      IllegalParameterException e) {
    return wrapperErrorResult(request, e);
  }

  /**
   * validation 异常处理
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ResultWrapper onConstraintViolationException(HttpServletRequest request,
      ConstraintViolationException e) {
    Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
    if (!CollectionUtils.isEmpty(constraintViolations)) {
      List<String> errorMessage = constraintViolations
          .stream()
          .map(error -> I18nMessageUtils.locale(error.getMessage()))
          .collect(Collectors.toList());
      return wrapperErrorResult(request, ResultCodeEnum.CONSTRAINT_VIOLATION, errorMessage);
    }
    return wrapperErrorResult(request, e);
  }

  /**
   * validation 异常处理
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  public ResultWrapper resolveMethodArgumentNotValidException(HttpServletRequest request,
      MethodArgumentNotValidException e) {
    List<FieldError> objectErrors = e.getBindingResult().getFieldErrors();
    if (!CollectionUtils.isEmpty(objectErrors)) {
      List<String> errorMessage = objectErrors
          .stream()
          .map(error ->
              String.format("[%s]%s",
                  error.getField(),
                  I18nMessageUtils.locale(error.getDefaultMessage(), error.getArguments())))
          .collect(Collectors.toList());
      return wrapperErrorResult(request, ResultCodeEnum.METHOD_ARGUMENT_NOT_VALID, errorMessage);
    }
    return wrapperErrorResult(request, e);
  }

  /**
   * 通用异常
   *
   * @param request 请求体
   * @param e 异常
   * @return 返回结果
   */
  @ExceptionHandler(GenericException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ResponseBody
  public ResultWrapper onGenericException(HttpServletRequest request, GenericException e) {
    return wrapperErrorResult(request, e);
  }

  /**
   * 错误信息返回辅助
   *
   * @param request 请求体
   * @param e Exception
   * @return ResultWrapper
   */
  private ResultWrapper wrapperErrorResult(HttpServletRequest request, Exception e) {
    Error error = new ResultWrapper.Error(request.getRequestURI(),
        I18nMessageUtils.locale(e.getMessage()));
    return ResultWrapper.error(error);
  }

  /**
   * 错误信息返回辅助
   *
   * @param request 请求体
   * @param code 错误代码
   * @param errorObj 错误消息描述
   * @return ResultWrapper
   */
  private ResultWrapper wrapperErrorResult(HttpServletRequest request, ResultCodeEnum code,
      Object errorObj) {
    Error error = new ResultWrapper.Error(request.getRequestURI(), errorObj);
    return ResultWrapper.error(code, error);
  }

  /**
   * 错误信息返回辅助
   *
   * @param request 请求体
   * @param e BaseException
   * @return ResultWrapper
   */
  private ResultWrapper wrapperErrorResult(HttpServletRequest request, BaseException e) {
    Error error = new ResultWrapper.Error(request.getRequestURI(),
        I18nMessageUtils.locale(e.getMessage()));
    return ResultWrapper.error(e, error);
  }

}
