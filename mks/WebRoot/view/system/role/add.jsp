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
			<th width="100" align="right">角色编码:</th>
			<td><input class="easyui-textbox" type="text" name="roleCode" style="width:300px" missingMessage="角色编码不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">角色名称:</th>
			<td><input class="easyui-textbox" type="text" name="roleName" style="width:300px" missingMessage="角色名称不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
         	<th width="100" align="right">数据范围：</th>
        	<td>
        		<input type="radio" value="full" name="dataRange">全部&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		<input name="dataRange" type="radio" value="dept">部门&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		<input name="dataRange" type="radio" value="owner" checked="checked">自己
        	</td>
     	</tr>
     	<tr>
         	<th width="100" align="right">是否APP登录：</th>
        	<td>
        		<input name="isAppLogin" type="radio" value="1" checked="checked">是&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        		<input name="isAppLogin" type="radio" value="0" >否
        	</td>
     	</tr>
     	<tr>
			<th width="100" align="right">级别:</th>
			<td><input class="easyui-textbox" type="text" name="level" style="width:300px" missingMessage="级别不能为空！" data-options="required:true" /></td>
		</tr>
		<!-- <tr>
			<th width="100" align="right">是否系统默认:</th>
			<td><input  name="isSystem" type="radio" value="1" checked="checked"/>是<input  name="isSystem" type="radio" value="0" />否</td>
		</tr> -->
	</table>
</form>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/admin/role/save.do',
		onSubmit: function(role){
			var isValid = $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');
			}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');
			var obj = $.parseJSON(data);
			if(obj.status){
				$.messager.alert("提示", obj.message);
				reflashrole();
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