package com.adotcode.oauth2server.web.controller.application;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

import com.adotcode.oauth2server.core.util.i18n.I18nMessageUtils;
import com.adotcode.oauth2server.core.wrapper.ListResultWrapper;
import com.adotcode.oauth2server.core.wrapper.ResultWrapper;
import com.adotcode.oauth2server.core.wrapper.ResultWrapper.ErrorWrapper;
import com.adotcode.oauth2server.model.output.application.LanguageMessageSourceOutput;
import com.adotcode.oauth2server.model.output.application.LanguageOutput;
import com.adotcode.oauth2server.service.application.I18nService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "应用基础信息")
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
  @ApiOperation(
      httpMethod = "GET",
      value = "获取语言列表",
      notes = "获取系统支持语言列表",
      response = LanguageOutput.class,
      responseContainer = "List",
      consumes = APPLICATION_JSON_UTF8_VALUE)
  public ListResultWrapper<LanguageOutput> getLanguages() {
    List<LanguageOutput> result = i18nService.getLanguages();
    return ListResultWrapper.ok(result);
  }

  /**
   * 切换语言 根据参数l系统自动切换语言，接口无需做处理
   *
   * @param l locale code 不存在则设置为默认
   */
  @GetMapping("i18n/languages/change")
  @ApiOperation(
      httpMethod = "GET",
      value = "切换语言",
      notes = "根据参数l系统自动切换语言",
      response = ResultWrapper.class,
      responseContainer = "List",
      consumes = APPLICATION_JSON_UTF8_VALUE)
  public ResultWrapper changeLanguage(@RequestParam String l) {
    List<String> applicationLanguages = i18nService.getLanguages()
        .stream()
        .map(LanguageOutput::getLocale)
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
  @ApiOperation(
      httpMethod = "GET",
      value = "获取语言资源列表",
      notes = "获取语言资源key-value列表",
      response = LanguageMessageSourceOutput.class,
      responseContainer = "List",
      consumes = APPLICATION_JSON_UTF8_VALUE)
  public ResultWrapper<LanguageMessageSourceOutput> getMessageResources() {
    LanguageMessageSourceOutput result = i18nService.getLocaleMessageResources();
    return ResultWrapper.ok(result);
  }
}
