package com.adotcode.oauth2server.mapper.log;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 审计日志Mapper
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@Repository
@Mapper
public interface AuditLogMapper {

  /**
   * 通过Id获取浏览器信息
   *
   * @param id id
   * @return 浏览器信息
   */
  String findBrowserInfoById(long id);
}

