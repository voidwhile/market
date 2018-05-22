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
<script type="text/javascript" src="${path}/view/js/common_list.js"></script>
<script type="text/javascript">
var tableListAction = "${path}/admin/log/list.do";
var _deleteAction="${path}/admin/log/del.do" ;

$(function(){
	//查询方法
	$("#btnSearch").click(function(){
		var queryParams = $('#dbgrid').datagrid('options').queryParams;
		queryParams.supplierId=$("#supplierId", $("#tb")).val();
		queryParams.operator = $("#operator", $("#tb")).val();  
		queryParams.logLevel = $("#logLevel", $("#tb")).val(); 
	   	queryParams.logType = $("#logType", $("#tb")).val(); 
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
});

function treeFormatter(node){
	var s = node.text;
    if (node.children){
        s += '&nbsp;<span style=\'color:blue\'>(' + node.children.length + ')</span>';
    }
    return s;
}

function treeClick(node){
	if(node.id.indexOf("level")<0){
		$("#supplierId").val(node.id);
		$("#btnSearch").click();
	} else {
		$("#supplierId").val("");
		$("#btnSearch").click();
	}
}
</script>
</head>
<body class="easyui-layout container">
<c:if test="${showTree eq true }">
<div data-options="region:'west',title:'企业选择',collapsible:false" style="width:220px; padding: 0 0 0 5px;">
	<ul class="easyui-tree" data-options="url:'${path}/admin/supplier/tree.do',animate:true,lines:true,formatter:treeFormatter,onClick:treeClick">
	</ul>
</div>
</c:if>
<div region="center" border="false">
	<table id="dbgrid" title="系统日志管理" fit="true" iconCls="icon-list" idField="uid" pagination="true" rownumbers="true" toolbar="#tb">
		<thead>
			<tr>
              	<th field="uid" checkbox="true"></th>
              	<th field="operator" sortable="true" width="200" halign="center">用户</th>
              	<th field="createTime" width="150" halign="center">操作时间</th>
              	<th field="ipAddress" width="120" halign="center">操作IP</th>
              	<th field="logLevel" sortable="true" width="80" align="center">日志级别</th>
				<th field="logType" width="200" align="center">日志类型</th>
				<th field="logContent" width="300" halign="center">日志内容</th>
			</tr>
      	</thead>
	</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="50" align="right">用户:</th>
				<td width="100"><input type="text" name="operator" id="operator" /></td>
        		<th width="80" align="right">日志级别:</th>
        		<td width="100">
	        		<div class="controls">
	        			<select name="logLevel" id="logLevel" style="width: 80px;">
				  			<option value="">全部</option>
				  			<option value="INFO">INFO</option>
				  			<option value="WARN">WARN</option>
				  			<option value="ERROR">ERROR</option>
						</select>
					</div>
				</td>
				<th width="80" align="right">日志类型:</th>
				<td width="100"><input type="text" name="logType" id="logType" /></td>
				<td align="left">
					<input type="hidden" name="supplierId" id="supplierId"/>
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
        	</tr>
		</table>
		<div class="toolbar_class">
			<a id="delbtn" href="javascript:void(0)" onclick="removeFn()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
</div>
</body>
</html>