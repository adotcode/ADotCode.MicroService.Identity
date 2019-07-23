package com.adotcode.oauth2server.domain.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 滚动分页加载结果返回包装器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@NoArgsConstructor
public class ScrollingResultWrapper<T> extends ResultWrapper<ScrollingResultWrapper.ScrollingResult<T>> {

    /**
     * 默认返回量
     */
    private static final long DEFAULT_TAKE = 10;

    /**
     * 滚动分页返回实体
     *
     * @param <T> 返回类型
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ScrollingResult<T> {
        /**
         * 分页数据集
         */
        private List<T> list;
        /**
         * 下一次开始位置
         */
        private String nextToken;

        /**
         * 每次取量
         */
        private long take;

    }

    /**
     * success result
     *
     * @param data      list data
     * @param nextToken next start position
     */
    private ScrollingResultWrapper(List<T> data, String nextToken) {
        super(new ScrollingResultWrapper.ScrollingResult<>(data, nextToken, DEFAULT_TAKE));
    }

    /**
     * success result
     *
     * @param data      list data
     * @param nextToken next start position
     * @param take      per get data count
     */
    private ScrollingResultWrapper(List<T> data, String nextToken, long take) {
        super(new ScrollingResultWrapper.ScrollingResult<>(data, nextToken, take));
    }

    /**
     * success result
     *
     * @param data      list data
     * @param nextToken next start position
     */
    public static <T> ScrollingResultWrapper<T> success(List<T> data, String nextToken) {
        return new ScrollingResultWrapper<>(data, nextToken);
    }

    /**
     * success result
     *
     * @param data      list data
     * @param nextToken next start position
     * @param take      per get data count
     */
    public static <T> ScrollingResultWrapper<T> success(List<T> data, String nextToken, long take) {
        return new ScrollingResultWrapper<>(data, nextToken, take);
    }
}

