package com.sungness.code.generate.dao;

import com.sungness.core.dao.LongPKBaseMapper;
import com.sungness.code.generate.model.schema.mysql.InformationSchemaColumn;

/**
 * Mysql信息架构（information_schema）-列 MyBatis映射接口类
 * Created by wanghongwei on 4/7/16.
 */
public interface InformationSchemaColumnMapper
        extends LongPKBaseMapper<InformationSchemaColumn> {
}
