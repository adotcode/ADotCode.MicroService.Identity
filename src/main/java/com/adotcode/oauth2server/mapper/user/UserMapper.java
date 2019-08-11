package com.adotcode.oauth2server.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.stereotype.Repository;

/**
 * 用户信息Mapper
 *
 * @author risfeng
 * @version 1.0.0
 * @date 2019-07-15
 */
@Repository
@Mapper
public interface UserMapper {

  /**
   * 通过用户Id获取邮箱信息
   *
   * @param id 用户Id
   * @return 邮箱信息
   */
  @SelectProvider(type = UserSqlProvider.class, method = "findUserEmailById")
  public String findUserEmailById(long id);
}

