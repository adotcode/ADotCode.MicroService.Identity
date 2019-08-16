package com.adotcode.oauth2server.domain.auditing;

import lombok.Data;

/**
 * 添加删除人[T DeletedBy]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasGenericDeletedBy<T> {

  /**
   * 删除人
   */
  private T deletedBy;
}
