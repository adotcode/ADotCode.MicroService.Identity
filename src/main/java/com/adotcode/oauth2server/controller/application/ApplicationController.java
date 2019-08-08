package com.adotcode.oauth2server.controller.application;

import com.adotcode.oauth2server.domain.dto.response.application.LanguageResponse;
import com.adotcode.oauth2server.domain.wrapper.ListResultWrapper;
import com.adotcode.oauth2server.domain.wrapper.ResultWrapper;
import com.adotcode.oauth2server.service.application.I18nService;
import java.util.List;
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
  @GetMapping("i18n/languages/switch")
  public ResultWrapper switchLanguage(@RequestParam String l) {
    return ListResultWrapper.ok();
  }
}
