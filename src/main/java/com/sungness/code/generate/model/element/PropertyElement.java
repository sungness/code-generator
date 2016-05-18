package com.sungness.code.generate.model.element;

/**
 * 属性元素模型累
 * Created by wanghongwei on 11/8/15.
 */
public class PropertyElement extends BaseElement {
    private static final long serialVersionUID = 3761678761483536209L;
    /** 属性值 */
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
