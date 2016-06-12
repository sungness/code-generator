<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sg" uri="http://www.sungness.com/tags" %>
<%@ taglib prefix="sform" uri="http://www.sungness.com/tags/form" %>
<s:url value="/manage/businessdata/${modulePath}${viewPath}/list" var="listURL"/>
<s:url value="/manage/businessdata/${modulePath}${viewPath}/detail?backURL=<#noparse>${pagination.encodedCurrentPageURL}</#noparse>" var="detailURL"/>
<s:url value="/manage/businessdata/${modulePath}${viewPath}/edit?backURL=<#noparse>${pagination.encodedCurrentPageURL}</#noparse>" var="editURL"/>
<s:url value="/manage/businessdata/${modulePath}${viewPath}/delete?backURL=<#noparse>${pagination.encodedCurrentPageURL}</#noparse>" var="delURL"/>
<#noparse>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="zh-cn" lang="zh-cn" dir="ltr" >
<head>
    <%@ include file="/WEB-INF/jsp/manage/includes/meta.jsp" %>
    <title><s:message code="system.name"/> - ${commandInfo.module.value} - ${commandInfo.value}</title>
    <%@ include file="/WEB-INF/jsp/manage/includes/linksOfListHead.jsp" %>
    <%@ include file="/WEB-INF/jsp/manage/includes/scriptsOfHead.jsp" %>
    <script src="<s:url value="/media/jui/js/jquery.searchtools.min.js" />" type="text/javascript"></script>
    <script src="<s:url value="/media/system/js/multiselect.js" />" type="text/javascript"></script>
</head>
<body class="admin com_users view-users layout- task- itemid-">
<!-- Top Navigation -->
<%@ include file="/WEB-INF/jsp/manage/includes/topNavigation.jsp" %>
<!-- Header -->
<%@ include file="/WEB-INF/jsp/manage/includes/header.jsp" %>
<!-- Subheader -->
<sec:authorize access="hasPermission(#commandInfo.module, 'edit')">
    <c:set var="showNew" value="true"/>
    <c:set var="showEdit" value="true"/>
    <c:set var="showBatch" value="true"/>
</sec:authorize>
<sec:authorize access="hasPermission(#commandInfo.module, 'delete')">
    <c:set var="showDelete" value="true"/>
</sec:authorize>
<%@ include file="/WEB-INF/jsp/manage/includes/listSubHeader.jsp" %>
<!-- container-fluid -->
<div class="container-fluid container-main">
    <section id="content">
        <!-- Begin Content -->
        <div class="row-fluid">
            <div class="span12">
                <%@ include file="/WEB-INF/jsp/manage/includes/systemMessage.jsp" %>
                <form:form commandName="queryFilter" id="adminForm" name="adminForm" action="${listURL}">
                    <%@ include file="/WEB-INF/jsp/manage/includes/sidebarContainer.jsp" %>
                    <div id="j-main-container" class="span10">
                        <div class="js-stools clearfix">
</#noparse>
                            <c:set var="searchPath" value="filter[${searchColumnName}]"/>
                            <c:set var="hideSearchTools" value="true"/>
                            <%@ include file="/WEB-INF/jsp/manage/includes/filter/searchTools.jsp" %>
                        </div>
                        <div style="overflow-y: auto;">
                        <table class="table table-striped" id="userList">
                            <thead>
                            <tr>
                            <#assign thCount = 2>
                            <#noparse>
                                <c:set var="fullordering" value="${queryFilter.filter.fullordering}"/>
                                <th width="1%" class="nowrap center">
                                    <sform:checkall id="checkall-toggle"/>
                                </th>
                            </#noparse>
                            <#list columnList as column>
                                <#if column.columnName != "id">
                                <th width="20%" class="<#if thCount == 2>left<#else>nowrap hidden-phone</#if>">
                                    <sg:thlink name="${table.camelCaseName}.${column.camelCaseName}" order="a.${column.columnName}" fullOrdering="<#noparse>${fullordering}</#noparse>"/>
                                </th>
                                    <#assign thCount = thCount + 1>
                                </#if>
                            </#list>
                                <th width="1%" class="nowrap hidden-phone">
                                    <sg:thlink name="${table.camelCaseName}.id" order="a.id" fullOrdering="<#noparse>${fullordering}</#noparse>"/>
                                </th>
                                <th width="15%" class="nowrap center">操作</th>
                            </tr>
                            </thead>
                            <tfoot>
                            <tr>
                                <c:set var="columnCount" value="${thCount + 1}"/>
                            <#noparse>
                                <td colspan="${columnCount}">
                                    <%@ include file="/WEB-INF/jsp/manage/includes/pagination.jsp" %>
                                </td>
                            </#noparse>
                            </tr>
                            </tfoot>
                            <tbody>
                            <c:forEach items="<#noparse>${</#noparse>${table.camelCaseName}List<#noparse>}</#noparse>" varStatus="status">
                                <c:set var="${table.camelCaseName}" value="<#noparse>${status.current}</#noparse>"/>
                            <tr class="<#noparse>row${status.index % 2}</#noparse>">
                                <td class="nowrap center">
                                    <input type="checkbox" id="cb-<#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>" name="id"
                                           value="<#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>" onclick="Joomla.isChecked(this.checked);" />
                                </td>
                            <#assign tdCount = 2>
                            <#list columnList as column>
                                <#if column.columnName != "id">
                                    <#if tdCount == 2>
                                <td>
                                    <div class="name break-word">
                                        <a href="<#noparse>${</#noparse>editURL<#noparse>}</#noparse>?id=<#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>" title="编辑${table.clearComment} <#noparse>${</#noparse>${table.camelCaseName}.${column.camelCaseName}<#noparse>}</#noparse>">
                                        <#noparse>${</#noparse>${table.camelCaseName}.${column.camelCaseName}<#noparse>}</#noparse></a>
                                    </div>
                                </td>
                                    <#else>
                                <td class="nowrap hidden-phone"><#noparse>${</#noparse>${table.camelCaseName}.${column.camelCaseName}<#noparse>}</#noparse></td>
                                    </#if>
                                    <#assign tdCount = tdCount + 1>
                                </#if>
                            </#list>
                                <td class="hidden-phone"><#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse></td>
                                <td class="center nowrap">
                                    <sec:authorize access="hasPermission(#commandInfo.module, 'detail')">
                                        <a class="btn btn-micro active hasTooltip" href="<#noparse>${</#noparse>detailURL<#noparse>}</#noparse>&id=<#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>"
                                           title="查看 <#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>">
                                            <span class="icon-eye-open"></span></a>
                                    </sec:authorize>
                                    <sec:authorize access="hasPermission(#commandInfo.module, 'edit')">
                                        <a class="btn btn-micro active hasTooltip" href="<#noparse>${</#noparse>editURL<#noparse>}</#noparse>&id=<#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>"
                                           title="编辑 <#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>">
                                            <span class="icon-edit"></span></a>
                                    </sec:authorize>
                                    <sec:authorize access="hasPermission(#commandInfo.module, 'delete')">
                                        <a class="btn btn-micro active hasTooltip" id="list-delete-<#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>"
                                           href="<#noparse>${</#noparse>delURL}&id=<#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>" title="删除 <#noparse>${</#noparse>${table.camelCaseName}.id<#noparse>}</#noparse>">
                                            <span class="icon-trash"></span></a>
                                    </sec:authorize>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        </div>
                        <input type="hidden" name="task" value="" />
                        <input type="hidden" name="boxchecked" value="0" />
                    </div>
                </form:form>
            </div>
        </div>
        <!-- End Content -->
    </section>
</div>
<!-- Begin Status Module -->
<%@ include file="/WEB-INF/jsp/manage/includes/footer.jsp" %>
<!-- End Status Module -->
<script src="<s:url value="/js/require.js" />"></script>
<script type="text/javascript">
    var __ctx='<%= request.getContextPath() %>';
    require(['<s:url value="/js/config.js" />'], function() {
        require(['app/module'], function(module) {
            var config = {
                url: {
                    <#noparse>
                    list: "${listURL}",
                    add: "${editURL}",
                    edit: "${editURL}",
                    del: "${delURL}",
                    back: "${pagination.currentPageURL}"
                    </#noparse>
                },
                message: {
                    hide_sidebar: "<s:message code="JTOGGLE_HIDE_SIDEBAR" />",
                    show_sidebar: "<s:message code="JTOGGLE_SHOW_SIDEBAR" />",
                    multiple_tip: "<s:message code="JSELECT_MULTIPLE" />",
                    single_tip:"<s:message code="JSELECT_SINGLE" />",
                    no_results_tip: "<s:message code="JSELECT_NO_RESULTS" />",
                    filter_search_tip: "<sg:message code="JSEARCH_TITLE" ref="${table.camelCaseName}.${searchColumnName}" />",
                    no_item_selected: "<s:message code="JGLOBAL_NO_ITEM_SELECTED"/>",
                    confirm_delete: "<s:message code="JGLOBAL_CONFIRM_DELETE"/>"
                }
            };
            module.initList(config);
        });
    });
</script>
</body>
</html>