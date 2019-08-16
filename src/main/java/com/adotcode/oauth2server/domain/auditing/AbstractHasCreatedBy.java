package com.adotcode.oauth2server.domain.auditing;

import java.util.UUID;
import lombok.Data;

/**
 * 添加创建人[CreatedBy]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasCreatedBy {

  /**
   * 创建人
   */
  private UUID createdBy;
}


