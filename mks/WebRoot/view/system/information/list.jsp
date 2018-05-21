<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>管理系统</title>
<%@ include file="../../common/IncludeHead.jsp"%>
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">  
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">
<link rel="stylesheet" type="text/css" href="${path}/library/css/form.css">            
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/library/js/title_list.js"></script>
<script type="text/javascript">
var tableListAction = "${path}/admin/information/v_list.do"; //列表URL
var _addAction="${path}/admin/information/add.do";//添加URL
var _updateAction="${path}/admin/information/update.do";//修改URL
var _deleteAction="${path}/admin/information/del.do" ;//删除URL
var _addTitle="添加资讯分类";
var _updateTitle="修改资讯分类";
var _dialogWidth=480;
var _dialogHeight=200;
</script>
</head>
<body class="easyui-layout container">
<div region="center" border="false">
	<table id="dbgrid" title="资讯分类" fit="true" iconCls="icon-list" idField="typeId" pagination="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
              	<th field="typeId" checkbox="true"></th>
              	<th field="typeName" sortable="true" width="200" halign="center">资讯分类名称</th>
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<div class="toolbar_class">
			<a class="easyui-linkbutton" id="addbtn" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="insert()" >新增</a>
			<a class="easyui-linkbutton" id="updbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="edit()">修改</a>
			<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removeFn()">删除</a>
		</div>
	</div>
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
</body>
</html>