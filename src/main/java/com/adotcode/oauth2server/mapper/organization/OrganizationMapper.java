package com.adotcode.oauth2server.mapper.organization;

import com.adotcode.oauth2server.domain.entity.Organization;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 组织机构树Mapper
 *
 * @author risfeng
 * @date 2019/08/20
 */
@Repository
@Mapper
public interface OrganizationMapper {

  /**
   * 插入
   *
   * @param organization 新增对象
   * @return Organization
   */
  Organization insert(Organization organization);
}
