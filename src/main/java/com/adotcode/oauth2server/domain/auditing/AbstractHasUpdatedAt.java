package com.adotcode.oauth2server.domain.auditing;

import java.util.Date;
import lombok.Data;

/**
 * 添加更新时间[UpdatedAt]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasUpdatedAt {

  /**
   * 更新时间
   */
  private Date updatedAt;
}
