package com.adotcode.oauth2server.config;

import com.adotcode.oauth2server.common.plugin.VersionInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置
 *
 * @author risfeng
 * @date 2019/08/18
 */
@Configuration
public class MyBatisConfig {

  /**
   * 乐观锁插件
   *
   * @return VersionInterceptor
   */
  @Bean
  public Interceptor versionInterceptor() {
    return new VersionInterceptor();
  }

}
