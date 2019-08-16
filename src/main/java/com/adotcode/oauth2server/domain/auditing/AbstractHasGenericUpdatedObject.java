package com.adotcode.oauth2server.domain.auditing;

import java.util.Date;
import lombok.Data;

/**
 * 添加更新相关[Updated Object]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasGenericUpdatedObject<T> {

  /**
   * 更新人
   */
  private T updatedBy;

  /**
   * 更新时间
   */
  private Date updatedAt;

  /**
   * 设置更新信息
   *
   * @param updatedBy 更新人
   */
  public void setUpdated(T updatedBy) {
    this.updatedBy = updatedBy;
    this.updatedAt = new Date();
  }
}
