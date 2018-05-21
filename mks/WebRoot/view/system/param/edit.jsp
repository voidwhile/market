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
<div class="easyui-panel" border="false" style="padding:5px 10px">
<form id="editForm" method="post">
	<input type="hidden" name="uid" value="${model.uid}">
	<table class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="100" align="right">编码:</th>
			<td><input class="easyui-textbox" type="text" name="paramCode" value="${model.paramCode}" readonly="true" style="width:300px" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">代码描述:</th>
			<td><input class="easyui-textbox" type="text" name="paramName" value="${model.paramName}" style="width:300px" missingMessage="编码名称不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">备注:</th>
			<td><input class="easyui-textbox" name="remark" value="${model.remark }" data-options="multiline:true" style="width:300px;height:60px"/></td>
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