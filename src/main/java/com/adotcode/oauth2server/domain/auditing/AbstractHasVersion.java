package com.adotcode.oauth2server.domain.auditing;

import lombok.Data;

/**
 * 添加乐观锁版本号[version]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/20
 */
@Data
public abstract class AbstractHasVersion {

  /**
   * 乐观锁版本号
   */
  private Long version;
}
