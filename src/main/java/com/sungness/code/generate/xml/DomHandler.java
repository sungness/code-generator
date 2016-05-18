package com.sungness.code.generate.xml;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.DefaultDocumentLoader;
import org.springframework.beans.factory.xml.DocumentLoader;
import org.springframework.beans.factory.xml.PluggableSchemaResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.xml.SimpleSaxErrorHandler;
import org.w3c.dom.*;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;

import java.io.File;
import java.io.InputStream;
import java.io.StringWriter;

/**
 * XML文档处理工具，包括文档加载、合并、输出等方法
 * @author wanghongwei
 * @since v1.0 2014-08-19
 */
public class DomHandler {

    protected final Log logger = LogFactory.getLog(getClass());

    private ErrorHandler errorHandler = new SimpleSaxErrorHandler(logger);
    /** 校验模式，spring文件使用XSD，struts文件使用DTD，默认为自动 */
    private int validationMode = XmlBeanDefinitionReader.VALIDATION_AUTO;

    public void setValidationMode(int validationMode) {
        this.validationMode = validationMode;
    }

    /**
     * 从给定文件名的文件加载内容到Document对象，编码方式为默认UTF-8
     * @param fileName String 源XML文件名
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public Document loadFromFile(String fileName) throws Exception {
        logger.debug("load XML from:" + fileName);
        return loadFromFile(fileName, "UTF-8");
    }

    /**
     *
     * 从给定文件名的文件加载内容到Document对象
     * @param fileName String 源XML文件名
     * @param encoding String 编码方式
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public Document loadFromFile(String fileName, String encoding)
            throws Exception {
        Resource resource =
                new ByteArrayResource(
                        FileUtils.readFileToByteArray(new File(fileName)));
        return loadFromResource(resource, encoding);
    }

    /**
     * 从给定的字符串中加载内容到Document对象，编码方式为默认UTF-8
     * @param xmlContent String XML内容字符串
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public Document loadFromString(String xmlContent)
            throws Exception {
        return loadFromString(xmlContent, "UTF-8");
    }

    /**
     * 从给定的字符串中加载内容到Document对象，编码方式为默认UTF-8
     * @param xmlContent String XML内容字符串
     * @param encoding
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public Document loadFromString(String xmlContent, String encoding)
            throws Exception {
        return loadFromResource(
                new ByteArrayResource(xmlContent.toString().getBytes()), encoding);
    }

    /**
     * 从给定的XML资源对象中加载内容到Document对象，编码方式为默认UTF-8
     * @param resource Resource XML资源对象
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public Document loadFromResource(Resource resource) throws Exception {
        return loadFromResource(resource, "UTF-8");
    }

    /**
     * 从给定的字符串中加载内容到Document对象
     * @param resource Resource XML资源对象
     * @param encoding String 编码方式
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public Document loadFromResource(Resource resource, String encoding)
            throws Exception {
        EncodedResource encodedResource = new EncodedResource(resource, encoding);
        InputStream inputStream = encodedResource.getResource().getInputStream();
        try {
            return loadFromInputStream(inputStream, encoding);
        } finally {
            inputStream.close();
        }
    }

    /**
     * 从输入流中加载Dom文档
     * @param inputStream InputStream 输入流对象
     * @param encoding String 编码
     * @return Document Dom对象
     * @throws Exception
     */
    public Document loadFromInputStream(InputStream inputStream, String encoding)
        throws Exception {
        InputSource inputSource = new InputSource(inputStream);
        inputSource.setEncoding(encoding);
        DocumentLoader documentLoader = new DefaultDocumentLoader();
        EntityResolver entityResolver =
                new PluggableSchemaResolver(getClass().getClassLoader());
        return documentLoader.loadDocument(
                inputSource, entityResolver, this.errorHandler, validationMode, false);
    }

    /**
     * 从输入流中加载Dom文档
     * @param inputStream InputStream 输入流对象
     * @return Document Dom对象
     * @throws Exception
     */
    public Document loadFromInputStream(InputStream inputStream)
        throws Exception {
        return loadFromInputStream(inputStream, "UTF-8");
    }

    /**
     * 将Document对象格式化，写入指定的xml文件中，默认编码为UTF-8
     * @param doc Document dom对象
     * @param fileName String 目标xml文件名
     * @throws java.io.IOException 文件IO异常
     */
    public void writeTofile(Document doc, String fileName)
            throws Exception {
        writeToFile(doc, fileName, "UTF-8");
    }

    /**
     * 将Document对象格式化，写入指定的xml文件中
     * @param doc Document dom对象
     * @param fileName String 目标xml文件名
     * @param encoding String 编码方式
     * @throws java.io.IOException 文件IO异常
     */
    public void writeToFile(Document doc, String fileName, String encoding)
            throws Exception {
        FileUtils.writeStringToFile(
                new File(fileName), toXML(doc), encoding);
    }

    /**
     * 将Document对象格式化，转换成xml内容字符串
     * @param source Node dom对象
     * @param encoding String 编码方式
     * @return String 转换后的xml内容字符串
     * @throws Exception 异常
     */
    public String toXML(Node source, String encoding) throws Exception {
        DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        DOMImplementationLS impls = (DOMImplementationLS) registry.getDOMImplementation("LS");

        LSOutput domOutput = impls.createLSOutput();
        domOutput.setEncoding(encoding);
        StringWriter stringWriter = new StringWriter();
        domOutput.setCharacterStream(stringWriter);

        LSSerializer domWriter = impls.createLSSerializer();

        DOMConfiguration domConfig = domWriter.getDomConfig();
        domConfig.setParameter("format-pretty-print", true);
        domConfig.setParameter("element-content-whitespace", true);
        domWriter.setNewLine("\r\n");
        domConfig.setParameter("cdata-sections", Boolean.TRUE);

        domWriter.write(source, domOutput);
        return domOutput.getCharacterStream().toString();
    }

    /**
     * 将Document对象格式化，转换成xml内容字符串
     * @param source Node dom对象
     * @return String 转换后的xml内容字符串
     * @throws Exception 异常
     */
    public String toXML(Node source) throws Exception {
        return toXML(source, "UTF-8");
    }

    /**
     * 将指定的源dom文档中的节点合并到目标dom文档中相应的节点，其中attrName和attrValue
     * 用于查找要合并的节点使用
     * @param destDoc Document 目标文档对象
     * @param srcDoc Document 源文档对象
     * @param mergeNode DomMergeNode 合并节点的匹配条件）
     * @throws org.w3c.dom.DOMException
     */
    public void mergeDocument(Document destDoc, Document srcDoc,
            DomMergeNode mergeNode) throws DOMException {
        if (destDoc == null || srcDoc == null) {
            return;
        }
        Element destRoot = getMergeRoot(destDoc, mergeNode);
        Element srcRoot = getMergeRoot(srcDoc, mergeNode);
        if (destRoot == null || srcRoot == null) {
            return;
        }
        if (!destRoot.getNodeName().equals(srcRoot.getNodeName())) {
            throw new DOMException(
                    DOMException.INVALID_ACCESS_ERR, "源文档与目标文档结构不匹配");
        }
        NodeList srcNodeList = srcRoot.getChildNodes();
        for (int i = 0; i < srcNodeList.getLength(); i++) {
            Node srcNode = srcNodeList.item(i);
            if (srcNode instanceof Element) {
                Element srcElement = (Element)srcNode;
                mergeSubNode(destRoot, srcElement, mergeNode.isOverWrite(),
                        mergeNode.getChildNodeUniqueAttrName());
            }
        }
    }

    /**
     * 根据给定的查询条件从dom文档获取节点Element
     * @param doc Document dom文档对象
     * @param qryCondition DomMergeNode 查询条件对象
     * @return Element 匹配的节点Element，匹配不到返回null
     */
    public Element getMergeRoot(Document doc, DomMergeNode qryCondition) {
        if (doc == null) {
            return null;
        }
        if (qryCondition == null || qryCondition.getMergeNodeName() == null) {
            return doc.getDocumentElement();
        }
        NodeList nodeList = doc.getElementsByTagName(qryCondition.getMergeNodeName());
        if (nodeList.getLength() > 0) {
            if (qryCondition.getMatchAttrName() == null
                || qryCondition.getMatchAttrName().isEmpty()
                || qryCondition.getMatchAttrValue() == null
                || qryCondition.getMatchAttrValue().isEmpty()) {
                return nodeList.item(0) instanceof Element ? (Element)nodeList.item(0) : null;
            }
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node instanceof Element) {
                    Element ele = (Element)node;
                    if (ele.getAttribute(qryCondition.getMatchAttrName()).equals(
                            qryCondition.getMatchAttrValue())) {
                        return ele;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 将子节点Element和并入目标节点中
     * @param destRoot Element 目标节点对象
     * @param srcSubElement Element 子几点对象
     * @param uniqueAttrName String 子节点唯一标识属性名，按此属性决定更新还是新增
     * @param overWrite boolean 是否覆盖已有节点，true 覆盖，false 不覆盖
     */
    private void mergeSubNode(Element destRoot, Element srcSubElement,
            boolean overWrite, String uniqueAttrName) {
        boolean isNewNode = true;
        NodeList destNodeList = destRoot.getChildNodes();
        for (int j = 0; j < destNodeList.getLength(); j++) {
            Node oldNode = destNodeList.item(j);
            if (oldNode instanceof Element) {
                Element oldElement = (Element)oldNode;
                if (uniqueAttrName != null && !uniqueAttrName.isEmpty()
                    && oldElement.getAttribute(uniqueAttrName).equals(
                            srcSubElement.getAttribute(uniqueAttrName))) {
                    isNewNode = false;
                    if (overWrite) {
                        Node importNode =
                                destRoot.getOwnerDocument().importNode(
                                        srcSubElement, true);
                        destRoot.replaceChild(importNode, oldNode);
                    } else {
                        logger.warn("Original node found but overWrite is false, skip only.");
                    }
                    break;
                }
            }
        }
        if (isNewNode) {
            destRoot.appendChild(
                    destRoot.getOwnerDocument().importNode(
                            srcSubElement, true));
        }
    }
}
