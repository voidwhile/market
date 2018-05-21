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
<script type="text/javascript" src="${path}/library/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.all.js"></script>
<script type="text/javascript">
var tableListAction = "${path}/cmd/price/list_data.do"; //列表URL
var _addAction="${path}/cmd/price/add.do";//添加URL
var _updateAction="${path}/cmd/price/edit.do";//修改URL
var _deleteAction="${path}/cmd/price/del.do" ;//删除URL
var _addTitle="添加商品价格";
var _updateTitle="商品价格修改";
var _dialogWidth=1000;
var _dialogHeight=800;
</script>
</head>
<body class="easyui-layout container">
<div region="center" border="false">
	<table id="dbgrid" title="商品价格管理" fit="true" iconCls="icon-list" idField="priceId" pagination="true" rownumbers="true" toolbar="#tb" >
		<thead>
			<tr>
              	<th field="priceId" checkbox="true"></th>
              	<th field="price" sortable="true" width="200" halign="center">价格</th>
              	<th field="status" formatter="getStatus" sortable="true" halign="center">状态</th>
              	<th field="priceType" formatter="getType" sortable="true" width="50" halign="center">类型</th>
              	<th field="event" formatter="getTitle" sortable="true" width="200" halign="center">活动名称</th>
              	<th field="event" formatter="getStart" sortable="true" width="100" halign="center">活动开始日期</th>
              	<th field="event" formatter="getEnd" sortable="true" width="100" halign="center">活动结束日期</th>
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">名称:</th>
				<td width="100"><input type="text" name="cmdName" id="cmdName" /></td>
				<td align="left">
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
        	</tr>
		</table>
		<div class="toolbar_class">
			&nbsp;&nbsp;&nbsp;&nbsp; <a class="easyui-linkbutton" id="addbtn" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="insert()" >新增</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="updbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="edit()">修改</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="pricebtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="removeFn()">设置价格</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removeFn()">删除</a>
		</div>
	</div>
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
<script type="text/javascript">
var status = ['失效','有效'];
var priceTypes = ['市场价','活动价'];
$(function(){
	//查询方法
	$("#btnSearch").click(function(){
		var queryParams = $('#dbgrid').datagrid('options').queryParams;
		queryParams.cmdName = $("#cmdName", $("#tb")).val();  
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
});
function getTitle(val,row){
	return row.brand.brandName;
}
function getStatus(val,row){
	return status[row.status];
}
function getType(val,row){
	return priceTypes[row.priceType+1];
}
function getStart(val,row){
	if(row.event){
		return formatDatebox(row.event.startTime);
	}else{
		return val;
	}
}
function getEnd(val,row){
	if(row.event){
		return formatDatebox(row.event.endTime);
	}else{
		return val;
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