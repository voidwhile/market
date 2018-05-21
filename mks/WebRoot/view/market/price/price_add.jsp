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
<form id="priceEditForm" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" id="cmdId" name="cmdId" value="${cmdId }">
	<table id="cmdTab" class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th align="right">价格:</th>
			<td><input type="text" maxlength="50" id="price" name="price" class="easyui-validatebox textbox" data-options="required:true"   missingMessage="请输入价格！"  /> 元</td>
		</tr>
		<tr>
			<th align="right">状态:</th>
			<td>
			<select id="priceStatus" name="status">
			<option value="1">有效</option>
			<option value="0">无效</option>
			</select>
			</td>
		</tr>
		<tr>
			<th align="right">类型:</th>
			<td>
			<select id="priceType" name="priceType">
			<option value="1">市场价</option>
			<option value="2">活动价</option>
			</select>
			</td>
		</tr>
		<tr>
			<th align="right">活动名称:</th>
			<td>
			<select id="eventId" name="eventId">
				<option value="0">--请选择--</option>
				<c:forEach var="eve" items="${eventList }">
				<option value="${eve.eventId }">${eve.title}</option>
				</c:forEach>
			</select> 
			</td>
		</tr>
		
	</table>
</form>
</div>
<script type="text/javascript">
function priceSubmitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#priceEditForm').form('submit', {
		url: path+'/cmd/price/save.do',
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
				$('#pricegrid').datagrid('reload');
				$('#pricegrid').datagrid('clearSelections');
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