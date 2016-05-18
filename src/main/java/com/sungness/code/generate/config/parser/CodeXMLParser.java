package com.sungness.code.generate.config.parser;

import com.sungness.code.generate.config.constants.CodeXMLConstants;
import com.sungness.code.generate.exception.ConfigException;
import com.sungness.code.generate.model.element.CodeElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.Map;

/**
 * 配置文件解析器主类，负责将整个配置文件解析映射成 CodeElement 对象。
 * Created by wanghongwei on 11/14/15.
 */
@Service
public class CodeXMLParser implements CodeXMLConstants {

    private static final Logger log = LoggerFactory.getLogger(CodeXMLParser.class);

    private Map<String, XMLParser> parsers;

    @Autowired
    public void setParsers(Map<String, XMLParser> parsers) {
        this.parsers = parsers;
    }

    /**
     * 将配置文档对象转换成ConfigModel模型对象
     * @param doc Document 文档对象
     * @return ConfigModel 配置模型对象
     */
    public CodeElement parse(Document doc) {
        CodeElement model = new CodeElement();
        try {
            if (doc == null) {
                throw new ConfigException("配置Document对象为空");
            }
            Element rootElement = doc.getDocumentElement();
            if (rootElement == null) {
                throw new ConfigException("配置Document对象无效，无根节点 code");
            }
            if (!rootElement.getTagName().equalsIgnoreCase(ELE_CODE)) {
                throw new ConfigException("配置文档必须以 code 为根节点");
            }
            NodeList nodeList = rootElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    Element subElement = (Element)node;
                    XMLParser parser = parsers.get(subElement.getTagName() + "Parser");
                    if (parser == null) {
                        throw new ConfigException("标签名无效，没有相应的解析器：tagName="
                                + subElement.getTagName());
                    }
                    parser.parse(subElement, model);
                }
            }
        } catch (Exception e) {
            log.error("处理模板Document失败", e);
            throw new ConfigException("处理模板Document失败", e);
        }
        return model;
    }
}
