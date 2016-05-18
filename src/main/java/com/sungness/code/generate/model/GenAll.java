package com.sungness.code.generate.model;

import java.util.ArrayList;
import java.util.List;

public class GenAll {
    private String tableNames;

    private List<File2> file2 = new ArrayList<File2>();

    public GenAll(String tableNames) {
        this.tableNames = tableNames;
    }

    public String getTableNames() {
        return this.tableNames;
    }

    public void setTableNames(String tableNames) {
        this.tableNames = tableNames;
    }

    public List<File2> getFile() {
        return this.file2;
    }

    public void setFile(List<File2> file2) {
        this.file2 = file2;
    }
}