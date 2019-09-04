package com.adotcode.oauth2server.core.handler.datatype;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

/**
 * UUID类型处理器
 *
 * @author risfeng
 * @date 2019/09/04
 */
@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(UUID.class)
public class UUIDTypeHandler extends BaseTypeHandler<UUID> {

  @Override
  public void setNonNullParameter(PreparedStatement ps, int i, UUID parameter, JdbcType jdbcType)
      throws SQLException {
    ps.setObject(i, parameter, jdbcType.TYPE_CODE);
  }

  @Override
  public UUID getNullableResult(ResultSet rs, String columnName)
      throws SQLException {
    return (UUID) rs.getObject(columnName);
  }

  @Override
  public UUID getNullableResult(ResultSet rs, int columnIndex)
      throws SQLException {
    return (UUID) rs.getObject(columnIndex);
  }

  @Override
  public UUID getNullableResult(CallableStatement cs, int columnIndex)
      throws SQLException {
    return (UUID) cs.getObject(columnIndex);
  }
}
