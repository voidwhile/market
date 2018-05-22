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
<script type="text/javascript" src="${path}/view/js/common_list.js"></script>
<script type="text/javascript">
var tableListAction = "${path}/rep/supp/list_data.do"; //列表URL
var _addAction="${path}/rep/supp/add.do";//添加URL
var _updateAction="${path}/rep/supp/edit.do";//修改URL
var _deleteAction="${path}/rep/supp/del.do" ;//删除URL
var _addTitle="添加供应商";
var _updateTitle="供应商修改";
var _dialogWidth=480;
var _dialogHeight=330;
</script>
</head>
<body class="easyui-layout container">
<div region="center" border="false">
	<table id="dbgrid" title="供应商管理" fit="true" iconCls="icon-list" idField="supplierId" pagination="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
              	<th field="supplierId" checkbox="true"></th>
              	<th field="supplierName" sortable="true" width="200" halign="center">名称</th>
              	<th field="status" formatter="getStatus" sortable="true" width="200" halign="center">状态</th>
              	<th field="linkMan" sortable="true" width="200" halign="center">联系人</th>
              	<th field="linkMp" sortable="true" width="200" halign="center">联系电话</th>
              	<th field="address" sortable="true" width="200" halign="center">地址</th>
              	<th field="description" sortable="true" width="200" halign="center">描述</th>
              	<th field="serverStart" formatter="formatDatebox" sortable="true" width="200" halign="center">开始合作时间</th>
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">名称:</th>
				<td width="100"><input type="text" name="supplierName" id="supplierName" /></td>
				<th width="80" align="right">联系人:</th>
				<td width="100"><input type="text" name="linkMan" id="linkMan" /></td>
				<th width="80" align="right">联系电话:</th>
				<td width="100"><input type="text" name="linkMp" id="linkMp" /></td>
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
		queryParams.supplierName = $("#supplierName", $("#tb")).val();  
		queryParams.linkMan = $("#linkMan", $("#tb")).val();  
		queryParams.linkMp = $("#linkMp", $("#tb")).val();  
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
});
function getStatus(val){
	if(val==1){
		return "合作中";
	}else if(val==2){
		return "取消合作";
	}
}

function formatDatebox(value) {  
    if (value == null || value == '') {  
        return '';  
    }  
    var dt;  
    if (value instanceof Date) {  
        dt = value;  
    } else {  
        dt = new Date(value);  
    }  
  
    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)  
} 
</script>
</body>
</html>