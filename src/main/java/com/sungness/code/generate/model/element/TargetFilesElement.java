package com.sungness.code.generate.model.element;

import java.util.ArrayList;
import java.util.List;


/**
 * 目标文件容器元素模型基类
 * Created by wanghongwei on 2015/11/08。
 */
public class TargetFilesElement extends BaseElement {
    private static final long serialVersionUID = 2602119977035719057L;
    /** 模板所在根路径 */
    private String path;
    /** 模版列表 */
    private List<TargetFileElement> targetFiles;

    public TargetFilesElement() {
        targetFiles = new ArrayList<>();
    }

    public void addTargetFile(TargetFileElement targetFile) {
        targetFiles.add(targetFile);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<TargetFileElement> getTargetFiles() {
        return targetFiles;
    }

    public void setTargetFiles(List<TargetFileElement> targetFiles) {
        this.targetFiles = targetFiles;
    }
}