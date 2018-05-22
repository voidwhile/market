<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div id="editDiv" class="easyui-panel" border="false" style="padding:5px 10px">
<form id="editForm" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" id="eventId" name="eventId" value="${eventId }">
	<table id="cmdTab" class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="20%" align="center">类型</th>
			<th width="20%" align="center">品牌</th>
			<th width="20%" align="center">商品</th>
			<th width="20%" align="center">活动价</th>
			<th width="20%" align="center">操作</th>
			
		</tr>
		<tr>
			<td>
			<select id="cmdType" name="cmdType" onchange="select();">
			<option value="0">--请选择--</option>
			${typeOptions}
			</select>
			</td>
			<td>
			<select id="cmdBrand" name="cmdBrand" >
			<option value="0">--请选择--</option>
			${brandOptions}
			</select>
			</td>
			<td>
			<select id="cmdId" name="cmdIds" >
			<option value="0">--请选择--</option>
			${cmdOptions}
			</select>
			</td>
			<td>
			<input type="text" maxlength="30" id="price" name="prices" class="easyui-validatebox textbox" data-options="required:true"   missingMessage="请输入价格！"  /> 元
			</td>
			<td>
			<a class="easyui-linkbutton" id="1" onclick="addNew(this);" iconCls="icon-add" plain="true" href="javascript:void(0)"></a>
			</td>
		</tr>
		
		
	</table>
</form>
</div>
<script type="text/javascript">

function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: path+'/evt/event/saveCmd.do',
		onSubmit: function(param){
			
			return true;	
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
function addNew(tr){
	var newtr = $(tr).parent().parent().clone();
	$(tr).parent().parent().after(newtr);
	newtr.find("input[type='text']").val("");
}
</script>
</body>
</html>