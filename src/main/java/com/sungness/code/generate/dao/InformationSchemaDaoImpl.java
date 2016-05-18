package com.sungness.code.generate.dao;

import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

/**
 * 信息架构数据持久化接口实现类
 * Created by wanghongwei on 11/15/15.
 */
public class InformationSchemaDaoImpl implements InformationSchemaDao {

    private JdbcTemplate jdbcTemplate;

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String tableListSql;

    private String tableSql;

    private String columnListSql;

    private RowMapper<InformationSchemaTable> tableRowMapper;

    private RowMapper<InformationSchemaColumn> columnRowMapper;

    /**
     * 根据数据库名获取信息架构表列表
     *
     * @param tableSchema String 数据库名称
     * @return List<InformationSchemaTable> 信息架构表列表
     */
    @Override
    public List<InformationSchemaTable> getTables(String tableSchema) {
        return jdbcTemplate.query(tableListSql, tableRowMapper, tableSchema);
    }

    /**
     * 根据数据库名和表名获取信息架构表
     *
     * @param tableSchema String 数据库名称
     * @param tableName   String 表名称
     * @return InformationSchemaTable 信息框架表对象
     */
    @Override
    public InformationSchemaTable getTable(String tableSchema, String tableName) {
        return jdbcTemplate.queryForObject(
                        tableSql, tableRowMapper, tableSchema, tableName);
    }

    /**
     * 根据数据库名和表名获取信息架构列列表
     *
     * @param tableSchema String 数据库名称
     * @param tableName   String 表名
     * @return List<InformationSchemaColumn> 信息架构列列表
     */
    @Override
    public List<InformationSchemaColumn> getColumns(String tableSchema, String tableName) {
        return jdbcTemplate.query(columnListSql, columnRowMapper, tableSchema, tableName);
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setTableListSql(String tableListSql) {
        this.tableListSql = tableListSql;
    }

    public void setColumnListSql(String columnListSql) {
        this.columnListSql = columnListSql;
    }

    public void setTableRowMapper(RowMapper<InformationSchemaTable> tableRowMapper) {
        this.tableRowMapper = tableRowMapper;
    }

    public void setColumnRowMapper(RowMapper<InformationSchemaColumn> columnRowMapper) {
        this.columnRowMapper = columnRowMapper;
    }

    public void setTableSql(String tableSql) {
        this.tableSql = tableSql;
    }
}
