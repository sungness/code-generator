package com.sungness.code.generate.dao;

import com.sungness.code.generate.model.schema.mysql.InformationSchemaTable;
import com.sungness.core.dao.LongPKBaseMapper;

/**
 * Mysql信息架构（information_schema）-表 MyBatis映射接口类
 * Created by wanghongwei on 4/7/16.
 */
public interface InformationSchemaTableMapper
        extends LongPKBaseMapper<InformationSchemaTable> {
}
