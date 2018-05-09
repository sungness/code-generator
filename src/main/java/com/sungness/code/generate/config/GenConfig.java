package com.sungness.code.generate.config;

import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

/**
 * 代码生成器工具类，从配置文件 gen-config.properties 中提取各个配置参数
 * Created by wanghongwei on 4/8/16.
 */
public class GenConfig {
    /** 配置属性 */
    private Properties properties;

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public String getBasePath() {
        return properties.getProperty("path.base");
    }

    public String getModuleName() {
        return properties.getProperty("moduleName");
    }

    public String getModulePath() {
        if (StringUtils.isNotBlank(getModuleName())) {
            return getModuleName() + "/";
        } else {
            return "";
        }
    }

    public String getModulePkg() {
        if (StringUtils.isNotBlank(getModuleName())) {
            return "." + getModuleName();
        } else {
            return "";
        }
    }

    public String getControllerPath() {
        return properties.getProperty("path.controller");
    }

    public String buildControllerFileName(String fileName) {
        return getBasePath() + getControllerPath() + fileName;
    }

    public String getDaoJavaPath() {
        return properties.getProperty("path.dao.java");
    }

    public String buildDaoJavaFileName(String fileName) {
        return getBasePath() + getDaoJavaPath() +  getModulePath() + fileName;
    }

    public String getDaoXmlPath() {
        return properties.getProperty("path.dao.xml");
    }

    public String buildDaoXmlFileName(String fileName) {
        return getBasePath() + getDaoXmlPath() +  getModulePath() + fileName;
    }

    public String getMessagePath() {
        return properties.getProperty("path.message");
    }

    public String buildMessageFileName(String fileName) {
        return getBasePath() + getMessagePath() +  getModulePath() + fileName;
    }

    public String getModelPath() {
        return properties.getProperty("path.model");
    }

    public String buildModelFileName(String fileName) {
        return getBasePath() + getModelPath() +  getModulePath() + fileName;
    }

    public String getOrderingPath() {
        return properties.getProperty("path.ordering");
    }

    public String buildOrderingFileName(String fileName) {
        return getBasePath() + getOrderingPath() +  getModulePath() + fileName;
    }

    public String getServicePath() {
        return properties.getProperty("path.service");
    }

    public String buildServiceFileName(String fileName) {
        return getBasePath() + getServicePath() +  getModulePath() + fileName;
    }

    public String getValidatorPath() {
        return properties.getProperty("path.validator");
    }

    public String buildValidatorFileName(String fileName) {
        return getBasePath() + getValidatorPath() +  getModulePath() + fileName;
    }

    public String getJspPath() {
        return properties.getProperty("path.jsp");
    }

    public String getViewPath() {
        return properties.getProperty("path.view");
    }

    public String getAngularPath() {
        return properties.getProperty("path.angular");
    }

    public String getJsonI18n() {
        return properties.getProperty("path.angular.i18n");
    }

    public String buildJspFileName(String fileName) {
        return getBasePath() + getJspPath() + getViewPath() + "/" + fileName;
    }

    public String buildAngularFile(String fileName) {
        return getAngularPath() + getViewPath() + "/" + fileName;
    }

    public String buildJsonI18nFile(String fileName, String local) {
        return getAngularPath() + getJsonI18n() + "/" + local + "/" + fileName;
    }

    public String getDbName() {
        return properties.getProperty("dbName");
    }

    public String getTableName() {
        return properties.getProperty("tableName");
    }

    public String getSearchColumnName() {
        return properties.getProperty("searchColumnName");
    }

    public String getPackageBase() {
        return properties.getProperty("package.base");
    }
}
