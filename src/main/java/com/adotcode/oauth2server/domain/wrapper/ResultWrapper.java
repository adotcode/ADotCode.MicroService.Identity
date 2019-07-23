package com.adotcode.oauth2server.domain.wrapper;

import com.adotcode.oauth2server.domain.exception.BaseException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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

    /**
     * success code
     */
    private static final String SUCCESS_CODE = HttpStatus.OK.name();

    /**
     * error code
     */
    private static final String ERROR_CODE = HttpStatus.EXPECTATION_FAILED.name();

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
     * data construction
     *
     * @param data data
     */
    ResultWrapper(T data) {
        this.code = SUCCESS_CODE;
        this.message = SUCCESS_CODE;
        this.data = data;
    }

    /**
     * no data result
     *
     * @param code    code
     * @param message message
     */
    private ResultWrapper(String code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * success result
     *
     * @return ResultWrapper
     */
    public static ResultWrapper success() {
        return new ResultWrapper(SUCCESS_CODE, SUCCESS_CODE);
    }

    /**
     * success result
     *
     * @param data data
     * @param <T>  object T
     * @return ResultWrapper<T>
     */
    public static <T> ResultWrapper<T> success(T data) {
        return new ResultWrapper<>(SUCCESS_CODE, SUCCESS_CODE, data);
    }

    /**
     * 错误结果返回
     *
     * @return ResultWrapper
     */
    public static ResultWrapper error() {
        return new ResultWrapper(ERROR_CODE, ERROR_CODE);
    }

    /**
     * 错误结果返回
     *
     * @param message error message
     * @return ResultWrapper
     */
    public static ResultWrapper error(String message) {
        return new ResultWrapper(ERROR_CODE, message);
    }

    /**
     * 错误结果返回
     *
     * @param e Exception
     * @return ResultWrapper
     */
    public static ResultWrapper error(Exception e) {
        return new ResultWrapper(ERROR_CODE, e.getMessage());
    }

    /**
     * 错误结果返回
     *
     * @param e BaseException
     * @return ResultWrapper
     */
    public static ResultWrapper error(BaseException e) {
        return new ResultWrapper(e.getCode(), e.getMessage());
    }

    /**
     * 错误结果返回
     *
     * @param code    error code
     * @param message message
     * @return ResultWrapper
     */
    public static ResultWrapper error(String code, String message) {
        return new ResultWrapper(code, message);
    }

}
