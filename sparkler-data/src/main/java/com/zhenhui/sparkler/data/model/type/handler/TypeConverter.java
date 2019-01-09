package com.zhenhui.sparkler.data.model.type.handler;

import com.zhenhui.sparkler.data.model.core.post.Type;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeConverter extends BaseTypeHandler<Type> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Type type, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, type.code);
    }

    @Override
    public Type getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return Type.valueOf(resultSet.getInt(s));
    }

    @Override
    public Type getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return Type.valueOf(resultSet.getInt(i));
    }

    @Override
    public Type getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return Type.valueOf(callableStatement.getInt(i));
    }
}
