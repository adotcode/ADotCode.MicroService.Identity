package com.adotcode.oauth2server.mapper.user;

import org.apache.ibatis.jdbc.SQL;

/**
 * 用户信息SqlBuilder
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
public class UserSqlProvider {

  /**
   * 通过用户Id获取邮箱信息
   *
   * @return Sql
   */
  public String findUserEmailById() {
    return new SQL()
        .SELECT("EmailAddress")
        .FROM("abpusers")
        .WHERE("Id=#{id}")
        .toString();
  }
}
