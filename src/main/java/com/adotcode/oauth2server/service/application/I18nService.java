package com.adotcode.oauth2server.service.application;

import com.adotcode.oauth2server.domain.dto.response.application.LanguageResponse;
import java.util.List;

/**
 * I18n服务接口
 *
 * @author risfeng
 * @date 2019/08/07
 */
public interface I18nService {

  /**
   * 获取语言列表
   *
   * @return List<LanguageResponse>
   */
  List<LanguageResponse> getLanguages();
}
