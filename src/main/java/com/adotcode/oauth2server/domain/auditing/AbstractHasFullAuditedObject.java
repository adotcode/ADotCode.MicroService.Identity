package com.adotcode.oauth2server.domain.auditing;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import lombok.Data;

/**
 * 添加审计相关全部[Audited Object]属性的标准抽象类。
 *
 * @author risfeng
 * @date 2019/08/15
 */
@Data
public abstract class AbstractHasFullAuditedObject implements Serializable, Cloneable {

  private static final long serialVersionUID = 275824822145841662L;

  /**
   * 乐观锁版本号
   */
  private Long version = 1L;

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
   * 设置创建信息
   *
   * @param createdBy 创建人
   */
  public void setCreated(UUID createdBy) {
    this.createdBy = createdBy;
    this.createdAt = new Date();
  }

  /**
   * 设置更新信息
   *
   * @param updatedBy 更新人
   */
  public void setUpdated(UUID updatedBy) {
    this.updatedBy = updatedBy;
    this.updatedAt = new Date();
  }

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
