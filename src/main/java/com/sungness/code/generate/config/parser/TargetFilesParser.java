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
 * TargetFiles标签解析器
 * Created by wanghongwei on 2015/04/10.
 */
@Service
public class TargetFilesParser extends BaseParser {

    private static final Logger log = LoggerFactory.getLogger(TargetFilesParser.class);

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
        TargetFilesElement targetFilesElement = new TargetFilesElement();
        super.parse(element, targetFilesElement);
        targetFilesElement.setPath(element.getAttribute(ATT_PATH));

        List<Element> subElements = DomUtils.getChildElementsByTagName(element, ELE_TARGETFILE);
        for (Element subElement: subElements) {
            TargetFileElement targetFileElement = new TargetFileElement();
            super.parse(subElement, targetFileElement);
            targetFileElement.setPath(subElement.getAttribute(ATT_PATH));
            targetFileElement.setFtlid(subElement.getAttribute(ATT_TARGETFILE_TEMPLATE));
            targetFileElement.setNameExpression(subElement.getAttribute(ATT_TARGETFILE_NAMEEXPR));
            targetFileElement.setOverride(subElement.getAttribute(ATT_TARGETFILE_OVERRIDE));
            targetFilesElement.addTargetFile(targetFileElement);
        }
        codeElement.setTargetFiles(targetFilesElement);
    }
}