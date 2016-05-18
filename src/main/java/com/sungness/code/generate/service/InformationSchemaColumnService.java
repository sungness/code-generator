package com.sungness.code.generate.service;

import com.sungness.code.generate.dao.InformationSchemaColumnMapper;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;
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
 * Mysql信息架构（information_schema）-列 业务处理类
 * Created by wanghongwei on 4/7/16.
 */
@Service
public class InformationSchemaColumnService
        extends LongPKBaseService<InformationSchemaColumn> {

    private static final Logger log =
            LoggerFactory.getLogger(InformationSchemaColumnService.class);

    @Autowired
    private InformationSchemaColumnMapper informationSchemaColumnMapper;

    /**
     * 获取数据层mapper接口对象，子类必须实现该方法
     *
     * @return GenericMapper<E, PK> 数据层mapper接口对象
     */
    @Override
    protected GenericMapper<InformationSchemaColumn, Long> getMapper() {
        return informationSchemaColumnMapper;
    }

    /**
     * 获取指定数据库名、表名的所有列信息
     * @param tableSchema String 数据库名
     * @param tableName String 表名
     * @return List<InformationSchemaColumn> 表中列信息列表
     */
    public List<InformationSchemaColumn> getList(
            String tableSchema, String tableName) {
        Map<String, Object> conditions = new HashMap<>();
        conditions.put("tableSchema", tableSchema);
        conditions.put("tableName", tableName);
        return informationSchemaColumnMapper.getList(conditions);
    }
}
