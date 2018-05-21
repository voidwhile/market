<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="../common/IncludeHead.jsp" %>
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">  
    <script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>

</head>
<body>
	<div style="margin-left:50px;margin-top:50px;float:left">
	       <div id="p1" class="easyui-panel" title="通知公告" icon="icon-save" collapsible="false" style="width:500px;heigth:180px;padding:10px;background:#fafafa;">
				<div class="deskitem">	  
					<c:forEach items="${notices}" var="n" varStatus="status">
							<li>${n.title}</li>
					</c:forEach>
				</div>
				<div style="margin-left: 420px;"><a href="${path}/admin/notice/v_viewlist.do">更多...</a></div>
	      </div>
	</div>
	

	
<%-- 	<div style="margin-left:50px;margin-top:50px;float:left">
			<input type="hidden" name="uid" value="${sessionAdmin.user.uid}">
	       <div id="p1" class="easyui-panel" title="通知公告" icon="icon-save" collapsible="false" style="width:500px;heigth:180px;padding:10px;background:#fafafa;">
				<div class="deskitem">	  
					<c:forEach items="${notices}" var="n" varStatus="status">
							<li>${n.title}</li>
					</c:forEach>
				</div>
				<div style="margin-left: 420px;"><a href="${path}/admin/notice/">更多...</a></div>
	      </div>
	</div> --%>
	
</body>
</html>