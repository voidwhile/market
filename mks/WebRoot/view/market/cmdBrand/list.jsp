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
var tableListAction = "${path}/cmd/brand/list_data.do"; //列表URL
var _addAction="${path}/cmd/brand/add.do?cmdType="+$("#cmdType").val();//添加URL
var _updateAction="${path}/cmd/brand/edit.do";//修改URL
var _deleteAction="${path}/cmd/brand/del.do" ;//删除URL
var _addTitle="添加品牌";
var _updateTitle="品牌修改";
var _dialogWidth=480;
var _dialogHeight=200;
</script>
</head>
<body class="easyui-layout container">
<input type="hidden" id="cmdType" name="cmdType"/>
<div data-options="region:'west',title:'分类选择',collapsible:false" style="width:220px; padding: 0 0 0 5px;">
	<ul id="tree" class="easyui-tree" data-options="url:'${path}/cmd/type/tree.do',animate:true,lines:true,onClick:treeClick,loadMsg:'数据正在加载中'">
	</ul>
</div>
<div region="center" border="false">
<div class="easyui-layout" data-options="fit:true">
	<table id="dbgrid" title="品牌管理" fit="true" iconCls="icon-list" idField="brandId" pagination="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
              	<th field="brandId" checkbox="true"></th>
              	<th field="brandName" sortable="true" width="200" halign="center">品牌</th>
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">品牌:</th>
				<td width="100"><input type="text" name="brandName" id="brandName" /></td>
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
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
<script type="text/javascript">
$(function(){
	//查询方法
	$("#btnSearch").click(function(){
		var queryParams = $('#dbgrid').datagrid('options').queryParams;
		queryParams.brandName = $("#brandName", $("#tb")).val();  
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
});
//查询方法
function find(){
	   var queryParams = $('#dbgrid').datagrid('options').queryParams;  
     queryParams.cmdType=$("#cmdType").val();
    	//重新加载datagrid的数据  
     $("#dbgrid").datagrid('reload');  
	   return false;
}

function treeClick(node){
	 $("#cmdType").val(node.id);
	 find();
}
</script>
</body>
</html>