package com.sungness.code.generate.model.element;

import org.apache.commons.lang3.StringUtils;

/**
 * 模板元素模型累
 * Created by wanghongwei on 11/8/15.
 */
public class TemplateElement extends BaseElement {

    private static final long serialVersionUID = -5791625500065344642L;

    /** 模板所在路径 */
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFullName() {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        StringBuilder stb = new StringBuilder(name);
        if (stb.charAt(0) == '/') {
            stb.deleteCharAt(0);
        }
        if (StringUtils.isNoneBlank(path)) {
            if (path.charAt(path.length() - 1) == '/') {
                stb.insert(0, path);
            } else {
                stb.insert(0, path + "/");
            }
            if (stb.charAt(0) == '/') {
                stb.deleteCharAt(0);
            }
        }
        return stb.toString();
    }
}
