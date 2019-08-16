package com.adotcode.oauth2server.domain.auditing;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

/**
 * 添加删除相关[Deleted Object]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasDeletedObject {

  /**
   * 是否删除
   */
  private boolean deleted = false;

  /**
   * 删除人
   */
  private UUID deletedBy;

  /**
   * 删除时间
   */
  private Date deletedAt;

  /**
   * 设置删除信息
   *
   * @param deletedBy 删除人
   */
  public void setDeleted(UUID deletedBy) {
    this.deleted = true;
    this.deletedAt = new Date();
    this.deletedBy = deletedBy;
  }
}
