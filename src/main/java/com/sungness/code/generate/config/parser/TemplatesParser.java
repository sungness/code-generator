package com.sungness.code.generate.config.parser;

import com.sungness.code.generate.exception.ConfigException;
import com.sungness.code.generate.model.element.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

import java.util.List;

/**
 * Templates标签解析器
 * Created by wanghongwei on 2015/04/10.
 */
@Service
public class TemplatesParser extends BaseParser {

    private static final Logger log = LoggerFactory.getLogger(TemplatesParser.class);

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
        TemplatesElement templatesElement = new TemplatesElement();
        super.parse(element, templatesElement);
        templatesElement.setPath(element.getAttribute(ATT_PATH));

        List<Element> subElements = DomUtils.getChildElementsByTagName(element, ELE_TEMPLATE);
        for (Element subElement: subElements) {
            TemplateElement templateElement = new TemplateElement();
            super.parse(subElement, templateElement);
            templateElement.setPath(subElement.getAttribute(ATT_PATH));
            templatesElement.addTemplate(templateElement);
        }
        codeElement.setTemplates(templatesElement);
    }
}