package com.adotcode.oauth2server.domain.auditing;

import lombok.Data;

/**
 * 添加更新人[T UpdatedBy]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasGenericUpdatedBy<T> {

  /**
   * 更新人
   */
  private T updatedBy;
}
