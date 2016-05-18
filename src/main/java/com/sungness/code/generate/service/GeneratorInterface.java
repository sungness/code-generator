package com.sungness.code.generate.service;

import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;

import java.util.List;

/**
 * 代码生成器接口
 * Created by wanghongwei on 4/7/16.
 */
public interface GeneratorInterface {
    /**
     * 代码生成器调用主方法
     * @param table InformationSchemaTable Schema 表对象
     * @param columnList List<InformationSchemaColumn> Schema 列列表对象
     */
    void execute(InformationSchemaTable table,
                 List<InformationSchemaColumn> columnList);
}
