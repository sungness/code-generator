package com.sungness.code.generate.model;

import com.sungness.code.generate.model.element.DatabaseElement;
import com.sungness.code.generate.model.element.TableElement;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;

import java.io.Serializable;
import java.util.List;

/**
 * 代码生成表模型，由TableElement、InformationSchemaTable、
 *  InformationSchemaColumn组合而成。
 * Created by wanghongwei on 11/16/15.
 */
public class TableModel implements Serializable {
    private static final long serialVersionUID = -2951266586459680086L;
    /** 配置文档中的数据库元素对象 */
    private DatabaseElement databaseElement;
    /** 配置文档中的表元素对象 */
    private TableElement tableElement;
    /** 信息架构表对象 */
    private InformationSchemaTable informationSchemaTable;
    /** 信息架构列 列表对象 */
    private List<InformationSchemaColumn> informationSchemaColumns;

    public TableElement getTableElement() {
        return tableElement;
    }

    public void setTableElement(TableElement tableElement) {
        this.tableElement = tableElement;
    }

    public InformationSchemaTable getInformationSchemaTable() {
        return informationSchemaTable;
    }

    public void setInformationSchemaTable(InformationSchemaTable informationSchemaTable) {
        this.informationSchemaTable = informationSchemaTable;
    }

    public List<InformationSchemaColumn> getInformationSchemaColumns() {
        return informationSchemaColumns;
    }

    public void setInformationSchemaColumns(List<InformationSchemaColumn> informationSchemaColumns) {
        this.informationSchemaColumns = informationSchemaColumns;
    }

    public DatabaseElement getDatabaseElement() {
        return databaseElement;
    }

    public void setDatabaseElement(DatabaseElement databaseElement) {
        this.databaseElement = databaseElement;
    }
}
