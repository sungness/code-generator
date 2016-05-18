package com.sungness.code.generate.model.element;

import java.util.HashMap;
import java.util.Map;

public class TableElement extends BaseElement {
    /** 表级别property，用于替换代码中的占位符 */
    private Map<String, String> propertes = new HashMap<>();

    public Map<String, String> getPropertes() {
        return propertes;
    }

    public void setPropertes(Map<String, String> propertes) {
        this.propertes = propertes;
    }

    public void putProperty(PropertyElement propertyElement) {
        this.propertes.put(propertyElement.getName(), propertyElement.getValue());
    }
}