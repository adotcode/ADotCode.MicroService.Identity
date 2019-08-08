package com.adotcode.oauth2server.service.application.impl;

import com.adotcode.oauth2server.domain.dto.response.application.LanguageResponse;
import com.adotcode.oauth2server.domain.enums.i18n.LanguageEnum;
import com.adotcode.oauth2server.service.application.I18nService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

/**
 * I18n服务接口实现
 *
 * @author risfeng
 * @date 2019/08/07
 */
@Service
public class I18nServiceImpl implements I18nService {

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
}
