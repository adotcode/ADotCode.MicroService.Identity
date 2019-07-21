package com.adotcode.oauth2server.domain.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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
@Builder
public class ResultWrapper<T> {
    /**
     * result code
     */
    private String code;
    /**
     * result message
     */
    private String message;
    /**
     * result data
     */
    private T data;

    /**
     * success result no data
     */
    public ResultWrapper() {
        this.code = HttpStatus.OK.name();
        this.message = HttpStatus.OK.getReasonPhrase();
    }

    /**
     * success result
     *
     * @param data object data
     */
    public ResultWrapper(T data) {
        this.code = HttpStatus.OK.name();
        this.message = HttpStatus.OK.getReasonPhrase();
        this.data = data;
    }

    /**
     * no data result
     *
     * @param code    code
     * @param message message
     */
    public ResultWrapper(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
