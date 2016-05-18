package com.sungness.code.generate.dao;

import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;

import java.util.List;

/**
 * 信息架构数据持久化接口类
 * Created by wanghongwei on 11/15/15.
 */
public interface InformationSchemaDao {
    /**
     * 根据数据库名获取信息架构表列表
     * @param tableSchema String 数据库名称
     * @return List<InformationSchemaTable> 信息架构表列表
     */
    List<InformationSchemaTable> getTables(String tableSchema);

    /**
     * 根据数据库名和表名获取信息架构表
     * @param tableSchema String 数据库名称
     * @param tableName String 表名称
     * @return InformationSchemaTable 信息框架表对象
     */
    InformationSchemaTable getTable(String tableSchema, String tableName);

    /**
     * 根据数据库名和表名获取信息架构列列表
     * @param tableSchema String 数据库名称
     * @param tableName String 表名
     * @return List<InformationSchemaColumn> 信息架构列列表
     */
    List<InformationSchemaColumn> getColumns(String tableSchema, String tableName);
}
