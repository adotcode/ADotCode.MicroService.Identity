package com.adotcode.oauth2server.domain.dto.response.application;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 本地化语言
 *
 * @author risfeng
 * @date 2019/08/07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageResponse implements Serializable {

  private static final long serialVersionUID = -1109056834909541664L;
  /**
   * 本地化编码
   */
  private String locale;
  /**
   * 语言编码
   */
  private String language;
  /**
   * 国家码
   */
  private String country;

  /**
   * 是否当前使用语言
   */
  private boolean currentLocale;


  @Override
  public String toString() {
    return "";
  }
}
