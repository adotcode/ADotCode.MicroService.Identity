package com.adotcode.oauth2server.service.application.impl;

import com.adotcode.oauth2server.domain.dto.response.application.KeyValueResponse;
import com.adotcode.oauth2server.domain.dto.response.application.LanguageMessageSourceResponse;
import com.adotcode.oauth2server.domain.dto.response.application.LanguageResponse;
import com.adotcode.oauth2server.domain.enums.i18n.LanguageEnum;
import com.adotcode.oauth2server.service.application.I18nService;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

/**
 * I18n服务接口实现
 *
 * @author risfeng
 * @date 2019/08/07
 */
@Service
public class I18nServiceImpl implements I18nService {

  private final ResourceBundleMessageSource messageSource;

  public I18nServiceImpl(
      ResourceBundleMessageSource messageSource) {
    this.messageSource = messageSource;
  }

  /**
   * 获取语言列表
   *
   * @return List<LanguageResponse>
   */
  @Override
  public List<LanguageResponse> getLanguages() {
    return Stream.of(LanguageEnum.values())
        .map(languageEnum -> {
          return new LanguageResponse(languageEnum.getLanguage(), languageEnum.getLang(),
              languageEnum.getCountry(), languageEnum.currentLocale());
        })
        .collect(Collectors.toList());
  }

  /**
   * 获取语言资源列表
   *
   * @return LanguageMessageSourceResponse
   */
  @Override
  public LanguageMessageSourceResponse getLocaleMessageResources() {
    Set<String> basenameSet = messageSource.getBasenameSet();
    Locale locale = LocaleContextHolder.getLocale();
    Iterator baseNameIterator = basenameSet.iterator();
    List<KeyValueResponse> keyValueResponses = new LinkedList<>();
    while (baseNameIterator.hasNext()) {
      String baseName = baseNameIterator.next().toString();
      ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
      Enumeration<String> resourceBundleKeys = resourceBundle.getKeys();
      while (resourceBundleKeys.hasMoreElements()) {
        String key = resourceBundleKeys.nextElement();
        String value = resourceBundle.getString(key);
        keyValueResponses.add(KeyValueResponse.builder().key(key).value(value).build());
      }
    }
    return LanguageMessageSourceResponse.builder()
        .locale(locale.toString())
        .messageSources(keyValueResponses)
        .build();
  }
}
