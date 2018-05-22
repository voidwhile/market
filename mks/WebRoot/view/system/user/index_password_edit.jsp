<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
 			<input type="hidden" name="uid" value="${sessionAdmin.user.uid}"/>
			<table class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<th width="100" align="right">原始密码:</th>
					<td><input id="oldpwd" class="easyui-validatebox textbox" type="password" name="oldpwd" style="width:150px" missingMessage="原始密码不能为空！" data-options="required:true" validType= "length[6,1000]" invalidMessage="密码最小长度为6！"/></td>
				</tr>
				<tr>
					<th width="100" align="right">新密码:</th>
					<td><input id="newpwd" class="easyui-validatebox textbox" type="password" name="newpwd" style="width:150px" missingMessage="新密码不能为空！" data-options="required:true" validType= "length[6,1000]" invalidMessage="密码最小长度为6！"/></td>
				</tr>
				<tr>
					<th width="100" align="right">确定新密码:</th>
					<td><input id="rnewpwd" class="easyui-validatebox textbox" type="password" name="rnewpwd" style="width:150px" required="required" validType="equals['#newpwd']"/></td>
				</tr>
			</table>
		</form>
  </div>
<script type="text/javascript">
	//提交
	function submitForm(){
		$.messager.progress({title:'请稍候',msg:'数据提交中...'});
		$('#editForm').form('submit', {
			url: '${path}/admin/user/updatepwd.do',
			onSubmit: function(user){
				var isValid = $(this).form('enableValidation').form('validate');
				if (!isValid){
					$.messager.progress('close');
				}
				return isValid;	
			},
			success: function(data){
				$.messager.progress('close');
				var obj = $.parseJSON(data);
				if(obj.code == 0){
					$.messager.alert("提示", obj.message);
					window.parent.closeDialog();
				} else {
					$.messager.alert("提示", obj.message);
				}
			}
		});  
	}
	
  	$.extend($.fn.validatebox.defaults.rules, {
  		equals: {
		validator: function(value,param){
			return value == $(param[0]).val();
		},
		message: "两次输入密码不一样！"
    	}
  	});
</script>
</body>  
</html>
