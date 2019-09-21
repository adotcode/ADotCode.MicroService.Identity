package com.adotcode.oauth2server.core.constant;

import com.adotcode.oauth2server.service.application.impl.I18nServiceImpl;
import java.util.concurrent.TimeUnit;

/**
 * Redis Key定义常量类
 *
 * @author risfeng
 * @date 2019/08/25
 */
public class RedisKeyConstants {

  /**
   * 118n服务接口下到redis key
   * <p>
   * {@link I18nServiceImpl}
   * </p>
   */
  public static class I18nService {

    /**
     * 默认过期时间单位
     */
    public static TimeUnit DEFAULT_TIME_UNIT = TimeUnit.MINUTES;
    /**
     * 默认过期时间
     */
    public static long DEFAULT_TIME_OUT = 24 * 60;

    /**
     * 获取当前支持到语言列表Key
     */
    public static String GET_LANGUAGES_KEY = "I18nServiceImpl:getLanguages";
  }
}
