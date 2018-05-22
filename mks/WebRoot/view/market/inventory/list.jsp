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
<script type="text/javascript" src="${path}/view/js/trans_list.js"></script>
<script type="text/javascript">
var tableListAction = path+"/rep/inventory/list_data.do"; //列表URL
var transListAction = path+"/rep/trans/list_data.do"; //列表URL
var _addAction=path+"/rep/trans/add.do";//添加URL
var _addTitle="添加库存";
var _updateTitle="库存修改";
var _dialogWidth=600;
var _dialogHeight=400;
</script>
</head>
<body class="easyui-layout container">
  <div data-options="region:'west',title:'商品列表',collapsible:false" style="width:45%; padding: 1 1 1 1px;">
	<table id="dbgrid" title="" fit="true" iconCls="icon-list" idField="inventoryId" pagination="true" rownumbers="true" toolbar="#tb" singleSelect="true">
		<thead>
			<tr>
              	<th field="cmdName" sortable="true" width="200" halign="center">名称</th>
              	<th field="cmdCode" sortable="true" width="150" halign="center">编号</th>
              	<th field="brandName" sortable="true" width="100" halign="center">品牌</th>
              	<th field="cmdStatus" sortable="true" halign="center">状态</th>
              	<th field="stock" sortable="true" halign="center">库存</th>
              	
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
			
				<th width="80" align="right">数量</th>
				<td>
			<input type="text" class="" id="num" name="num">
			<a class="easyui-linkbutton" id="editbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="editStock()">修改库存</a>
				</td>
				<th width="80" align="right">名称</th>
				<td width="100"><input type="text" name="cmdName" id="cmdName" class="easyui-validatebox textbox" /></td>
				<td align="left">
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
        	</tr>
		</table>
	</div>
</div>
<div region="center" border="false">
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'出入库记录',split:true,border:false" style="height:50px;padding: 1 1 1 1px;">
	<table id="trangrid" title="" fit="true" iconCls="icon-list" idField="transId" pagination="true" rownumbers="true"  toolbar="#tb2" >
		<thead>
			<tr>
              	<th field="cmdName" sortable="true" width="100" halign="center">商品</th>
              	<th field="brandName" sortable="true" width="100" halign="center">品牌</th>
              	<th field="supplierName"  sortable="true" width="100" halign="center">供应商</th>
              	<th field="amount" sortable="true" width="50" halign="center">数量</th>
              	<th field="unitPrice" sortable="true" width="50" halign="center">单价</th>
              	<th field="realName" sortable="true" width="100" halign="center">操作人</th>
              	<th field="transTime" sortable="true" width="100" halign="center">操作时间</th>
              	<th field="transType" formatter="getTransType" sortable="true" width="100" halign="center">操作类型</th>
			</tr>
      	</thead>
	</table>
	<div id="tb2">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">
				<button type="button" id="btnTrans" class="button button-primary">入库</button>
				</th>
				<th width="80" align="right">操作时间:</th>
				<td width="100"><input type="text" name="startTime" id="startTime" class="easyui-datebox textbox" /></td>
				<td width="100"><input type="text" name="endTime" id="endTime" class="easyui-datebox textbox" /></td>
				<td align="left">
					<button type="button" id="btnTransSearch" class="button button-primary">搜索</button>
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