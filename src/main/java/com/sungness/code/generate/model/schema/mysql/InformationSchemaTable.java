package com.sungness.code.generate.model.schema.mysql;

import com.sungness.core.util.FieldNaming;

import java.io.Serializable;
import java.util.List;

/**
 * Mysql信息架构（information_schema）-表对应的Bean
 * Created by wanghongwei on 11/15/15.
 */
public class InformationSchemaTable implements Serializable {
    private static final long serialVersionUID = -8412087153821120864L;
    /**  */
    private String tableCatalog;
    /** 所属数据库名 */
    private String tableSchema;
    /** 表名 */
    private String tableName;
    /** 表类型 */
    private String tableType;
    /** 表引擎类型 */
    private String engine;
    /** 版本 */
    private String version;
    /**  */
    private String rowFormat;
    /** 字符集 */
    private String tableCollation;
    /** 注释 */
    private String tableComment;

    /** 表名转换后的驼峰式名称（首字母小写） */
    private String camelCaseName;
    /** 表名转换后的驼峰式名称（首字母大写） */
    private String upperCaseName;


    private List<InformationSchemaColumn> columns;

    public String getTableCatalog() {
        return tableCatalog;
    }

    public void setTableCatalog(String tableCatalog) {
        this.tableCatalog = tableCatalog;
    }

    public String getTableSchema() {
        return tableSchema;
    }

    public void setTableSchema(String tableSchema) {
        this.tableSchema = tableSchema;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.camelCaseName = FieldNaming.toCamelCase(tableName);
        this.upperCaseName = FieldNaming.upperCaseFirstLetter(this.camelCaseName);
    }

    public String getTableType() {
        return tableType;
    }

    public void setTableType(String tableType) {
        this.tableType = tableType;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getRowFormat() {
        return rowFormat;
    }

    public void setRowFormat(String rowFormat) {
        this.rowFormat = rowFormat;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    public String getTableComment() {
        return tableComment;
    }

    public String getClearComment() {
        if (tableComment.endsWith("表")) {
            return tableComment.substring(0, tableComment.lastIndexOf("表"));
        }
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public List<InformationSchemaColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<InformationSchemaColumn> columns) {
        this.columns = columns;
    }

    public String getCamelCaseName() {
        return camelCaseName;
    }

    public String getUpperCaseName() {
        return upperCaseName;
    }
}
