package com.adotcode.oauth2server.domain.auditing;

import java.util.Date;
import lombok.Data;

/**
 * 添加创建时间[CreatedAt]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasCreatedAt {

  /**
   * 创建时间
   */
  private Date createdAt;
}
