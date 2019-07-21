package com.adotcode.oauth2server.domain.exception;

import com.adotcode.oauth2server.domain.wrapper.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
     * 默认异常处理：内部错误
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ResultWrapper onException(HttpServletRequest request, Exception e) {
        log.error("服务器内部错误.", e);
        return wrapperErrorResult(request, e.getMessage(), e);
    }

    /**
     * 非法连接异常处理
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(IllegalAccessException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultWrapper onNotLoggedInException(HttpServletRequest request, IllegalAccessException e) {
        return wrapperErrorResult(request, e.getMessage(), e);
    }

    /**
     * null或空异常处理
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(NullOrEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultWrapper onNullOrEmptyException(HttpServletRequest request, NullOrEmptyException e) {
        return wrapperErrorResult(request, e.getMessage(), e);
    }

    /**
     * 非法属性异常
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(IllegalPropertiesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultWrapper onIllegalPropertiesException(HttpServletRequest request, IllegalPropertiesException e) {
        return wrapperErrorResult(request, e.getMessage(), e);
    }

    /**
     * validation 异常处理
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultWrapper onConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            String errorMessage = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(";"));
            return wrapperErrorResult(request, errorMessage, e);
        }
        return wrapperErrorResult(request, e.getMessage(), e);
    }

    /**
     * validation 异常处理
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultWrapper resolveMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            String errorMessage = objectErrors.stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            return wrapperErrorResult(request, errorMessage, e);
        }
        return wrapperErrorResult(request, e.getMessage(), e);
    }

    private ResultWrapper wrapperErrorResult(HttpServletRequest request, String message, Exception e) {
        return new ResultWrapper("ERROR", message);
    }
}
