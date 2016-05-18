package com.sungness.code.generate.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


/**
 * XML文档处理静态方法工具集，将DomHandler.java中的各方法封装成静态方法
 * @author wanghongwei
 * @since v1.0 2014-08-19
 */
public class DomUtil {
    private static final Log logger = LogFactory.getLog(DomUtil.class);

    /**
     * 获取处理器对象
     * @return DomHandler dom处理器对象
     */
    public static DomHandler getHandler() {
        return getHandler(XmlBeanDefinitionReader.VALIDATION_NONE);
    }

    /**
     * 获取处理器对象
     * @param validation int 验证类型
     * @return DomHandler dom处理器对象
     */
    public static DomHandler getHandler(int validation) {
        DomHandler docUtil = new DomHandler();
        docUtil.setValidationMode(validation);
        return docUtil;
    }

    /**
     * 从给定的字符串中加载内容到Document对象，编码方式为默认UTF-8
     * @param xmlContent String XML内容字符串
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public static Document loadFromString(String xmlContent) throws Exception {
        return loadFromString(xmlContent, "UTF-8");
    }

    /**
     * 从给定的字符串中加载内容到Document对象，编码方式为默认UTF-8
     * @param xmlContent String XML内容字符串
     * @param encoding
     * @return Document w3c dom对象
     * @throws Exception 文件IO错误
     */
    public static Document loadFromString(String xmlContent, String encoding)
            throws Exception {
        return getHandler().loadFromString(xmlContent, encoding);
    }

    /**
     * 从文件中加载内容到Document对象
     * @param fileName String 文件名称
     * @param encoding String 编码方式
     * @return Document 文档对象
     * @throws Exception
     */
    public static Document loadFromFile(String fileName, String encoding)
            throws Exception {
        return getHandler().loadFromFile(fileName, encoding);
    }

    /**
     * 从文件中加载内容到Document对象，使用默认UTF-8编码
     * @param fileName String 文件名称
     * @return Document 文档对象
     * @throws Exception
     */
    public static Document loadFromFile(String fileName)
            throws Exception {
        return loadFromFile(fileName, "UTF-8");
    }

    /**
     * 从系统的classpath路径中的文件加载Document对象
     * @param fileName String 文件名
     * @param encoding String 编码
     * @return Document 文档对象
     * @throws Exception
     */
    public static Document loadFromClassPathFile(String fileName, String encoding)
            throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return getHandler().loadFromResource(
                resourceLoader.getResource("classpath:" + fileName), encoding);
    }

    /**
     * 从系统的classpath路径中的文件加载Document对象，默认使用UTF-8编码
     * @param fileName String 文件名
     * @return Document 文档对象
     * @throws Exception
     */
    public static Document loadFromClassPathFile(String fileName)
            throws Exception {
        return loadFromClassPathFile(fileName, "UTF-8");
    }

    /**
     * 从Resource中加载DOM文档
     * @param resource Resource 资源对象
     * @return Document Dom对象
     * @throws Exception
     */
    public static Document loadFromResource(Resource resource) throws Exception {
        return loadFromResource(resource, "UTF-8");
    }

    /**
     * 从Resource中加载DOM文档
     * @param resource Resource 资源对象
     * @param encoding String 编码方式
     * @return Document Dom对象
     * @throws Exception
     */
    public static Document loadFromResource(Resource resource, String encoding) throws Exception {
        return getHandler().loadFromResource(resource, encoding);
    }

    /**
     * 从输入流中加载Dom文档
     * @param inputStream InputStream 输入流对象
     * @param encoding String 编码
     * @return Document Dom对象
     * @throws Exception
     */
    public static Document loadFromInputStream(InputStream inputStream, String encoding)
            throws Exception {
        return getHandler().loadFromInputStream(inputStream, encoding);
    }

    /**
     * 从输入流中加载Dom文档
     * @param inputStream InputStream 输入流对象
     * @return Document Dom对象
     * @throws Exception
     */
    public static Document loadFromInputStream(InputStream inputStream)
            throws Exception {
        return loadFromInputStream(inputStream, "UTF-8");
    }

    /**
     * 获取Element中指定名称的第一个元素
     * @param element Element 文档对象模型
     * @param tagName String 标签名
     * @return Element 第一个元素对象
     */
    public static Element getFirstElementByTagName(Element element, String tagName) {
        try {
        logger.debug(toXML(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
        NodeList nodeList = element.getElementsByTagName(tagName);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element) {
                Element subElement = (Element)node;
                return subElement;
            }
        }
        return null;
    }

    /**
     * 获取Document中指定名称的第一个元素
     * @param doc Document 文档对象模型
     * @param tagName String 标签名
     * @return Element 第一个元素对象
     */
    public static Element getFirstElementByTagName(Document doc, String tagName) {
        return getFirstElementByTagName(doc.getDocumentElement(), tagName);
    }

    /**
     * 根据标签名称获取标签的textContent
     * @param element Element 父节点元素对象
     * @param tagName String 标签名字
     * @return String 节点的内容
     */
    public static String getTextByTagName(Element element, String tagName) {
        return DomUtil.getFirstElementByTagName(element, tagName).getTextContent();
    }

    /**
     * 将dom对象转换成xml内容字符串
     * @param source Node dom节点对象
     * @return String 转换后的XML内容
     * @throws Exception
     */
    public static String toXML(Node source) throws Exception {
        DomHandler handler = new DomHandler();
        return handler.toXML(source);
    }

    public static Document createDocument() throws ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder db = dbf.newDocumentBuilder();
        return db.newDocument();
    }

    public static String validXmlBySchema(String xml, String schema) {
        String result = "";
//        StreamSource[] schemaDocuments = /* created by your application */;
//        Source instanceDocument = /* created by your application */;
//
//        SchemaFactory sf = SchemaFactory.newInstance(
//            "http://www.w3.org/XML/XMLSchema/v1.1");
//        Schema s = sf.newSchema(schemaDocuments);
//        Validator v = s.newValidator();
//        v.validate(instanceDocument);
        return result;
    }

    public static boolean validByXSD(String xsdPath, InputStream xmlData) {
        SchemaFactory schemaFactory = SchemaFactory
                .newInstance("http://www.w3.org/2001/XMLSchema");

        File schemaFile = new File(xsdPath);
        Schema schema = null;
        try {
            schema = schemaFactory.newSchema(schemaFile);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        Validator validator = schema.newValidator();
        Source source = new StreamSource(xmlData);
        try {
            validator.validate(source);
        } catch (Exception ex) {
            logger.info(ex.getMessage());
            return false;
        }
        return true;
    }

    public static boolean validByXSD(String xsdPath, String xmlFileName) {
        try {
            ResourceLoader resourceLoader = new DefaultResourceLoader();
            InputStream xmlData = resourceLoader.getResource(xmlFileName).getInputStream();
            String schemaFileName = resourceLoader.getResource(xsdPath).getFilename();
            return validByXSD(schemaFileName, xmlData);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static File getResourceFile(String fileName) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(fileName).getFile();
    }

    public static String getResourceFileName(String fileName) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource(fileName).getFilename();
    }
}
