<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sg" uri="http://www.sungness.com/tags" %>
<s:url value="/manage/business/${modulePath}${viewPath}/save" var="saveURL"/>
<form:form commandName="${table.camelCaseName}" id="edit-form" method="post" name="adminForm"
           action="<#noparse>${</#noparse>saveURL<#noparse>}</#noparse>" class="form-validate form-horizontal">
    <fieldset>
        <legend><#noparse>${</#noparse>${table.camelCaseName}.${searchColumnName} != null ? ${table.camelCaseName}.${searchColumnName} : ""<#noparse>}</#noparse> 详细信息</legend>
<#list columnList as column>
    <#if column.columnName != "id">
        <div class="control-group">
            <div class="control-label">
                <label id="jform_${column.camelCaseName}-lbl" for="jform_${column.camelCaseName}" class="hasTooltip required"
                       title="<sg:message code="TIP_TITLE" ref="${table.camelCaseName}.${column.camelCaseName},${table.camelCaseName}.${column.camelCaseName}.tip"/>">
                    <s:message code="${table.camelCaseName}.${column.camelCaseName}"/><span class="star">&#160;*</span></label>
            </div>
            <div class="controls">
                <form:input path="${column.camelCaseName}" id="jform_${column.camelCaseName}" class="required" size="30"
                            required="required" aria-required="true" />
            </div>
        </div>
    </#if>
</#list>
        <div class="control-group">
            <div class="control-label">
                <label id="jform_id-lbl" for="jform_id" class="hasTooltip"
                       title="<sg:message code="TIP_TITLE" ref="${table.camelCaseName}.id,${table.camelCaseName}.id.tip"/>">
                    <s:message code="${table.camelCaseName}.id"/></label>
            </div>
            <div class="controls">
                <form:input path="id" id="jform_id" class="readonly" readonly="true" /></div>
        </div>
    </fieldset>
    <input type="hidden" name="task" value="" />
</form:form>