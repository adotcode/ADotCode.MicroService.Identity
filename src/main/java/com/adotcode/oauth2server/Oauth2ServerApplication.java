package com.adotcode.oauth2server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * main
 * <p>
 * exclude = {DataSourceAutoConfiguration.class}
 * <p>
 * 禁用springboot默认加载的application.yml单数据源配置
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-13
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.adotcode.oauth2server.mapper")
public class Oauth2ServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(Oauth2ServerApplication.class, args);
  }

}
