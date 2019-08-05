package com.adotcode.oauth2server.domain.enums.i18n;

import org.springframework.util.StringUtils;

/**
 * 国际化多语言枚举
 *
 * @author risfeng
 * @date 2019/07/30
 */
public enum LanguageEnum {

  /**
   * 美式英文
   */
  LANGUAGE_EN_US("en_US"),

  /**
   * 简体中文
   */
  LANGUAGE_ZH_CN("zh_CN");

  /**
   * 语言类型
   */
  private String language;

  private LanguageEnum(String language) {
    this.language = language;
  }

  /**
   * 获取指定语言类型(如未匹配,则返回中文)
   *
   * @param language 语言类型
   */
  public static String getLanguageType(String language) {
    if (StringUtils.isEmpty(language)) {
      return LANGUAGE_ZH_CN.language;
    }
    for (LanguageEnum languageEnum : LanguageEnum.values()) {
      if (languageEnum.language.equalsIgnoreCase(language)) {
        return languageEnum.language;
      }
    }
    return LANGUAGE_ZH_CN.language;
  }
}
