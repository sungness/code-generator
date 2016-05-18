package com.sungness.code.generate.xml;

/**
 * dom文档合并条件类
 * @author wanghongwei
 * @since v1.0 2014-08-19
 */
public class DomMergeNode {
    /**要合并的节点名称*/
    private String mergeNodeName;
    /**查询合并节点的属性名*/
    private String matchAttrName;
    /**查询合并节点的属性值*/
    private String matchAttrValue;
    /**子节点唯一标识属性名*/
    private String childNodeUniqueAttrName;
    /**覆盖已有节点*/
    private boolean overWrite;

    public String getMergeNodeName() {
        return mergeNodeName;
    }
    public void setMergeNodeName(String mergeNodeName) {
        this.mergeNodeName = mergeNodeName;
    }
    public String getMatchAttrName() {
        return matchAttrName;
    }
    public void setMatchAttrName(String matchAttrName) {
        this.matchAttrName = matchAttrName;
    }
    public String getMatchAttrValue() {
        return matchAttrValue;
    }
    public void setMatchAttrValue(String matchAttrValue) {
        this.matchAttrValue = matchAttrValue;
    }
    public String getChildNodeUniqueAttrName() {
        return childNodeUniqueAttrName;
    }
    public void setChildNodeUniqueAttrName(String childNodeUniqueAttrName) {
        this.childNodeUniqueAttrName = childNodeUniqueAttrName;
    }
    public boolean isOverWrite() {
        return overWrite;
    }
    public void setOverWrite(boolean overWrite) {
        this.overWrite = overWrite;
    }

}
