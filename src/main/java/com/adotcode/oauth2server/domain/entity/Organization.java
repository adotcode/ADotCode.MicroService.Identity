package com.adotcode.oauth2server.domain.entity;

import com.adotcode.oauth2server.domain.auditing.AbstractHasFullAuditedObject;
import java.io.Serializable;
import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 组织机构树数据表实体
 *
 * @author risfeng
 * @date 2019/08/15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Organization extends AbstractHasFullAuditedObject implements Serializable, Cloneable {

  private static final long serialVersionUID = -736140271691530336L;

  /**
   * 主键Id
   */
  private UUID id = UUID.randomUUID();
  /**
   * 路径编码：00001.00001
   */
  private String code;

  /**
   * 组织机构名称
   */
  private String name;

  /**
   * 父组织机构Id
   */
  private UUID parentId;

  /**
   * 是否叶子节点
   */
  private boolean leaf;

  /**
   * 层级
   */
  private int level;

}
