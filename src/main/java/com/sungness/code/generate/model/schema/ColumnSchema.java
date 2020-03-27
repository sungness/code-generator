package com.sungness.code.generate.model.schema;

public class ColumnSchema {
    private String columnName = "";

    private String comment = "";

    private String colType = "";

    private String colDbType = "";

    private boolean isPK = false;

    private long length = 0L;

    private int precision = 0;

    private int scale = 0;

    private int autoGen = 0;

    private boolean isNotNull = false;

    private String displayDbType = "";

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String paramString) {
        this.columnName = paramString;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String paramString) {
        this.comment = paramString;
    }

    public String getColType() {
        return this.colType;
    }

    public void setColType(String paramString) {
        this.colType = paramString;
    }

    public String getColDbType() {
        return this.colDbType;
    }

    public void setColDbType(String paramString) {
        this.colDbType = paramString;
    }

    public boolean getIsPK() {
        return this.isPK;
    }

    public void setIsPK(boolean paramBoolean) {
        this.isPK = paramBoolean;
    }

    public long getLength() {
        return this.length;
    }

    public void setLength(long paramLong) {
        this.length = paramLong;
    }

    public int getPrecision() {
        return this.precision;
    }

    public void setPrecision(int paramInt) {
        this.precision = paramInt;
    }

    public int getScale() {
        return this.scale;
    }

    public void setScale(int paramInt) {
        this.scale = paramInt;
    }

    public int getAutoGen() {
        return this.autoGen;
    }

    public void setAutoGen(int paramInt) {
        this.autoGen = paramInt;
    }

    public boolean getIsNotNull() {
        return this.isNotNull;
    }

    public void setIsNotNull(boolean paramBoolean) {
        this.isNotNull = paramBoolean;
    }

    public String getDisplayDbType() {
        return this.displayDbType;
    }

    public void setDisplayDbType(String paramString) {
        this.displayDbType = paramString;
    }

    public String getDisplay() {
        return "";
    }

    public String toString() {
        return "ColumnModel [columnName=" + this.columnName + ", comment="
                + this.comment + ", colType=" + this.colType + ", colDbType="
                + this.colDbType + ", isPK=" + this.isPK + ", length="
                + this.length + ", precision=" + this.precision + ", scale="
                + this.scale + ", autoGen=" + this.autoGen + ", isNotNull="
                + this.isNotNull + ", displayDbType=" + this.displayDbType
                + "]";
    }
}
