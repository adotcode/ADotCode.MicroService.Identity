package com.adotcode.oauth2server.domain.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 列表结果返回包装器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-14
 */
@NoArgsConstructor
public class ListResultWrapper<T> extends ResultWrapper<ListResultWrapper.ListResult<T>> {

    /**
     * 列表返回
     *
     * @param <T> entity type
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResult<T> {
        /**
         * 列表数据集
         */
        private List<T> list;
    }

    /**
     * 成功返回
     *
     * @param data 列表数据
     */
    public ListResultWrapper(List<T> data) {
        super(new ListResultWrapper.ListResult<>(data));
    }

    /***
     * custom result code & message
     * @param code code
     * @param message message
     * @param data list data
     */
    public ListResultWrapper(String code, String message, List<T> data) {
        super(code, message, new ListResultWrapper.ListResult<>(data));
    }

}
