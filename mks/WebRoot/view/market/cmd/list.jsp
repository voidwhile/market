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
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/library/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.all.js"></script>
<script type="text/javascript" src="${path}/view/js/cmd.js"></script>
<script type="text/javascript">
var tableListAction = "${path}/cmd/cmd/list_data.do"; //列表URL
var _addAction="${path}/cmd/cmd/add.do";//添加URL
var _updateAction="${path}/cmd/cmd/edit.do";//修改URL
var _deleteAction="${path}/cmd/cmd/del.do" ;//删除URL
var _priceList = path+"/cmd/price/list_data.do";//价格列表
var _addPrice = path+"/cmd/price/add.do";//添加价格
var _addTitle="添加商品";
var _updateTitle="商品修改";
var _dialogWidth=1000;
var _dialogHeight=800;
</script>
</head>
<body class="easyui-layout container">
<div region="center" border="false">
<div class="easyui-layout" data-options="fit:true">
<div data-options="region:'center',title:'商品列表',split:true,border:false" style="height:50px">
	<table id="dbgrid" title="" fit="true" iconCls="icon-list" idField="cmdId" pagination="true" rownumbers="true" toolbar="#tb" >
		<thead>
			<tr>
              	<th field="cmdId" checkbox="true"></th>
              	<th field="cmdName" sortable="true" width="200" halign="center">名称</th>
              	<th field="cmdCode" sortable="true" width="200" halign="center">编号</th>
              	<th field="brand" formatter="getBrand" sortable="true" width="200" halign="center">品牌</th>
              	<th field="supplier" formatter="getSupplier" sortable="true" width="200" halign="center">供应商</th>
              	<th field="status" formatter="getStatus" sortable="true" halign="center">状态</th>
              	<th field="putawayTime"  sortable="true" width="100" halign="center">上架时间</th>
              	<th field="produceDate" sortable="true" width="100" halign="center">生产日期</th>
              	<th field="shelfLife" sortable="true" width="100" halign="center">保质期</th>
              	<th field="expirationDate" sortable="true" width="100" halign="center">到期日</th>
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
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="showbtn" iconCls="icon-eye-open" plain="true" href="javascript:void(0)" onclick="show()">预览</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="showbtn" iconCls="icon-tags" plain="true" href="javascript:void(0)" onclick="sale()">特价</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="showbtn" iconCls="icon-bullhorn" plain="true" href="javascript:void(0)" onclick="recom()">推荐</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removeFn()">删除</a>
			
		</div>
	</div>
	</div>
	<div data-options="region:'south',title:'商品价格',border:false" style="height: 360px;">
		<table id="pricegrid" title="" fit="true" iconCls="icon-list" idField="priceId" pagination="true" rownumbers="true" toolbar="#tb2" >
		<thead>
			<tr>
              	<th field="priceId" checkbox="true"></th>
              	<th field="cmdName" sortable="true" width="200" halign="center">商品</th>
              	<th field="price" sortable="true" width="100" halign="center">价格</th>
              	<th field="status" formatter="getPriceStatus" width="100"  sortable="true" halign="center">状态</th>
              	<th field="priceType" formatter="getType" sortable="true" width="100" halign="center">类型</th>
              	<th field="title"  sortable="true" width="200" halign="center">活动名称</th>
              	<th field="startTime" sortable="true" width="100" halign="center">活动开始日期</th>
              	<th field="endTime" sortable="true" width="100" halign="center">活动结束日期</th>
			</tr>
      	</thead>
	</table>
	<div id="tb2">
		<div class="toolbar_class">
			&nbsp;&nbsp;&nbsp;&nbsp; <a class="easyui-linkbutton" id="addbtn" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="addPrice()" >新增</a>
			&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="updbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="editPrice()">修改</a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removePrice()">删除</a>
		</div>
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
		queryParams.cmdName = $("#cmdName", $("#tb")).val();  
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
});
var priceTypes = {"1":"市场价","2":"活动价"};
var priceStatus = {"0":"失效","1":"有效"};
function getBrand(val,row){
	return row.brand.brandName;
}
function getStatus(val,row){
	return row.status.statusName;
}
function getSupplier(val,row){
	if(row.supplier){
		return row.supplier.supplierName;
	}else{
		return val;
	}
}
function getPriceStatus(val,row){
	return priceStatus[val];
}
function getType(val,row){
	return priceTypes[val];
}


function show(){
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];	
		openEditorDialog("预览", 600, 800, path+"/cmd/cmd/detail.do?cmdId="+uid);
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}
function sale(){
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];
		openEditorDialog("添加特拉商品", 600, 400, path+"/cmd/cmd/sale.do?cmdId="+uid);
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}
function recom(){
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];	
		openEditorDialog("添加推荐商品", 600, 400, path+"/cmd/cmd/recom.do?cmdId="+uid);
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}
 
</script>
</body>
</html>