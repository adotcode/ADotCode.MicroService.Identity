package com.adotcode.oauth2server.core.enums.application;

import lombok.Getter;

/**
 * 标识：是/否、启用/禁用等枚举
 *
 * @author risfeng
 * @date 2019/08/25
 */
@Getter
public enum YesNoEnum {

  /**
   * 否/禁用
   */
  NO(0),

  /**
   * 是/启用
   */
  YES(1);

  /**
   * 枚举值
   */
  private Integer value;

  YesNoEnum(Integer value) {
    this.value = value;
  }
}
