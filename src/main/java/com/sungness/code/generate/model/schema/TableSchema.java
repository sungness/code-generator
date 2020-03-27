package com.sungness.code.generate.model.schema;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableSchema {
    /** 表名 */
    private String tableName;

    /** 注释 */
    private String tableComment;

    /** 外键 */
    private String foreignKey = "";

    private Map<String, String> variables = new HashMap<>();

    /** 列 列表 */
    private List<ColumnSchema> columnList = new ArrayList<>();

    /** 关联子表 列表 */
    private List<TableSchema> subTableList = new ArrayList<>();

    /** 是否是子表 */
    private boolean sub;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableComment() {
        return this.tableComment.replaceAll("\r\n", "");
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public boolean isSub() {
        return this.sub;
    }

    public void setSub(boolean paramBoolean) {
        this.sub = paramBoolean;
    }

    public boolean getSub() {
        return this.sub;
    }

    public List<ColumnSchema> getColumnList() {
        return this.columnList;
    }

    public List<ColumnSchema> getPkList() {
        List<ColumnSchema> localArrayList = new ArrayList<>();
        for (ColumnSchema localColumnSchema : this.columnList) {
            if (localColumnSchema.getIsPK())
                localArrayList.add(localColumnSchema);
        }
        return localArrayList;
    }

    public ColumnSchema getPkModel() {
        for (ColumnSchema columnSchema : this.columnList) {
            if (columnSchema.getIsPK()) {
                return columnSchema;
            }
        }
        return null;
    }

    public List<ColumnSchema> getCommonList() {
        List<ColumnSchema> commonList = new ArrayList<>();
        for (ColumnSchema columnSchema : this.columnList) {
            if (!columnSchema.getIsPK())
                commonList.add(columnSchema);
        }
        return commonList;
    }

    public void setColumnList(List<ColumnSchema> columnList) {
        this.columnList = columnList;
    }

    public List<TableSchema> getSubTableList() {
        return this.subTableList;
    }

    public void setSubTableList(List<TableSchema> subTableList) {
        this.subTableList = subTableList;
    }

    public String getForeignKey() {
        return this.foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public Map<String, String> getVariables() {
        return this.variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public String toString() {
        return "TableModel [tableName=" + this.tableName + ", tableComment="
                + this.tableComment + ", foreignKey=" + this.foreignKey
                + ", variables=" + this.variables + ", columnList="
                + this.columnList + ", subTableList=" + this.subTableList
                + ", sub=" + this.sub + "]";
    }
}
