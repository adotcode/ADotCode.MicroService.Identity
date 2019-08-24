package com.adotcode.oauth2server.core.base;

import com.alibaba.fastjson.JSON;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 基础实体类
 *
 * @param <T> T为操作人的类型
 * @author risfeng
 * @date 2019/08/24
 */
@Data
public abstract class BaseEntity<T> implements Serializable, Cloneable {

  private static final long serialVersionUID = -8578434261034243867L;
  /**
   * 乐观锁版本号
   */
  private long version = 1L;

  /**
   * 创建人
   */
  private T createdBy;

  /**
   * 创建时间
   */
  private Date createdAt;

  /**
   * 更新人
   */
  private T updatedBy;

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
  private T deletedBy;

  /**
   * 删除时间
   */
  private Date deletedAt;

  /**
   * 设置创建信息
   *
   * @param createdBy 创建人
   */
  public void setCreated(T createdBy) {
    this.createdBy = createdBy;
    this.createdAt = new Date();
  }

  /**
   * 设置更新信息
   *
   * @param updatedBy 更新人
   */
  public void setUpdated(T updatedBy) {
    this.updatedBy = updatedBy;
    this.updatedAt = new Date();
  }

  /**
   * 设置删除信息
   *
   * @param deletedBy 删除人
   */
  public void setDeleted(T deletedBy) {
    this.deleted = true;
    this.deletedAt = new Date();
    this.deletedBy = deletedBy;
  }

  /**
   * 转字符串
   *
   * @return 字符串
   */
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
  }

  /**
   * 转Json字符串
   *
   * @return json字符串
   */
  public String toJsonString() {
    return JSON.toJSONString(this);
  }
}
