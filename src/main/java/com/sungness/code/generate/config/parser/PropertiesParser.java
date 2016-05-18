package com.sungness.code.generate.config.parser;

import com.sungness.code.generate.exception.ConfigException;
import com.sungness.code.generate.model.element.BaseElement;
import com.sungness.code.generate.model.element.CodeElement;
import com.sungness.code.generate.model.element.PropertyElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * 系统属性标签解析器
 * Created by wanghongwei on 11/14/15.
 */
@Service
public class PropertiesParser extends BaseParser {

    private static final Logger log = LoggerFactory.getLogger(SettingsParser.class);

    /**
     * 从文档元素对象中解析基础子对象
     * @param element Element 文档元素对象
     * @param model BaseElement 基础子对象
     */
    @Override
    public void parse(Element element, BaseElement model) {
        if (element == null) {
            throw new ConfigException("文档元素为空，解析模型对象失败");
        }
        CodeElement codeElement = (CodeElement)model;
        List<Element> subElements = DomUtils.getChildElementsByTagName(element, ELE_PROPERTY);
        for (Element subElement: subElements) {
            PropertyElement propertyElement = new PropertyElement();
            super.parse(subElement, propertyElement);
            propertyElement.setValue(subElement.getAttribute(ATT_VALUE));
            codeElement.putProperty(propertyElement);
        }
    }
}
