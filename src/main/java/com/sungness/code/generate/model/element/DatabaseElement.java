package com.sungness.code.generate.model.element;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * 数据库元素模型类
 * Created by wanghongwei on 2015/11/08。
 */
public class DatabaseElement extends BaseElement {
    private static final long serialVersionUID = -835583393937028857L;
    /** 包名 */
    private String packageName;
    /** 源表列表 */
    private List<TableElement> tables;
    /** 排除的表名集合 */
    private Set<String> excludeTables;

    public DatabaseElement() {
        this.tables = new ArrayList<>();
        this.excludeTables = new HashSet<>();
    }

    public List<TableElement> getTables() {
        return tables;
    }

    public void setTables(List<TableElement> tables) {
        this.tables = tables;
    }

    public void addTable(TableElement tableElement) {
        this.tables.add(tableElement);
    }

    public Set<String> getExcludeTables() {
        return excludeTables;
    }

    public void setExcludeTables(Set<String> excludeTables) {
        this.excludeTables = excludeTables;
    }

    public void addExclude(String tableName) {
        this.excludeTables.add(tableName);
    }

    public boolean exclude(String tableName) {
        return this.excludeTables.contains(tableName);
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}