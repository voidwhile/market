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
			<th width="100" align="right">企业编号:</th>
			<td><input class="easyui-textbox" type="text" name="supplierCode" style="width:300px" missingMessage="企业编号不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">企业名称:</th>
			<td><input class="easyui-textbox" type="text" name="supplierName" style="width:300px" missingMessage="企业名称不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">企业等级:</th>
			<td><select name="supplierLevel">
				<option value="0">免费客户</option>
				<option value="1">普通客户</option>
				<option value="2">VIP客户</option>
				<option value="3">金牌客户</option>
				<option value="4">钻石客户</option>
				<option value="5">重点客户</option>
			</select></td>
		</tr>
	    <tr>
                <th align="right"><label>所属地区:</label></th>
				<td width="300" colspan="3">
       			<select name="province" class="province" onchange="setCity(this.value);">
       			    ${provinceOption }
				</select>
				<select name="city" class="city" onchange="setCountry(this.value);">
				<option value="">请选择...</option>
				</select>
				<select name="county" class="country">
				<option value="">请选择...</option>
				</select>
				</td>
	    </tr>
		<tr>
		    <th width="100" align="right">地址:</th>
			<td><input class="easyui-textbox" type="text" name="address" style="width:300px"/></td>
		</tr>
		<tr>
		    <th width="100" align="right">联系人:</th>
			<td><input class="easyui-textbox" type="text" name="contacts" style="width:300px" missingMessage="联系人不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
		    <th align="right">联系人号码:</th>
		    <td><input class="easyui-numberbox textbox" type="text" name="contactNumber" style="width:300px" missingMessage="联系人手机号不能为空！" data-options="required:true" validType="length[11,11]" invalidMessage="手机输入位数有误！"/></td>
	    </tr>
		<tr>
		    <th align="right">企业描述:</th>
		    <td><textarea name="supplierIntro" cols="51"></textarea></td>
	    </tr>
	</table>
</form>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/admin/supplier/save.do',
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

//设置城市
function setCity(provincecode){
	var _getCityAction="${path}/admin/customer/getCity.do";
	$.post(_getCityAction,{provincecode : provincecode,selected:""},
			function(city) {
		        $(".city").html("");
		        $(".city").append("<option value=\"\">请选择...</option>");
				$(".city").append(city);
			});
}
 //设置区域
function setCountry(citycode){
	var _getCountryAction="${path}/admin/customer/getCountry.do";
	$.post(_getCountryAction,{citycode : citycode,selected:""},
			function(country) { 
		        $(".country").html("");
		        $(".country").append("<option value=\"\">请选择...</option>");
			    $(".country").append(country);
			});
}
</script>
</body>
</html>