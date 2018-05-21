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
var tableListAction = "${path}/admin/param/list.do"; //列表URL
var _addAction="${path}/admin/param/v_add.do";//添加URL
var _updateAction="${path}/admin/param/v_edit.do";//修改URL
var _deleteAction="${path}/admin/param/del.do" ;//删除URL
var _addTitle="添加编码";
var _updateTitle="编码修改";
var _dialogWidth=480;
var _dialogHeight=240;
//是否系统默认formatter
function systemFormatter(value,row,index){
	if(value=="1"){
		return "<span style=\"color:#FF0000\">是</span>";
	} else {
		return "否";
	}
}
</script>
</head>
<body class="easyui-layout container">
<div region="center" border="false">
	<table id="dbgrid" title="编码管理" fit="true" iconCls="icon-list" idField="uid" pagination="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
              	<th field="uid" checkbox="true"></th>
              	<th field="paramCode" sortable="true" width="200" halign="center">编码</th>
              	<th field="paramName" halign="center" width="250">代码描述</th>
              	<th field="issystem" align="center" width="80" formatter="systemFormatter">系统默认</th>
				<th field="remark" halign="center" width="300">备注</th>
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="50" align="right">编码:</th>
				<td width="100"><input type="text" name="paramCode" id="paramCode" /></td>
				<th width="80" align="right">代码描述:</th>
				<td width="100"><input type="text" name="paramName" id="paramName" /></td>
				<td align="left">
					<input type="hidden" name="supplierId" id="supplierId"/>
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
        	</tr>
		</table>
		<div class="toolbar_class">
			<a class="easyui-linkbutton" id="addbtn" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="insert()" >新增</a>
			<a class="easyui-linkbutton" id="updbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="edit()">修改</a>
			<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removeFn()">删除</a>
			<a class="easyui-linkbutton" id="itembtn" iconCls="icon-list" plain="true" href="javascript:void(0)" onclick="itemList()">编码维护</a>
		</div>
	</div>
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
<script type="text/javascript">
$(function(){
	//查询方法
	$("#btnSearch").click(function(){
		var queryParams = $('#dbgrid').datagrid('options').queryParams;
		queryParams.supplierId=$("#supplierId", $("#tb")).val();
		queryParams.paramCode = $("#paramCode", $("#tb")).val();  
		queryParams.paramName = $("#paramName", $("#tb")).val(); 
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
});

//编码维护
function itemList() {	
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];
		var title = selects[0]["paramName"];
		openDialog(title + "编码维护", 720, 480, "${path}/admin/param/v_item_list.do?paramId="+uid);
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中要维护的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>只能选择一条记录!', 'info');
	}
}

function treeClick(node){
	if(node.id.indexOf("level")<0){
		$("#supplierId").val(node.id);
		$("#btnSearch").click();
	} else {
		$("#supplierId").val("");
	}
}
</script>
</body>
</html>