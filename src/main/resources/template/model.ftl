package com.msymobile.urp.model${modulePkg};

import java.io.Serializable;

/**
* ${table.clearComment} Bean
*
* Created by wanghongwei on ${genDate}.
*/
public class ${table.upperCaseName} implements Serializable {

<#list columnList as column>
    /** ${column.columnComment} */
    private ${column.javaType} ${column.camelCaseName};
</#list>

<#list columnList as column>
    public ${column.javaType} get${column.upperCaseName}() {
        return ${column.camelCaseName};
    }

    public void set${column.upperCaseName}(${column.javaType} ${column.camelCaseName}) {
        this.${column.camelCaseName} = ${column.camelCaseName};
    }

</#list>
}