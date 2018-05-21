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
<script type="text/javascript" src="${path}/view/js/order_list.js"></script>
<script type="text/javascript">
var tableListAction = path+"/odr/order/list_data.do"; //列表URL
var detailListAction = path+"/odr/order/list_detail_data.do"; //列表URL
var _deliverAction=path+"/odr/order/deliver.do";//添加URL
</script>
</head>
<body class="easyui-layout container">
  <div data-options="region:'west',title:'订单列表',collapsible:false" style="width:70%; padding: 1 1 1 1px;">
	<table id="dbgrid" title="" fit="true" iconCls="icon-list" idField="orderId" pagination="true" rownumbers="true" toolbar="#tb" singleSelect="true">
		<thead>
			<tr>
				<th field="orderId" checkbox="true"></th>
              	<th field="orderCode" sortable="true" width="100" halign="center">编号</th>
              	<th field="member" formatter="getMemeber" sortable="true" width="100" halign="center">会员</th>
              	<th field="totalAmount" sortable="true" width="50" halign="center">金额</th>
              	<th field="statusName" sortable="true" width="50" halign="center">状态</th>
              	<th field="payStatus" formatter="getPayStatus" sortable="true" halign="center">支付状态</th>
              	<th field="linkMan" sortable="true" halign="center">联系人</th>
              	<th field="phone" sortable="true" halign="center">联系方式</th>
              	<th field="address" sortable="true" width="200"  halign="center">送货地址</th>
              	<th field="createTime" sortable="true" halign="center">下单时间</th>
              	
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">订单号</th>
				<td width="100"><input type="text" name="orderCode" id="orderCode" class="easyui-validatebox textbox" /></td>
				<th width="80" align="right">商品</th>
				<td width="100"><input type="text" name="cmdName" id="cmdName" class="easyui-validatebox textbox" /></td>
				<th width="80" align="right">会员</th>
				<td width="100"><input type="text" name="memberName" id="memberName" class="easyui-textbox" /></td>
				<th width="80" align="right">日期</th>
				<td width="100"><input type="text" name="startTime" id="startTime" class="easyui-datebox textbox" /></td>
				<td width="100"><input type="text" name="endTime" id="endTime" class="easyui-datebox textbox" /></td>
				<td align="left">
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
				<td align="center">
					<button type="button" id="btnDeliver" class="button button-primary">发货</button>
				</td>
        	</tr>
		</table>
	</div>
</div>
<div region="center" border="false">
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'订单明细',split:true,border:false" style="height:50px;padding: 1 1 1 1px;">
	<table id="detailgrid" title="" fit="true" iconCls="icon-list" idField="detailId" pagination="true" rownumbers="true" >
		<thead>
			<tr>
              	<th field="cmdName" width="100" halign="center">商品</th>
              	<th field="quantity" sortable="true" width="50" halign="center">数量</th>
              	<th field="unitPrice" sortable="true" width="50" halign="center">价格</th>
              	<th field="cmdPrice" formatter="getEvent" sortable="true" width="200" halign="center">活动</th>
			</tr>
      	</thead>
	</table>
	
	</div>
	</div>
	
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>

</body>
</html>