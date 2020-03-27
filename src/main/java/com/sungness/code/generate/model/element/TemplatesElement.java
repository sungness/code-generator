package com.sungness.code.generate.model.element;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 模板容器元素模型基类
 * Created by wanghongwei on 2015/04/10。
 */
public class TemplatesElement extends BaseElement {
    private static final long serialVersionUID = 2602119977035719057L;
    /** 模板所在根路径 */
    private String path;

    /** 模版Map */
    private Map<String, TemplateElement> templates;

    public TemplatesElement() {
        templates = new LinkedHashMap<>();
    }

    public void addTemplate(TemplateElement template) {
        templates.put(template.getId(), template);
    }

    public TemplateElement getTemplate(String id) {
        return templates.get(id);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, TemplateElement> getTemplates() {
        return templates;
    }

    public void setTemplates(Map<String, TemplateElement> templates) {
        this.templates = templates;
    }
}