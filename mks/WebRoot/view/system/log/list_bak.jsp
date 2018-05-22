<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>管理系统</title>
<%@ include file="../../common/IncludeHead.jsp"%>
<style type="text/css">
#tableBox{
	border-left: 1px solid #0000FF;
	border-top: 1px solid #0000FF;
	border-bottom:none;
	border-right:none;
	
}

#tableBox th{
	background-color:#EFEFEF;
	height:24px;
	font-size: 14px;
	border-left: none;
	border-top: none;
	border-bottom: 1px solid #0000FF;
	border-right: 1px solid #0000FF;
	padding: 2px;
}

#tableBox td{
	border-left: none;
	border-top: none;
	border-bottom: 1px solid #0000FF;
	border-right: 1px solid #0000FF;
	padding: 2px;
}
</style>
</head>
<body>
<form id="tableForm" action="${pageContext.request.contextPath}/admin/log/v_list.do" method="post" >
	<input type="hidden" name="pageNo" value="${page.pageNo}"/>
	<input type="hidden" name="pageSize" value="${page.pageSize}"/>
	<input type="hidden" name="sortField" value="${page.sortField}" />
	<input type="hidden" name="sortOrder" value="${page.sortOrder}" />
	<div style="margin-bottom: 20px;">
	<table id="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th>日志级别</th>
			<th>日志类型</th>
			<th>日志内容</th>
			<th>操作IP</th>
			<th>操作时间</th>
		</tr>
		<c:forEach var="item" items="${data.list}">
		<tr>
			<td>${item.logLevel}</td>
			<td>${item.logType}</td>
			<td>${item.logContent}</td>
			<td>${item.ipAddress}</td>
			<td><fmt:formatDate value="${item.createTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></th>
		</tr>
		</c:forEach>
	</table>
	</div>
</form>

<table border="0" width="100%" style="border: 1px solid #333;">
		<tr>
			<td>
			<c:if test="${page.pageNo eq 1}">
				上一页
			</c:if>
			<c:if test="${page.pageNo ne 1}">
			<a href="javascript:perPage(${page.pageNo});">上一页</a>
			</c:if>
			&nbsp;&nbsp;
			<c:if test="${page.pageNo eq data.pageCount}">
			 	下一页
			</c:if>
			<c:if test="${page.pageNo ne data.pageCount}">
			<a href="javascript:void(0);" onclick="nextPage(${page.pageNo});">下一页</a>
			</c:if>
			</td>
		</tr>
	</table>
<%@ include file="../../common/IncludeBottom.jsp"%>
<script type="text/javascript">
	function perPage(pageNo){
		$("input[name=pageNo]").val(pageNo-1);
		$("#tableForm").submit();
	}
	
	function nextPage(pageNo){
		$("input[name=pageNo]").val(pageNo+1);
		$("#tableForm").submit();
	}
</script>
</body>
</html>