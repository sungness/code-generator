package com.sungness.code.generate.model.element;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 配置元素模型基类
 * Created by wanghongwei1 on 2014/8/19.
 */
public class BaseElement implements Serializable {
    private static final long serialVersionUID = -4235132249984889318L;
    /** 元素id */
    protected String id;

    /** 元素名称 */
    protected String tagName;

    /** 属性名 */
    protected String name;

    /** 属性集合<属性名, 属性值>对 */
    protected Map<String, String> attributeMap = new LinkedHashMap<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Map<String, String> getAttributeMap() {
        return attributeMap;
    }

    public void setAttributeMap(Map<String, String> attributeMap) {
        this.attributeMap = attributeMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 根据属性名称获取属性值
     * @param attrName String 属性名称
     * @return String 属性值，如果不存在，返回 null
     */
    public String getAttrValueByName(String attrName) {
        return attributeMap.get(attrName);
    }
}
