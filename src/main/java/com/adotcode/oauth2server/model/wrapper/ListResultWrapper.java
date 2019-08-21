package com.adotcode.oauth2server.model.wrapper;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  static class ListResult<T> {

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
  private ListResultWrapper(List<T> data) {
    super(new ListResultWrapper.ListResult<>(data));
  }

  /**
   * ok result
   *
   * @param data data
   * @param <T> object T
   * @return ResultWrapper<T>
   */
  public static <T> ListResultWrapper<T> ok(List<T> data) {
    return new ListResultWrapper<>(data);
  }
}
