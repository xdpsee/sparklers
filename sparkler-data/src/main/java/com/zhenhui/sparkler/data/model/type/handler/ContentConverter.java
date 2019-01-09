package com.zhenhui.sparkler.data.model.type.handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.zhenhui.sparkler.data.model.core.post.Content;
import com.zhenhui.sparkler.data.utils.JsonUtils;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContentConverter extends BaseTypeHandler<Content> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Content content, JdbcType jdbcType) throws SQLException {

        preparedStatement.setString(i, JsonUtils.toJsonString(content));

    }

    @Override
    public Content getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return JsonUtils.fromJsonString(resultSet.getString(s), new TypeReference<Content>() {
        });
    }

    @Override
    public Content getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return JsonUtils.fromJsonString(resultSet.getString(i), new TypeReference<Content>() {
        });
    }

    @Override
    public Content getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return JsonUtils.fromJsonString(callableStatement.getString(i), new TypeReference<Content>() {
        });
    }
}
