<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>活动系统</title>
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
<script type="text/javascript" src="${path}/view/js/event_list.js"></script>
<script type="text/javascript">
var tableListAction = path+"/evt/event/list_data.do"; //列表URL
var cmdListAction = path+"/evt/event/list_cmd_data.do"; //列表URL
var _addAction = path+"/evt/event/add.do"; //列表URL
var _addCmdAction = path+"/evt/event/add_cmd.do"; //列表URL
var _updateAction = path+"/evt/event/edit.do";//编辑URL
var _deleteAction = path+"/evt/event/del.do";//编辑URL
var _addTitle="添加活动";
var _updateTitle="修改活动";
var _dialogWidth=1000;
var _dialogHeight = 800;
</script>
</head>
<body class="easyui-layout container">
  <div data-options="region:'west',title:'活动列表',collapsible:false" style="width:70%; padding: 1 1 1 1px;">
	<table id="dbgrid" title="" fit="true" iconCls="icon-list" idField="eventId" pagination="true" rownumbers="true" toolbar="#tb" singleSelect="true">
		<thead>
			<tr>
				<th field="eventId" checkbox="true"></th>
              	<th field="title" sortable="true" width="200" halign="center">标题</th>
              	<th field="startTime"  sortable="true" width="150" halign="center">开始时间</th>
              	<th field="endTime" sortable="true" width="150" halign="center">结束时间</th>
              	<th field="eveStatus" formatter="getStatus" sortable="true" width="50" halign="center">状态</th>
              	<th field="fcd" sortable="true" width="150"  halign="center">创建时间</th>
              	
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">标题</th>
				<td width="100"><input type="text" name="title" id="title" class="easyui-validatebox textbox" /></td>
				<th width="80" align="right">状态</th>
				<td width="100">
				<select name="eveStatus" id="eveStatus">
					<option value="-1">--请选择--</option>
					<option value="0">未开始</option>
					<option value="1">进行中</option>
					<option value="2">已结束</option>
				</select>
				</td>
				<th width="80" align="right">商品</th>
				<td width="100"><input type="text" name="cmdName" id="cmdName" class="easyui-validatebox textbox" /></td>
				<th width="80" align="right">日期</th>
				<td width="100"><input type="text" name="startTime" id="startTime" class="easyui-datebox textbox" /></td>
				<td width="100"><input type="text" name="endTime" id="endTime" class="easyui-datebox textbox" /></td>
				<td align="left">
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
				
        	</tr>
		</table>
		<div class="toolbar_class">
			&nbsp;&nbsp;&nbsp;&nbsp; <a class="easyui-linkbutton" id="addbtn" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="insert()" >新增</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="updbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="edit()">修改</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="showbtn" iconCls="icon-show" plain="true" href="javascript:void(0)" onclick="show()">预览</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removeFn()">删除</a>
			
			
			
		</div>
	</div>
</div>
<div region="center" border="false">
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'参与商品',split:true,border:false" style="height:50px;padding: 1 1 1 1px;">
	<table id="cmdgrid" title="" fit="true" iconCls="icon-list" idField="detailId" pagination="true" rownumbers="true" toolbar="#tb2">
		<thead>
			<tr>
              	<th field="cmdName" width="200" halign="center">商品</th>
              	<th field="price" sortable="true" width="100" halign="center">价格</th>
			</tr>
      	</thead>
	</table>
	<div id="tb2">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
			
				<td align="left">
					<button type="button" id="btnAddCmd" class="button button-primary">添加商品</button>
				</td>
				
        	</tr>
		</table>
		
	</div>
	</div>
	</div>
	
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>

</body>
</html>