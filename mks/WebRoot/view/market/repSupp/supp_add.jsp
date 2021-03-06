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
	<table class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="100" align="right">名称:</th>
			<td><input class="easyui-textbox" type="text" name="supplierName" style="width:300px" missingMessage="名称不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">联系人:</th>
			<td><input class="easyui-textbox" type="text" name="linkMan" style="width:300px" missingMessage="联系人不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">联系电话:</th>
			<td><input class="easyui-textbox" type="text" name="linkMp" style="width:300px" missingMessage="联系电话不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">地址:</th>
			<td><input class="easyui-textbox" type="text" name="address" style="width:300px" missingMessage="地址电话不能为空！" data-options="required:false" /></td>
		</tr>
		<tr>
			<th width="100" align="right">描述:</th>
			<td><input class="easyui-textbox" type="text" name="description" style="width:300px" missingMessage="描述电话不能为空！" data-options="required:false" /></td>
		</tr>
		<tr>
			<th width="100" align="right">开始合作时间:</th>
			<td><input class="easyui-datebox textbox" type="text" name="startTime"  missingMessage="开始合作时间电话不能为空！" data-options="required:false" /></td>
		</tr>
		<tr>
			<th width="100" align="right">状态:</th>
			<td>
			<select name="status" >
			<option value="1" selected="selected">合作中</option>
			<option value="2">取消合作</option>
			</select>
			</td>
		</tr>
		
	</table>
</form>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/rep/supp/save.do',
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