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
<input type="hidden" id="cmdId" name="rsCmdId" value="${cmd.cmdId }">
	<table id="cmdTab" class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="30%" align="right">名称</th>
			<td width="70%">${cmd.cmdName }</td>
		</tr>
		<tr>		    
			<th align="right">价格</th>
			<td><input class="easyui-textbox" type="text" name="salePrice" style="width:250px" /> </td>
		</tr>
		<tr>
			<th width="10%" align="right">时间</th>
			<td><input  class="easyui-datebox textbox" type="text" name="dSaleDay"  /> </td>
		</tr>
		<tr>
			<th width="10%" align="right">简介</th>
			<td><input  class="easyui-textbox" type="text" name="intro"  /> </td>
		</tr>
		
		<tr>
			<th width="10%" align="right">类型</th>
			<td>
			<select name="saleType">
			<option value="1">单日促销 </option>
			<option value="2">长期促销</option>
			</select>
			</td>
		</tr>
		
	</table>
	<div id="file-group"></div>
	<div id="showImage" style="position: absolute;top: 100px;left: 200px;" ></div>
</form>
</div>
<script type="text/javascript">

function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: path+'/cmd/cmd/saveSale.do',
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