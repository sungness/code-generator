package com.sungness.code.generate.dao;

import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 信息架构列RowMapper接口实现类
 * Created by wanghongwei on 11/15/15.
 */
public class InformationSchemaColumnRowMapper implements RowMapper<InformationSchemaColumn> {
    @Override
    public InformationSchemaColumn mapRow(ResultSet resultSet, int i) throws SQLException {
        InformationSchemaColumn item = new InformationSchemaColumn();
        item.setTableCatalog(resultSet.getString("table_catalog"));
        item.setTableSchema(resultSet.getString("table_schema"));
        item.setTableName(resultSet.getString("table_name"));
        item.setColumnName(resultSet.getString("column_name"));
        item.setOrdinalPosition(resultSet.getInt("ordinal_position"));
        item.setColumnDefault(resultSet.getObject("column_default"));
        item.setIsNullable(resultSet.getString("is_nullable"));
        item.setDataType(resultSet.getString("data_type"));
        item.setCharacterMaximumLength(resultSet.getLong("character_maximum_length"));
        item.setCharacterOctetLength(resultSet.getLong("character_octet_length"));
        item.setNumericPrecision(resultSet.getInt("numeric_precision"));
        item.setCharacterSetName(resultSet.getString("character_set_name"));
        item.setCollationName(resultSet.getString("collation_name"));
        item.setColumnType(resultSet.getString("column_type"));
        item.setColumnKey(resultSet.getString("column_key"));
        item.setExtra(resultSet.getString("extra"));
        item.setColumnComment(resultSet.getString("column_comment"));
        return item;
    }
}
