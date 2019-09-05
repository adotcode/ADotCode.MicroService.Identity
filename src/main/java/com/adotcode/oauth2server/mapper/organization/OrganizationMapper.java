package com.adotcode.oauth2server.mapper.organization;

import com.adotcode.oauth2server.entity.Organization;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * ${description}
 *
 * @author risfeng
 * @date 2019/09/04
 */
@Mapper
public interface OrganizationMapper extends
    com.adotcode.oauth2server.core.base.Mapper<Organization> {


  /**
   * 批量更新
   *
   * @param list 更新列表
   * @return 影响行数
   */
  int updateBatch(List<Organization> list);

  /**
   * 批量更新
   *
   * @param list 更新列表
   * @return 影响行数
   */
  int batchInsert(List<Organization> list);

  /**
   * 插入或更新
   *
   * @param record 更新列表
   * @return 影响行数
   */
  int insertOrUpdate(Organization record);

  /**
   * 选择性插入或更新（null不处理）
   *
   * @param record 更新列表
   * @return 影响行数
   */
  int insertOrUpdateSelective(Organization record);
}