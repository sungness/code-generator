package com.sungness.code.generate.service;

import com.sungness.code.generate.dao.InformationSchemaTableMapper;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.core.dao.GenericMapper;
import com.sungness.core.service.LongPKBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mysql信息架构（information_schema）-表 业务处理类
 * Created by wanghongwei on 4/7/16.
 */
@Service
public class InformationSchemaTableService
        extends LongPKBaseService<InformationSchemaTable> {

    private static final Logger log =
            LoggerFactory.getLogger(InformationSchemaTableService.class);

    @Autowired
    private InformationSchemaTableMapper informationSchemaTableMapper;

    /**
     * 获取数据层mapper接口对象，子类必须实现该方法
     *
     * @return GenericMapper<E, PK> 数据层mapper接口对象
     */
    @Override
    protected GenericMapper<InformationSchemaTable, Long> getMapper() {
        return informationSchemaTableMapper;
    }

    /**
     * 获取指定数据库名下的所有表信息
     * @param tableSchema String 数据库名
     * @return List<InformationSchemaTable> 表信息列表
     */
    public List<InformationSchemaTable> getList(String tableSchema) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("tableSchema", tableSchema);
        return informationSchemaTableMapper.getList(conditions);
    }

    /**
     * 获取指定数据库名、表名的表信息
     * @param tableSchema String 数据库名
     * @param tableName String 表名
     * @return List<InformationSchemaTable> 表信息列表
     */
    public List<InformationSchemaTable> getList(
            String tableSchema, String tableName) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("tableSchema", tableSchema);
        conditions.put("tableName", tableName);
        return informationSchemaTableMapper.getList(conditions);
    }
}
