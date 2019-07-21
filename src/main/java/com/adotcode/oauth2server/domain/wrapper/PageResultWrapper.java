package com.adotcode.oauth2server.domain.wrapper;

import com.sun.tools.javac.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 分页结果返回包装器
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@NoArgsConstructor
public class PageResultWrapper<T> extends ResultWrapper<PageResultWrapper.PageResult<T>> {

    /**
     * 默认分页大小
     */
    private static final long DEFAULT_PAGE_SIZE = 10;

    /**
     * 分页返回实体
     *
     * @param <T> 返回数据类型
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PageResult<T> {
        /**
         * 分页数据集
         */
        private List<T> list;
        /**
         * 总记录数
         */
        private long total;
        /**
         * 分页页码
         */
        private long pageIndex;
        /**
         * 分页大小
         */
        private long pageSize;

        /**
         * 总页数
         *
         * @return 总页数
         */
        public long getTotalPages() {
            return (this.total + this.pageSize - 1) / this.pageSize;
        }
    }

    /**
     * success result
     *
     * @param data      list data
     * @param total     total items
     * @param pageIndex page index
     */
    public PageResultWrapper(List<T> data, long total, long pageIndex) {
        super(new PageResultWrapper.PageResult<>(data, total, pageIndex, DEFAULT_PAGE_SIZE));
    }

    /**
     * success result
     *
     * @param data      list data
     * @param total     total items
     * @param pageIndex page index
     * @param pageSize  page size
     */
    public PageResultWrapper(List<T> data, long total, long pageIndex, long pageSize) {
        super(new PageResultWrapper.PageResult<>(data, total, pageIndex, pageSize));
    }

    /***
     * custom result code & message
     * @param code code
     * @param message message
     * @param data list data
     * @param total total items
     * @param pageIndex page index
     * @param pageSize page size
     */
    public PageResultWrapper(String code, String message, List<T> data, long total, long pageIndex, long pageSize) {
        super(code, message, new PageResultWrapper.PageResult<>(data, total, pageIndex, pageSize));
    }

}
