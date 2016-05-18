package com.sungness.code.generate.dao;

import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 信息架构表RowMapper接口实现类
 * Created by wanghongwei on 11/15/15.
 */
public class InformationSchemaTableRowMapper implements RowMapper<InformationSchemaTable> {
    @Override
    public InformationSchemaTable mapRow(ResultSet resultSet, int i) throws SQLException {
        InformationSchemaTable item = new InformationSchemaTable();
        item.setTableCatalog(resultSet.getString("table_catalog"));
        item.setTableSchema(resultSet.getString("table_schema"));
        item.setTableName(resultSet.getString("table_name"));
        item.setTableType(resultSet.getString("table_type"));
        item.setEngine(resultSet.getString("engine"));
        item.setVersion(resultSet.getString("version"));
        item.setRowFormat(resultSet.getString("row_format"));
        item.setTableCollation(resultSet.getString("table_collation"));
        item.setTableComment(resultSet.getString("table_comment"));
        return item;
    }
}
