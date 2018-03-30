package ${packageBase}.dao${modulePkg};

import com.sungness.core.dao.LongPKBaseMapper;
import ${packageBase}.model${modulePkg}.${table.upperCaseName};

/**
* ${table.clearComment} MyBatis 映射接口类
*
* Created by wangzhiming on ${genDate}.
*/
public interface ${table.upperCaseName}Mapper
        extends LongPKBaseMapper<${table.upperCaseName}> {
}