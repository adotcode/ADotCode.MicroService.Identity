package com.adotcode.oauth2server.mapper.log;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * 审计日志Mapper
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@Repository
public interface AuditLogMapper {
    /**
     * 通过Id获取浏览器信息
     *
     * @param id id
     * @return 浏览器信息
     */
    @SelectProvider(type = AuditLogSqlProvider.class, method = "findBrowserInfoById")
    public String findBrowserInfoById(long id);
}

