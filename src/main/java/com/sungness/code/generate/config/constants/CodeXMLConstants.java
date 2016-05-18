package com.sungness.code.generate.config.constants;

/**
 * 代码生成器配置XML常量定义接口类，包含配置XML内的所有元素名称、属性等
 * @author wanghongwei
 * Created by wanghongwei on 11/14/15.
 */
public interface CodeXMLConstants {
    String ELE_CODE = "code";
    String ELE_SETTINGS = "settings";
    String ELE_SETTING = "setting";
    String ELE_PROPERTIES = "properties";
    String ELE_PROPERTY = "property";
    String ELE_TEMPLATES = "templates";
    String ELE_TEMPLATE = "template_bak";
    String ELE_TARGETFILES = "targetFiles";
    String ELE_TARGETFILE = "targetFiles";
    String ELE_DATABASE = "database";
    String ELE_TABLE = "table";
    String ELE_EXCLUDE = "exclude";

    String ATT_ID = "id";
    String ATT_NAME = "name";
    String ATT_VALUE = "value";
    String ATT_PATH = "path";
    String ATT_PACKAGE = "package";
    String ATT_TARGETFILE_TEMPLATE = "ftlid";
    String ATT_TARGETFILE_NAMEEXPR = "nameExpression";
    String ATT_TARGETFILE_OVERRIDE = "override";
}
