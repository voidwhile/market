<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div id="editDiv" class="easyui-panel" border="false" style="padding:5px 10px">
<form id="editForm" class="easyui-form" method="post" data-options="novalidate:true">
	<input type="hidden" name="supplierId" value="${sessionAdmin.supplier.uid}"/>
	<table class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="100" align="right">编码:</th>
			<td><input class="easyui-textbox" type="text" name="paramCode" style="width:300px" missingMessage="编码不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">编码名称:</th>
			<td><input class="easyui-textbox" type="text" name="paramName" style="width:300px" missingMessage="编码名称不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">备注:</th>
			<td><input class="easyui-textbox" name="remark" data-options="multiline:true" style="width:300px;height:60px"/></td>
		</tr>
	</table>
</form>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/admin/param/save.do',
		onSubmit: function(param){
			var isValid = $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');
			}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');
			var obj = jQuery.parseJSON(data);
			if(obj.status){
				$.messager.alert("提示", obj.message);
				reflash();
				closeDialog();
			} else {
				$.messager.alert("提示", obj.message);
			}
			
		}
	});
}
</script>
</body>
</html>