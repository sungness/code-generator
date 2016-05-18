package com.sungness.code.generate.model;

import com.sungness.code.generate.model.element.FilesElement;
import com.sungness.code.generate.model.element.TableElement;
import com.sungness.code.generate.model.element.TemplatesElement;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 配置文件模型类
 * Created by wanghongwei on 2015/04/10.
 */
public class ConfigModel {
    //变量元素Map，对应标签variables
    private Map<String, String> variables = new LinkedHashMap<>();

    //模版集元素对象
    private TemplatesElement templatesElement;

    //文件集元素对象
    private FilesElement filesElement = new FilesElement();


    private String charset;

    private Database database;

    private List<TableElement> tables = new ArrayList<>();

    private GenAll genAll;

    public Map<String, String> getVariables() {
        return this.variables;
    }

    public void setVariables(Map<String, String> variables) {
        this.variables = variables;
    }

    public void addVariable(String name, String value) {
        this.variables.put(name, value);
    }

    public TemplatesElement getTemplatesElement() {
        return templatesElement;
    }

    public void setTemplatesElement(TemplatesElement templatesElement) {
        this.templatesElement = templatesElement;
    }

    public FilesElement getFilesElement() {
        return this.filesElement;
    }

    public void setFilesElement(FilesElement filesElement) {
        this.filesElement = filesElement;
    }


    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String paramString) {
        this.charset = paramString;
    }

    public Database getDatabase() {
        return this.database;
    }

    public void setDatabase(Database paramDatabase) {
        this.database = paramDatabase;
    }


    public List<TableElement> getTables() {
        return this.tables;
    }

    public void setTables(List<TableElement> paramList) {
        this.tables = paramList;
    }


    public GenAll getGenAll() {
        return this.genAll;
    }

    public void setGenAll(GenAll paramGenAll) {
        this.genAll = paramGenAll;
    }
}
