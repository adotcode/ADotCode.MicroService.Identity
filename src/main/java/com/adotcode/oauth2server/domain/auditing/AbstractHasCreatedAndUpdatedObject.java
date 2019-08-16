package com.adotcode.oauth2server.domain.auditing;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

/**
 * 添加创建和更新相关[Audited Object]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasCreatedAndUpdatedObject {

  /**
   * 当前时间
   */
  private final Date now = new Date();

  /**
   * 创建人
   */
  private UUID createdBy;

  /**
   * 创建时间
   */
  private Date createdAt;

  /**
   * 更新人
   */
  private UUID updatedBy;

  /**
   * 更新时间
   */
  private Date updatedAt;


  /**
   * 设置创建信息
   *
   * @param createdBy 创建人
   */
  public void setCreated(UUID createdBy) {
    this.createdBy = createdBy;
    this.createdAt = now;
  }

  /**
   * 设置更新信息
   *
   * @param updatedBy 更新人
   */
  public void setUpdated(UUID updatedBy) {
    this.updatedBy = updatedBy;
    this.updatedAt = now;
  }

}
