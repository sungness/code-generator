package com.sungness.code.generate.model.element;

import java.util.ArrayList;
import java.util.List;

/**
 * Files元素模型类
 * Created by wanghongwei on 2015/04/10.
 */
public class FilesElement extends BaseElement {
    //生成的文件的目标即路径
    private String basePath = "";
    //文件元素列表
    private List<FileElement> fileElementList = new ArrayList<>();

    public FilesElement() {
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public List<FileElement> getFileElementList() {
        return fileElementList;
    }

    public void setFileElementList(List<FileElement> fileElementList) {
        this.fileElementList = fileElementList;
    }

    public void addFileElement(FileElement fileElement) {
        this.fileElementList.add(fileElement);
    }

    public void addFile(String template, String filename,
                        String dir, boolean sub, boolean append, String insertTag,
                        String startTag, String endTag, boolean override) {
        FileElement file1 = new FileElement(template, filename, dir, sub, append,
                insertTag, startTag, endTag, override);
        this.fileElementList.add(file1);
    }

}
