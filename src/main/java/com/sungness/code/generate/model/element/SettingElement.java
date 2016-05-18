package com.sungness.code.generate.model.element;

/**
 * 系统配置元素模型累
 * Created by wanghongwei on 11/8/15.
 */
public class SettingElement extends BaseElement {
    private static final long serialVersionUID = 3761678761483536209L;
    /** 设置参数值 */
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
