package com.adotcode.oauth2server.domain.auditing;

import lombok.Data;

/**
 * 添加软删除标识[DeletedBy]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasSoftDeleted {

  /**
   * 是否删除
   */
  private boolean deleted;
}
