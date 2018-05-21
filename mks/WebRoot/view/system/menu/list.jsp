<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="../../common/IncludeHead.jsp"%>
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">  
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">
<link rel="stylesheet" type="text/css" href="${path}/library/css/form.css">          
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
<form id="menuForm" action="${path}/admin/menus/level_save.do" method="post">
<table id="itemGrid" title="菜单管理" class="easyui-treegrid" border="false" 
	data-options="url: '${path}/admin/menus/list.do',singleSelect: true,toolbar: '#itemToolbar',
		method: 'post',rownumbers: true,idField: 'uid',treeField: 'menuName'">
	<thead>
		<tr>
			<th data-options="field:'supplierLevel0',align:'center',formatter:setSupplierLevel0">免费客户</th>
     		<th data-options="field:'supplierLevel1',align:'center',formatter:setSupplierLevel1">普通客户</th>
     		<th data-options="field:'supplierLevel2',align:'center',formatter:setSupplierLevel2">VIP客户</th>
     		<th data-options="field:'supplierLevel3',align:'center',formatter:setSupplierLevel3">金牌客户</th>
     		<th data-options="field:'supplierLevel4',align:'center',formatter:setSupplierLevel4">钻石客户</th>
     		<th data-options="field:'supplierLevel5',align:'center',formatter:setSupplierLevel5">重点客户</th>
			<th data-options="field:'menuName',halign:'center',width:360">名称</th>
			<th data-options="field:'menuCode',halign:'center'">菜单代码</th>
     		<th data-options="field:'menuOrder',align:'center',width:60">排序</th>
		</tr>
	</thead>
</table>
</form>
<div id="itemToolbar" style="height:auto">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-list',plain:true" onclick="levelSubmit()">批量设置</a>
</div>
<script type="text/javascript">
function setSupplierLevel0(value,row,rowIndex){
	var checkedStr="";
	if(value=='1'){
		checkedStr="checked=\"checked\"";
	}
	return "<input type=\"checkbox\" "+checkedStr+" name=\"supplierLevel0\" value=\""+row.uid+"\" >";
}
function setSupplierLevel1(value,row,rowIndex){
	var checkedStr="";
	if(value=='1'){
		checkedStr="checked=\"checked\"";
	}
	return "<input type=\"checkbox\" "+checkedStr+" name=\"supplierLevel1\" value=\""+row.uid+"\" >";
}
function setSupplierLevel2(value,row,rowIndex){
	var checkedStr="";
	if(value=='1'){
		checkedStr="checked=\"checked\"";
	}
	return "<input type=\"checkbox\" "+checkedStr+" name=\"supplierLevel2\" value=\""+row.uid+"\" >";
}
function setSupplierLevel3(value,row,rowIndex){
	var checkedStr="";
	if(value=='1'){
		checkedStr="checked=\"checked\"";
	}
	return "<input type=\"checkbox\" "+checkedStr+" name=\"supplierLevel3\" value=\""+row.uid+"\" >";
}
function setSupplierLevel4(value,row,rowIndex){
	var checkedStr="";
	if(value=='1'){
		checkedStr="checked=\"checked\"";
	}
	return "<input type=\"checkbox\" "+checkedStr+" name=\"supplierLevel4\" value=\""+row.uid+"\" >";
}
function setSupplierLevel5(value,row,rowIndex){
	var checkedStr="";
	if(value=='1'){
		checkedStr="checked=\"checked\"";
	}
	return "<input type=\"checkbox\" "+checkedStr+" name=\"supplierLevel5\" value=\""+row.uid+"\" >";
}
function levelSubmit(){
	$("#menuForm").submit();
}
</script>
</body>
</html>