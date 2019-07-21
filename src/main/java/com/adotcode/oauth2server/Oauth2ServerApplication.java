package com.adotcode.oauth2server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * main
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-13
 */
@SpringBootApplication
@MapperScan("com.adotcode.oauth2server.mapper")
public class Oauth2ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ServerApplication.class, args);
    }

}
