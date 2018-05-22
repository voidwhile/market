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
<input type="hidden" name="inventoryId" value="${inventory.inventoryId }">
	<table id="transTab" class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="100" align="right">商品:</th>
			<td>
			<select id="cmdId" name="cmdId" >
			<option value="0">--请选择--</option>
			${cmdOptions}
			</select>
			</td>
		</tr>
		<tr>
			<th width="100" align="right">品牌:</th>
			<td><select id="brandId" name="brandId" >
			<option value="0">--请选择--</option>
			${brandOptions}
			</select></td>
		</tr>
		<tr>
			<th width="100" align="right">供应商:</th>
			<td>
			<select name="supplierId" >
			${supplierOptions}
			</select>
			</td>
	
		</tr>
		<tr>
			<th width="100" align="right">单价:</th>
			<td><input  class="easyui-textbox" type="text" name="unitPrice"/></td>
		</tr>
		<tr>
			<th width="100" align="right">数量:</th>
			<td><input  class="easyui-textbox" type="text" name="amount"/></td>	
		</tr>
		
		
	</table>
</form>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: path+'/rep/trans/save.do',
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
				$.messager.progress('close');
				$.messager.alert("提示", obj.message);
				reflash();
				closeDialog();
				$('#trangrid').datagrid('reload');
			} else {
				$.messager.alert("提示", obj.message);
			}
			
		}
	});
}

</script>
</body>
</html>