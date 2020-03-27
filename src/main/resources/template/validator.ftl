package com.sungness.projectName.validator${modulePkg};

import com.sungness.projectName.model${modulePkg}.${table.upperCaseName};
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


/**
* ${table.upperCaseName} 对象验证器
* Created by wanghongwei on ${genDate}.
*/
@Component
public class ${table.upperCaseName}Validator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ${table.upperCaseName}.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
<#list columnList as column>
    <#if column.camelCaseName != "id">
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "${column.camelCaseName}", "${table.camelCaseName}.${column.camelCaseName}.error", "${column.enComment} is required.");
    </#if>
</#list>
    }
}