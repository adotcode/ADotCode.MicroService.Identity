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
     * Constraint Violation Code
     */
    private final static String CONSTRAINT_VIOLATION_CODE = "CONSTRAINT_VIOLATION";
    /**
     * Method Argument Not Valid  Code
     */
    private final static String METHOD_ARGUMENT_NOT_VALID_CODE = "METHOD_ARGUMENT_NOT_VALID";

    /**
     * 默认异常处理(未匹配到任何预知异常，服务器内部错误)
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
        return wrapperErrorResult(request, e);
    }

    /**
     * 用户未授权异常处理
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResultWrapper onUnauthorizedException(HttpServletRequest request, UnauthorizedException e) {
        return wrapperErrorResult(request, e);
    }

    /**
     * 无权访问异常处理
     *
     * @param request 请求体
     * @param e       异常
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
     * @param e       异常
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
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(IllegalPropertiesException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultWrapper onIllegalPropertiesException(HttpServletRequest request, IllegalPropertiesException e) {
        return wrapperErrorResult(request, e);
    }

    /**
     * 非法参数异常
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(IllegalParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultWrapper onIllegalParameterException(HttpServletRequest request, IllegalParameterException e) {
        return wrapperErrorResult(request, e);
    }

    /**
     * validation 异常处理
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ResultWrapper onConstraintViolationException(HttpServletRequest request, ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            String errorMessage = constraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(";"));
            return wrapperErrorResult(request, CONSTRAINT_VIOLATION_CODE, errorMessage, e);
        }
        return wrapperErrorResult(request, e);
    }

    /**
     * validation 异常处理
     *
     * @param request 请求体
     * @param e       异常
     * @return 返回结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ResponseBody
    public ResultWrapper resolveMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        List<ObjectError> objectErrors = e.getBindingResult().getAllErrors();
        if (!CollectionUtils.isEmpty(objectErrors)) {
            String errorMessage = objectErrors.stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(";"));
            return wrapperErrorResult(request, METHOD_ARGUMENT_NOT_VALID_CODE, errorMessage, e);
        }
        return wrapperErrorResult(request, e);
    }

    /**
     * 错误信息返回辅助
     *
     * @param request 请求体
     * @param e       Exception
     * @return ResultWrapper
     */
    private ResultWrapper wrapperErrorResult(HttpServletRequest request, Exception e) {
        return ResultWrapper.error(e);
    }

    /**
     * 错误信息返回辅助
     *
     * @param request 请求体
     * @param code    错误代码
     * @param message 错误消息
     * @param e       异常
     * @return ResultWrapper
     */
    private ResultWrapper wrapperErrorResult(HttpServletRequest request, String code, String message, Exception e) {
        return ResultWrapper.error(code, message);
    }

    /**
     * 错误信息返回辅助
     *
     * @param request 请求体
     * @param e       BaseException
     * @return ResultWrapper
     */
    private ResultWrapper wrapperErrorResult(HttpServletRequest request, BaseException e) {
        return ResultWrapper.error(e);
    }

}
