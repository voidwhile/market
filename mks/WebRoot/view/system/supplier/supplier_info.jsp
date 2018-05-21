<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<%@ include file="../../common/IncludeHead.jsp"%>
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>  
</head>
<body>
<div id="editDiv" class="easyui-panel" border="false" style="padding:5px 10px">
<form id="editForm" class="easyui-form" method="post" data-options="novalidate:true" >
    <input type="hidden" value="${model.uid }" name="uid"/>
	<table class="tableBox" border="0" width="35%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="100" align="right">企业编号:</th>
			<td><label>${model.supplierCode }</label></td>
		</tr>
		<tr>
			<th width="100" align="right">企业名称:</th>
			<td><input class="easyui-textbox" type="text" name="supplierName" value="${model.supplierName }" style="width:300px" missingMessage="企业名称不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">企业等级:</th>
			<td><select name="supplierLevel" id="supplierLevel">
				<option value="0" ${model.supplierLevel eq 0 ? 'selected = selected' : "" }>免费客户</option>
				<option value="1" ${model.supplierLevel eq 1 ? 'selected = selected' : "" }>普通客户</option>
				<option value="2" ${model.supplierLevel eq 2 ? 'selected = selected' : "" }>VIP客户</option>
				<option value="3" ${model.supplierLevel eq 3 ? 'selected = selected' : "" }>金牌客户</option>
				<option value="4" ${model.supplierLevel eq 4 ? 'selected = selected' : "" }>钻石客户</option>
				<option value="5" ${model.supplierLevel eq 5 ? 'selected = selected' : "" }>重点客户</option>
			</select></td>
		</tr>
	     <tr>
            <th align="right"><label>所属地区:</label></th>
			<td width="300" colspan="3">
      		  <select name="province" id="editProvince" onchange="initCity(this.value);">
      			${provinceOption }
			   </select>
			   <select name="city" id="editCity" onchange="initCountry(this.value);">
			    ${cityOption }
			   </select>
			   <select name="county" id="editCountry">
                ${countryOption }
			  </select>
			</td>
		    </tr>
		    <tr>
		    <th align="right"><label>详细地址：</label></th>
		    <td><input type="text" value="" name="address" class="easyui-textbox"  style="width:300px"/></td>
		<tr>
		<th width="100" align="right">联系人:</th>
			<td><input class="easyui-textbox" type="text" name="contacts" style="width:300px" missingMessage="联系人不能为空！" data-options="required:true" value="${model.contacts }"/></td>
		</tr>
		<tr>
		    <th align="right">联系人号码:</th>
		    <td><input class="easyui-numberbox textbox" type="text" name="contactNumber" style="width:300px" missingMessage="联系人手机号不能为空！" data-options="required:true" validType="length[11,11]" invalidMessage="手机输入位数有误！" value="${model.contactNumber }"/></td>
	    </tr>
	    	<tr>
		    <th align="right">企业描述:</th>
		    <td><textarea name="supplierIntro" cols="51">${model.supplierIntro }</textarea></td>
	    </tr>
	</table>
	<br>
	    <div style="text-align: center;width:35%"><a id="addbtn" href="javascript:void(0)" onclick="submitForm()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">提交</a></div>
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
				$.messager.alert("提示", "<br>"+obj.message);
				reflash();
				closeDialog();
			} else {
				$.messager.alert("提示", "<br>"+obj.message);
			}
			
		}
	});
}

//设置城市
function initCity(provincecode){
	  var param={provincecode : provincecode,selected:""}; 
	var _getCityAction="${path}/admin/customer/getCity.do";
	$.post(_getCityAction,param,
			function(city) {
		        $("#editCity").html("");
		        $("#editCity").append("<option value=''>请选择...</option>");
				$("#editCity").append(city);
			});
}
  //设置区域
  function initCountry(citycode){
	  var  param={citycode : citycode,selected:""};  
  	var _getCountryAction="${path}/admin/customer/getCountry.do";
  	$.post(_getCountryAction,param,
  			function(country) {
  		        $("#editCountry").html("");
  		        $("#editCountry").append("<option value=''>请选择...</option>");
  				$("#editCountry").append(country);
  			});
  } 
</script>
</body>
</html>