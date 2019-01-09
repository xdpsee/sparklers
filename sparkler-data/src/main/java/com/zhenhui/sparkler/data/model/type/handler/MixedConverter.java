package com.zhenhui.sparkler.data.model.type.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.sparkler.data.model.core.common.misc.Mixed;
import com.zhenhui.sparkler.data.utils.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MixedConverter extends BaseTypeHandler<Mixed> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Mixed mixture, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, JsonUtils.toJsonString(mixture));
    }

    @Override
    public Mixed getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JsonUtils.fromJsonString(resultSet.getString(s), new TypeReference<Mixed>() {
        });
    }

    @Override
    public Mixed getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JsonUtils.fromJsonString(resultSet.getString(i), new TypeReference<Mixed>() {
        });
    }

    @Override
    public Mixed getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JsonUtils.fromJsonString(callableStatement.getString(i), new TypeReference<Mixed>() {
        });
    }

}
