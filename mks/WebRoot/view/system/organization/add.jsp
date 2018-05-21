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
			<th width="100" align="right">上级机构:</th>
			<td><select class="easyui-combotree" url="${path}/admin/org/tree.do" name="pId" style="width:300px;"></select></td>
		</tr>
		<tr>
			<th width="100" align="right">机构名称:</th>
			<td><input class="easyui-textbox" type="text" name="orgName" style="width:300px" missingMessage="机构名称不能为空！" data-options="required:true" /></td>
		</tr>
		<tr>
			<th width="100" align="right">机构代码:</th>
			<td><input class="easyui-textbox" type="text" name="orgCode" style="width:300px" /></td>
		</tr>
		<tr>
			<th width="100" align="right">负责人:</th>
			<td>
			 <input type="hidden"  id="auditor_uid" name="orgHead"/>
			<input class="easyui-textbox" type="text" id="auditor_name" style="width:120px;" readonly/>
			 <span onclick="selectUser();" style="cursor: auto;" title="点击选择" class="icon-search">&nbsp;</span>
			
			</td>
		</tr>
		<tr>
			<th width="100" align="right">联系电话:</th>
			<td><input class="easyui-numberbox" type="text" name="orgPhone" style="width:300px;" /></td>
		</tr>
		<tr>
			<th width="100" align="right">状态:</th>
			<td>
				<input name="status" type="radio" value="1" checked="checked"/>正常
				<input name="status" type="radio" value="0"/>不可用
			</td>
		</tr>
	</table>
</form>
 <div id="openDialogSelectUserDiv" class="easyui-dialog" title="选择员工" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
</div>
<script type="text/javascript">
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/admin/org/save.do',
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
function selectUser(){
	openSelectUserDialog("选择员工", 940, 500, _commonUserAction);
}
var _commonUserAction="${path}/admin/user/comSelList.do";
function selectUser(){
		openSelectUserDialog("选择负责人", 940, 500, _commonUserAction);
	}
function openSelectUserDialog(dialogTitle, dialogWidth, dialogHeight, url){
		$("#openDialogSelectUserDiv").dialog({
			title:dialogTitle,
			href: url,
			closed:false,
	        width: dialogWidth,
	        height: dialogHeight,
	        buttons: [{
	            text: '确定',
	            id:"save",
	            iconCls: 'icon-save',
	            handler: function () {
	            	confirmComSelUser();
	            }
	        }, {
	            text: '取消',
	            id:"cancel",
	            iconCls: 'icon-cancel',
	            handler: function () {
	                $('#openDialogSelectUserDiv').dialog('close');
	            }
	        }]
		});
	}
function confirmComSelUser() {
		var selects =  $('#selUserGrid').datagrid('getSelections');
		if (selects.length==1) {
			var uid = selects[0][getId()];	
			var name = selects[0].realName;
			$("#auditor_uid").val(uid);
			$("#auditor_name").textbox('setValue',name);
			$('#openDialogSelectUserDiv').dialog('close');
		} else if(selects.length < 1) {
			$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
		}else if(selects.length > 1) {
			$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
		}
	}
</script>
</body>
</html>