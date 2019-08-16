package com.adotcode.oauth2server.domain.auditing;

import java.util.UUID;
import lombok.Data;

/**
 * 添加删除人[DeletedBy]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasDeletedBy {

  /**
   * 删除人
   */
  private UUID deletedBy;
}
