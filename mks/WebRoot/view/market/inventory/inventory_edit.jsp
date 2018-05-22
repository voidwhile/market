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
	<input type="hidden" name="cmdId" value="${cmd.cmdId }" >
	<table id="cmdTab" class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="100" align="right">名称:</th>
			<td><input class="easyui-textbox" type="text" name="cmdName" value="${cmd.cmdName }" style="width:150px" missingMessage="名称不能为空！" data-options="required:true" /></td>
		    
			<th width="100" align="right">分类:</th>
			<td><select id="cmdType" name="cmdType" onchange="addLabel();">
			<option value="0">--请选择--</option>
			${typeOptions}
			</select></td>
		</tr>
		<tr>
			<th width="100" align="right">标签:</th>
			<td colspan="3" id="labels">
			<c:forEach items="${cmd.labels }" var="label">
			<span id='${label.cmdType }' onclick='removeLabel(${label.cmdType });'>&nbsp;&nbsp;${label.type.typeName }<input type='hidden' name='cmdLabel' value='${label.cmdType }'></span>	
			</c:forEach>
			</td>
		</tr>
		<tr>
			<th width="100" align="right">品牌:</th>
			<td><select id="cmdBrand" name="cmdBrand" >
			<option value="0">--请选择--</option>
			${brandOptions}
			</select></td>
			<th width="100" align="right">状态:</th>
			<td>
			<select name="cmdStatus" >
			${statusOptions}
			</select>
			</td>
	
		</tr>
		<tr>
			<th width="100" align="right">上架时间 :</th>
			<td><input  class="easyui-datebox textbox" value="<fmt:formatDate value='${cmd.putawayTime }' pattern='yyyy-MM-dd'/>" type="text" name="dputawayTime" missingMessage="地址电话不能为空！" data-options="required:false" /></td>
	
			<th width="100" align="right">生产日期:</th>
			<td><input class="easyui-datebox textbox" type="text" name="dproduceDate" value="<fmt:formatDate value='${cmd.produceDate }' pattern='yyyy-MM-dd'/>"  /></td>
		</tr>
		<tr>
			<th width="100" align="right">保质期:</th>
			<td><input  class="easyui-textbox" type="text" name="shelfLife"/></td>
			<th width="100" align="right">到期日:</th>
			<td><input class="easyui-datebox textbox" type="text" name="dexpirationDate" value="<fmt:formatDate value='${cmd.expirationDate }' pattern='yyyy-MM-dd'/>" /></td>
	    </tr>
		<tr>
			<th width="100" align="right">供应商:</th>
			<td>
			<select name="supplierId" >
			<option value="0">--请选择--</option>
			${supplierOptions}
			</select>
			</td>
			<th width="100" align="right">添加规格:</th>
			<td>
			<img align="middle" alt="" src="${path}/library/images/t01.png" onclick="addSft();">
			</td>
		</tr>
		<c:forEach items="${cmd.sfts }" var="sft">
		<tr>
			<th width="100" align="right">规格:</th>
			<td colspan="3">
			<select name="specification" >
			${specificationOptions}
			</select>
			<input type="text" id="sftValue" name="sftValue" value="${sft.sftVal }" style="width:400px" />
			<img align="middle" alt="" src="${path}/library/images/t03.png" onclick="rmSft(this);">
			</td>
		</tr>
		</c:forEach>
	</table>
</form>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/cmd/cmd/save.do',
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
function addSft(){
	if(document.getElementById("sftValue")){
		var tr = $("#cmdTab tr").eq(-1);
		$("#cmdTab").append(tr.clone());
		$("#cmdTab tr").eq(-1).find("input[type='text']").val("");
	}else{
		var tr = "<tr><th width='100' align='right'>规格:</th><td colspan='3'><select name='specification' >${specificationOptions}</select><input type='text' id='sftValue' name='sftValue' value='${sft.sftVal }' style='width:400px' /><img align='middle' alt='' src='${path}/library/images/t03.png' onclick='rmSft(this);'></td></tr>";
		$("#cmdTab").append(tr);	
	}
}
function rmSft(el){
	$(el).parent().parent().remove();
}
function addLabel(){
	var cmdType = $("#cmdType").val();
	if(cmdType!=0&&$("#"+cmdType)){
		var typeName = $("#cmdType").find("option:selected").text();
		var labels = $("#labels").html();
		labels = labels +"<span style='border-color: red;' id='"+cmdType+"' onclick='removeLabel("+cmdType+");'>&nbsp;&nbsp;"+typeName+"<input type='hidden' name='cmdLabel' value='"+cmdType+"'></span>";
		$("#labels").html(labels);
		brandChange();
	}
}
function removeLabel(id){
	$("#"+id).remove();
	brandChange();
}
function brandChange(){
	var types = "";
	var whereClause = "1=1";
	$("#cmdTab").find("input[name='cmdLabel']").each(function(){
		types = types + "," +  this.value;
	});
	if(types!=""){
		whereClause="cmd_type in ("+types.substr(1)+")";
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${path}/admin/param/options.do",
		data:{
			tableName:'mk_cmd_brand',
			code:'brand_id',
			des:'brand_name',
			whereClause:whereClause,
			selected:''
		},
		success:function(msg){
			$("#cmdBrand option").remove();
			$("#cmdBrand").append("<option value='0'>--请选择--</option>");
			$("#cmdBrand").append(msg.options);
			
		}
	});
}
</script>
</body>
</html>