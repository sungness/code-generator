package com.msymobile.urp.service${modulePkg};

import com.msymobile.core.dao.GenericMapper;
import com.msymobile.core.service.LongPKBaseService;
import com.msymobile.urp.dao${modulePkg}.${table.upperCaseName}Mapper;
import com.msymobile.urp.model${modulePkg}.${table.upperCaseName};
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* ${table.clearComment} 业务处理类
*
* Created by wanghongwei on ${genDate}.
*/
@Service
public class ${table.upperCaseName}Service
        extends LongPKBaseService<${table.upperCaseName}> {
    private static final Logger log = LoggerFactory.getLogger(${table.upperCaseName}Service.class);

    @Autowired
    private ${table.upperCaseName}Mapper ${table.camelCaseName}Mapper;

    /**
    * 获取数据层mapper接口对象，子类必须实现该方法
    *
    * @return GenericMapper<E, PK> 数据层mapper接口对象
    */
    @Override
    protected GenericMapper<${table.upperCaseName}, Long> getMapper() {
        return ${table.camelCaseName}Mapper;
    }

    /**
    * 根据id获取对象，如果为空，返回空对象
    * @param id Long 公司id
    * @return ${table.upperCaseName} ${table.clearComment}对象
    */
    public ${table.upperCaseName} getSafety(Long id) {
        ${table.upperCaseName} ${table.camelCaseName} = get(id);
        if (${table.camelCaseName} == null) {
            ${table.camelCaseName} = new ${table.upperCaseName}();
        }
        return ${table.camelCaseName};
    }
}