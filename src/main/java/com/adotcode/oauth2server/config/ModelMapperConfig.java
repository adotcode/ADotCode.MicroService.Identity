package com.adotcode.oauth2server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 实体转换配置
 *
 * @author risfeng
 * @date 2019/08/08
 */
@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }
}
