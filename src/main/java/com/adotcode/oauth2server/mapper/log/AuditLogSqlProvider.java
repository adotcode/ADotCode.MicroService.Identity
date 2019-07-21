package com.adotcode.oauth2server.mapper.log;

import org.apache.ibatis.jdbc.SQL;

/**
 * 审计日志SqlBuilder
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
public class AuditLogSqlProvider {

    /**
     * 通过Id查询浏览器信息
     *
     * @return sql
     */
    public String findBrowserInfoById() {
        return new SQL()
                .SELECT("BrowserInfo")
                .FROM("AbpAuditLogs")
                .WHERE("id=#{id}")
                .toString();
    }
}
