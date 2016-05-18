package com.sungness.code.generate.model.element;

/**
 * 目标文件元素模型累
 * Created by wanghongwei on 11/8/15.
 */
public class TargetFileElement extends BaseElement {
    private static final long serialVersionUID = 1734606987079846863L;
    /** 对应的模板id */
    private String ftlid;
    /** 文件名表达式 */
    private String nameExpression;
    /** 目标文件所在路径 */
    private String path;
    /** 是否覆盖已生成文件，true-覆盖，false-不覆盖，默认为false */
    private String override;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNameExpression() {
        return nameExpression;
    }

    public void setNameExpression(String nameExpression) {
        this.nameExpression = nameExpression;
    }

    public String getFtlid() {
        return ftlid;
    }

    public void setFtlid(String ftlid) {
        this.ftlid = ftlid;
    }

    public String getOverride() {
        return override;
    }

    public void setOverride(String override) {
        this.override = override;
    }
}