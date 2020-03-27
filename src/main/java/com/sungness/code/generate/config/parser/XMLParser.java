package com.sungness.code.generate.config.parser;

import com.sungness.code.generate.config.constants.CodeXMLConstants;
import com.sungness.code.generate.model.element.BaseElement;
import org.w3c.dom.Element;

/**
 * 配置文档解析器接口类
 * Created by wanghongwei on 2015/04/09.
 */
public interface XMLParser extends CodeXMLConstants {
    /**
     * 从文档节点中解析模型对象
     * @param element Element 文档元素对象
     * @param model BaseElement 配置模型对象
     */
    void parse(Element element, BaseElement model);
}
