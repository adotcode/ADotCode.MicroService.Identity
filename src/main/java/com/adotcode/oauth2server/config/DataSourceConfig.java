package com.adotcode.oauth2server.config;

import com.adotcode.oauth2server.common.database.datasource.DataSourceTypeEnum;
import com.adotcode.oauth2server.common.database.datasource.DynamicDataSource;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 数据源配置并创建
 *
 * @author risfeng
 * @date 2019/08/11
 */
@Configuration
@Slf4j
public class DataSourceConfig {

  /**
   * 数据库连接池
   * <p>DruidDataSources是阿里巴巴开源平台上一个数据库连接池实现，结合了 C3P0、DBCP、PROXOOL 等 DB 池的优点，同时加入了日志监控
   */
  @Value("${spring.datasource.type}")
  private Class<? extends DataSource> dataSourceType;

  /**
   * 主库数据源
   *
   * @return DataSource
   */
  @Bean(name = "masterDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.master")
  @Primary
  public DataSource masterDataSource() {
    DataSource masterDataSource = DataSourceBuilder.create().type(dataSourceType).build();
    log.info("master data source[{}]", masterDataSource);
    return masterDataSource;
  }

  /**
   * 从库数据源
   *
   * @return DataSource
   */
  @Bean(name = "slaveDataSource")
  @ConfigurationProperties(prefix = "spring.datasource.slave")
  public DataSource slaveDataSource() {
    DataSource slaveDataSource = DataSourceBuilder.create().type(dataSourceType).build();
    log.info("slave data source[{}]", slaveDataSource);
    return slaveDataSource;
  }

  /**
   * {@code @Primary} 给注解表示在同一个接口有多个实现类可以注入的时候，默认选择那个，而不是让@Autowied 注解报错
   * <p>
   * {@code @Qualifier} 根据名称进行注入，通常是在具有相同的多个类型的实力的一个注入（列如有多个DataSource 的类型）
   */
  @Bean
  public DynamicDataSource dataSource(
      @Qualifier("masterDataSource") DataSource masterDataSource,
      @Qualifier("slaveDataSource") DataSource slaveDataSource) {
    Map<Object, Object> targetDataSources = new HashMap<>(2);
    targetDataSources.put(DataSourceTypeEnum.MASTER, masterDataSource);
    targetDataSources.put(DataSourceTypeEnum.SLAVE, slaveDataSource);

    DynamicDataSource dataSource = new DynamicDataSource();
    // 此方法是AbstractRoutingDataSource的方法
    dataSource.setTargetDataSources(targetDataSources);
    //默认数据源设置为masterDataSource
    dataSource.setDefaultTargetDataSource(masterDataSource);
    return dataSource;
  }

  /**
   * 根据数据源创建SqlSessionFactory
   */
  @Bean
  public SqlSessionFactory seqSessionFactory(DynamicDataSource ds) throws Exception {
    SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
    // 指定数据源（否则报错）
    fb.setDataSource(ds);
    return fb.getObject();
  }

  /**
   * 配置事务管理
   */
  @Bean
  public DataSourceTransactionManager transactionManager(DynamicDataSource ds) {
    return new DataSourceTransactionManager(ds);
  }
}
