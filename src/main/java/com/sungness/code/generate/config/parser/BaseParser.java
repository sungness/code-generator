package com.sungness.code.generate.config.parser;

import com.sungness.code.generate.exception.ConfigException;
import com.sungness.code.generate.model.element.BaseElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * 解析器基类，负责解析BaseElement子对象部分
 * Created by wanghongwei on 2015/04/09.
 */
public class BaseParser implements XMLParser {

    protected static final Logger log = LoggerFactory.getLogger(BaseParser.class);

    /**
     * 从文档元素对象中解析基础子对象
     * @param element Element 文档元素对象
     * @param model BaseElement 基础子对象
     */
    @Override
    public void parse(Element element, BaseElement model) {
        if (element == null) {
            throw new ConfigException("文档元素为空，解析模型基础子对象失败");
        }
        model.setTagName(element.getTagName());
        model.setId(element.getAttribute(ATT_ID));
        model.setName(element.getAttribute(ATT_NAME));
        NamedNodeMap nodeMap = element.getAttributes();
        if (nodeMap != null) {
            for (int i = 0; i < nodeMap.getLength(); i++) {
                Node node = nodeMap.item(i);
                if (node != null && !node.getNodeName().equalsIgnoreCase(ATT_ID)) {
                    model.getAttributeMap().put(node.getNodeName(), node.getNodeValue());
                }
            }
        }
    }
}
