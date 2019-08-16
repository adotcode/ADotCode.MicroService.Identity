package com.adotcode.oauth2server.domain.auditing;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

/**
 * 添加创建相关[Created Object]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasCreatedObject {

  /**
   * 创建人
   */
  private UUID createdBy;

  /**
   * 创建时间
   */
  private Date createdAt;

  /**
   * 设置创建信息
   *
   * @param createdBy 创建人
   */
  public void setCreated(UUID createdBy) {
    this.createdBy = createdBy;
    this.createdAt = new Date();
  }
}
