package com.adotcode.oauth2server.service.application.impl;

import com.adotcode.oauth2server.core.cache.RedisService;
import com.adotcode.oauth2server.core.constant.RedisKeyConstants;
import com.adotcode.oauth2server.core.enums.i18n.LanguageEnum;
import com.adotcode.oauth2server.core.util.i18n.I18nMessageUtils;
import com.adotcode.oauth2server.facade.model.output.application.KeyValueOutput;
import com.adotcode.oauth2server.facade.model.output.application.LanguageMessageSourceOutput;
import com.adotcode.oauth2server.facade.model.output.application.LanguageOutput;
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
import org.apache.commons.lang3.ObjectUtils;
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
  private final RedisService<String, LanguageOutput> redisService;

  public I18nServiceImpl(
      ResourceBundleMessageSource messageSource,
      RedisService<String, LanguageOutput> redisService) {
    this.messageSource = messageSource;
    this.redisService = redisService;
  }

  /**
   * 获取语言列表
   *
   * @return List<LanguageOutput>
   */
  @Override
  public List<LanguageOutput> getLanguages() {
    String redisKey = RedisKeyConstants.I18nService.GET_LANGUAGES_KEY;
    List<LanguageOutput> result = redisService.listFindAll(redisKey);
    if (ObjectUtils.isNotEmpty(result)) {
      return result;
    }
    result = Stream.of(LanguageEnum.values())
        .map(languageEnum -> {
          return new LanguageOutput(
              languageEnum.getLanguage(),
              I18nMessageUtils.translate(languageEnum.getDisplayName()),
              languageEnum.getLang(),
              languageEnum.getCountry(),
              languageEnum.currentLocale());
        })
        .collect(Collectors.toList());
    result.forEach(item -> {
      redisService.listPush(redisKey, item);
    });
    redisService.expire(redisKey, RedisKeyConstants.I18nService.DEFAULT_TIME_OUT,
        RedisKeyConstants.I18nService.DEFAULT_TIME_UNIT);
    return result;
  }

  /**
   * 获取语言资源列表
   *
   * @return LanguageMessageSourceOutput
   */
  @Override
  public LanguageMessageSourceOutput getLocaleMessageResources() {
    Set<String> basenameSet = messageSource.getBasenameSet();
    Locale locale = LocaleContextHolder.getLocale();
    Iterator baseNameIterator = basenameSet.iterator();
    List<KeyValueOutput> keyValueRespons = new LinkedList<>();
    while (baseNameIterator.hasNext()) {
      String baseName = baseNameIterator.next().toString();
      ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);
      Enumeration<String> resourceBundleKeys = resourceBundle.getKeys();
      while (resourceBundleKeys.hasMoreElements()) {
        String key = resourceBundleKeys.nextElement();
        String value = resourceBundle.getString(key);
        keyValueRespons.add(KeyValueOutput.builder().key(key).value(value).build());
      }
    }
    return LanguageMessageSourceOutput.builder()
        .locale(locale.toString())
        .messageSources(keyValueRespons)
        .build();
  }
}
