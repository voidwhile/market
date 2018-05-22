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
	<input type="hidden" name="brandId" value="${brand.brandId}">
	<table class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="100" align="right">分类:</th>
			<td>
			<select name="cmdType" >
			${typeOptions}
			</select>
			</td>
		</tr>
		<tr>
			<th width="100" align="right">名称:</th>
			<td><input class="easyui-textbox" type="text" name="brandName" value="${brand.brandName}" style="width:300px" data-options="required:true" /></td>
		</tr>
	</table>
</form>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/cmd/brand/save.do',
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