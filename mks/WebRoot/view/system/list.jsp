<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>管理系统</title>
<%@ include file="../common/IncludeHead.jsp"%>
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">  
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">         
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout container">
<div data-options="region:'west',title:'部门选择',collapsible:false" style="width:220px; padding: 0 0 0 5px;">
	部门选择
</div>
<div region="center">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'用户列表',split:true,border:false" style="height:50px">
	                north
		</div>
		<div data-options="region:'south',title:'用户权限列表',border:false" style="height: 200px;">
			south
		</div>
    </div>
</div>
</body>
</html>