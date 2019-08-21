package com.adotcode.oauth2server.controller.application;

import com.adotcode.oauth2server.common.util.i18n.I18nMessageUtils;
import com.adotcode.oauth2server.model.response.application.LanguageMessageSourceResponse;
import com.adotcode.oauth2server.model.response.application.LanguageResponse;
import com.adotcode.oauth2server.model.wrapper.ListResultWrapper;
import com.adotcode.oauth2server.model.wrapper.ResultWrapper;
import com.adotcode.oauth2server.model.wrapper.ResultWrapper.ErrorWrapper;
import com.adotcode.oauth2server.service.application.I18nService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用信息获取控制器
 *
 * @author risfeng
 * @date 2019/08/06
 */
@RequestMapping("api/v1/applications")
@RestController
@Slf4j
@Validated
public class ApplicationController {

  private final I18nService i18nService;

  public ApplicationController(
      I18nService i18nService) {
    this.i18nService = i18nService;
  }

  /**
   * 获取语言列表
   */
  @GetMapping("i18n/languages")
  public ListResultWrapper<LanguageResponse> getLanguages() {
    List<LanguageResponse> result = i18nService.getLanguages();
    return ListResultWrapper.ok(result);
  }

  /**
   * 切换语言 根据参数l系统自动切换语言，接口无需做处理
   *
   * @param l locale code 不存在则设置为默认
   */
  @GetMapping("i18n/languages/change")
  public ResultWrapper changeLanguage(@RequestParam String l) {
    List<String> applicationLanguages = i18nService.getLanguages()
        .stream()
        .map(LanguageResponse::getLocale)
        .collect(Collectors.toList());
    if (!applicationLanguages.contains(l)) {
      return ResultWrapper.error(ErrorWrapper
          .newInstance(I18nMessageUtils.translate("application.i18n.language.not.present", l)));
    }
    return ResultWrapper.ok();
  }

  /**
   * 获取语言资源列表
   */
  @GetMapping("i18n/languages/message-resources")
  public ResultWrapper<LanguageMessageSourceResponse> getMessageResources() {
    LanguageMessageSourceResponse result = i18nService.getLocaleMessageResources();
    return ResultWrapper.ok(result);
  }
}
