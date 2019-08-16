package com.adotcode.oauth2server.domain.auditing;

import java.util.Date;
import lombok.Data;

/**
 * 添加删除时间[DeletedAt]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasDeletedAt {

  /**
   * 删除时间
   */
  private Date deletedAt;
}
