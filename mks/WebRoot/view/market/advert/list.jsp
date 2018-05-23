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
<script type="text/javascript" src="${path}/library/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${path}/view/js/common_list.js"></script>
<script type="text/javascript">
var tableListAction = path+"/evt/ad/list_data.do"; //列表URL
var _addAction=path+"/evt/ad/add.do";//添加URL
var _updateAction=path+"/evt/ad/edit.do";//修改URL
var _deleteAction=path+"/evt/ad/del.do" ;//删除URL
var _addTitle="添加广告";
var _updateTitle="规格广告";
var _dialogWidth=800;
var _dialogHeight=600;
</script>
</head>
<body class="easyui-layout container">
<div region="center" border="false">
	<table id="dbgrid" title="广告列表" fit="true" iconCls="icon-list" idField="advertId" pagination="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
              	<th field="advertId" checkbox="true"></th>
              	<th field="title" sortable="true" width="200" halign="center">标题</th>
              	<th field="cmdName" sortable="true" width="100" halign="center">商品</th>
              	<th field="url" sortable="true" width="300" halign="center">链接地址</th>
              	<th field="imgPath" formatter="getImg" sortable="true" width="300" halign="center">图片</th>
              	<th field="adAdvertType" formatter="getType" sortable="true" width="50" halign="center">类型</th>
              	<th field="state" formatter="getState" sortable="true" width="50" halign="center">状态</th>
              	<th field="startTime" sortable="true" width="100" halign="center">开始时间</th>
              	<th field="endTime" sortable="true" width="100" halign="center">结束时间</th>
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">标题:</th>
				<td width="100"><input type="text" name="title" id="title" /></td>
				<td align="left">
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
        	</tr>
		</table>
		<div class="toolbar_class">
			<a class="easyui-linkbutton" id="addbtn" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="insert()" >新增</a>
			<a class="easyui-linkbutton" id="updbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="edit()">修改</a>
			<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removeFn()">删除</a>
		</div>
	</div>
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
<script type="text/javascript">
$(function(){
	//查询方法
	$("#btnSearch").click(function(){
		var queryParams = $('#dbgrid').datagrid('options').queryParams;
		queryParams.sftName = $("#sftName", $("#tb")).val();  
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
});
var types = {"1":"轮播","2":"普通"};
var states = {"0":"失效","1":"正常"};
function getType(val,row){
	return types[val];
}
function getState(val,row){
	return states[val];
}
function getImg(val,row){
	return '<img src="${imgUrl}'+row.imgPath+'" width=200px height=100px />';
}
</script>
</body>
</html>