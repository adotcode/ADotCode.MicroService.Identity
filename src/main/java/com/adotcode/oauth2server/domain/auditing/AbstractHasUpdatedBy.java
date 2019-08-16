package com.adotcode.oauth2server.domain.auditing;

import java.util.UUID;
import lombok.Data;

/**
 * 添加更新人[UpdatedBy]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasUpdatedBy {

  /**
   * 更新人
   */
  private UUID updatedBy;
}
