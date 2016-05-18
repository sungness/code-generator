package com.sungness.code.generate.model.element;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 配置文件根元素 code 模型类
 * Created by wanghongwei on 11/8/15.
 */
public class CodeElement extends BaseElement {
    private static final long serialVersionUID = -3854025330500155303L;
    /** 系统设置参数map */
    private Map<String, String> settingMap;

    /** 系统属性参数map */
    private Map<String, String> propertyMap;

    /** 模板容器对象 */
    private TemplatesElement templates;

    /** 目标文件容器对象 */
    private TargetFilesElement targetFiles;

    /** 源数据库列表 */
    private List<DatabaseElement> databases;

    public CodeElement() {
        settingMap = new LinkedHashMap<>();
        propertyMap = new LinkedHashMap<>();
        databases = new ArrayList<>();
    }

    public TemplatesElement getTemplates() {
        return templates;
    }

    public void setTemplates(TemplatesElement templates) {
        this.templates = templates;
    }

    public String getTemplatesPath() {
        return templates.getPath();
    }

    public TemplateElement getTemplateById(String ftlid) {
        return templates.getTemplate(ftlid);
    }

    public Map<String, String> getSettingMap() {
        return settingMap;
    }

    public void setSettingMap(Map<String, String> settingMap) {
        this.settingMap = settingMap;
    }

    public void putSetting(SettingElement setting) {
        this.settingMap.put(setting.getName(), setting.getValue());
    }

    public String getSettingValue(String name) {
        return this.settingMap.get(name);
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public void putProperty(PropertyElement property) {
        this.propertyMap.put(property.getName(), property.getValue());
    }

    public String getPropertyValue(String name) {
        return this.propertyMap.get(name);
    }

    public TargetFilesElement getTargetFiles() {
        return targetFiles;
    }

    public void setTargetFiles(TargetFilesElement targetFiles) {
        this.targetFiles = targetFiles;
    }

    public List<DatabaseElement> getDatabases() {
        return databases;
    }

    public void setDatabases(List<DatabaseElement> databases) {
        this.databases = databases;
    }

    public void addDatabase(DatabaseElement databaseElement) {
        this.databases.add(databaseElement);
    }
}
