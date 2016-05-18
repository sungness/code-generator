package com.sungness.code.generate.model;

import java.util.HashMap;
import java.util.Map;


public class SubTable {
    private String tableName;

    private String foreignKey;

    private Map<String, String> vars = new HashMap<String, String>();

    public SubTable(String tableName, String foreignKey,
            Map<String, String> vars) {
        this.tableName = tableName;
        this.foreignKey = foreignKey;
        this.vars = vars;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getForeignKey() {
        return this.foreignKey;
    }

    public void setForeignKey(String foreignKey) {
        this.foreignKey = foreignKey;
    }

    public Map<String, String> getVars() {
        return this.vars;
    }

    public void setVars(Map<String, String> vars) {
        this.vars = vars;
    }
}