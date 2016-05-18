package com.sungness.code.generate.config.parser;

import com.sungness.code.generate.exception.ConfigException;
import com.sungness.code.generate.model.element.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Database标签解析器
 * Created by wanghongwei on 2015/04/10.
 */
@Service
public class DatabaseParser extends BaseParser {

    private static final Logger log = LoggerFactory.getLogger(DatabaseParser.class);

    /**
     * 从文档元素对象中解析对象
     * @param element Element 文档元素对象
     * @param model BaseElement 基础子对象
     */
    @Override
    public void parse(Element element, BaseElement model) {
        if (element == null) {
            throw new ConfigException("文档元素为空，解析模型对象失败");
        }
        CodeElement codeElement = (CodeElement)model;
        DatabaseElement databaseElement = new DatabaseElement();
        super.parse(element, databaseElement);
        databaseElement.setPackageName(element.getAttribute(ATT_PACKAGE));

        List<Element> subElements = DomUtils.getChildElementsByTagName(element, ELE_TABLE);
        for (Element subElement: subElements) {
            TableElement tableElement = new TableElement();
            parse(subElement, tableElement);
            databaseElement.addTable(tableElement);
        }
        /** 原则上每个数据库只允许配置一个 exclude 节点 */
        Element excludeElement = DomUtils.getChildElementByTagName(element, ELE_EXCLUDE);
        if (excludeElement != null) {
            String[] excludeTableNames = StringUtils.tokenizeToStringArray(element.getTextContent(), ",; \t\n");
            for (String tName: excludeTableNames) {
                databaseElement.addExclude(tName);
            }
        }
        codeElement.addDatabase(databaseElement);
    }

    private void parse(Element element, TableElement tableElement) {
        super.parse(element, tableElement);
        List<Element> subElements = DomUtils.getChildElementsByTagName(element, ELE_PROPERTY);
        for (Element subElement: subElements) {
            PropertyElement propertyElement = new PropertyElement();
            super.parse(subElement, propertyElement);
            propertyElement.setValue(subElement.getAttribute(ATT_VALUE));
            tableElement.putProperty(propertyElement);
        }
    }
}