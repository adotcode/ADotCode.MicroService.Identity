package com.adotcode.oauth2server.common.annotation;

import com.adotcode.oauth2server.common.database.datasource.DataSourceTypeEnum;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源切换注解
 *
 * @author risfeng
 * @date 2019/08/11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

  /**
   * 数据源类型，默认：{@code DataSourceTypeEnum.MASTER}
   *
   * @return {@code DataSourceTypeEnum}
   */
  DataSourceTypeEnum value() default DataSourceTypeEnum.MASTER;
}
