<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加用户信息</title>
</head>
<body>
  <div id="editDiv" class="easyui-panel" border="false" style="padding:5px 10px">
  <form id="editForm" class="easyui-form"  method="post">  
  	<input type="hidden" name="supplierId" value="${sessionAdmin.supplier.uid}"/>
  	<input type="hidden" name="attr5" value="" id="attr5"/>
  	<table class="tableBox" width="100%" border="0" cellspacing="1">
	       <tr>
		        <th><label>用户名：</label></th>
		    	<td><input type="text" value="${userName}" maxlength="50" id="userName" name="userName" class="easyui-validatebox textbox" data-options="required:true"   missingMessage="请输入用户名！" validType="length[4,100]"  invalidMessage="用户名最小长度为4！"/></td>
		    	<th><label >密码：</label></th>
		        <td><input type="password" id="plainPassword" name="plainPassword" class="easyui-validatebox textbox" data-options="required:true" missingMessage="请输入密码！" value=""  validType= "length[6,1000]" invalidMessage="密码最小长度为6！"/></td>
		    </tr>
	         <tr>
		        <th><label >姓名：</label></th>
		    	<td><input type="text" value="" id="realName" name="realName" class="easyui-validatebox textbox" data-options="required:true" missingMessage="请输入姓名！"/></td>
		    	<th><label >性别：</label></th>
		        <td><select name="gender" style="width:130px;">${xbOption }</select></td>
		    </tr>
	         <tr>
		        <th><label >出生日期：</label></th>
		    	<td><input type="text" value="" id="birthday" name="Ubirthday" class="easyui-datebox textbox"/></td>
		    	<th><label >手机：</label></th>
		        <td><input type="text" value="" name="phone" class="easyui-numberbox textbox" validType="length[11,11]" invalidMessage="手机输入位数有误！"/></td>
		    </tr>
	         <tr>
		        <th><label >电话：</label></th>
		    	<td><input type="text" value="" id="tel" name="tel" class="easyui-numberbox textbox"/></td>
		    	<th><label >QQ：</label></th>
		        <td><input type="text" value="" name="qq" class="easyui-numberbox textbox"/></td>
		    </tr>
		    
		    <tr>
				<th><label >职位:</label></th>
				<td><input type="text" value="" id="userPosition" name="userPosition" class="easyui-validatebox textbox" data-options="required:true" missingMessage="请输入职位！"/></td>
			    <th><label >邮箱：</label></th>
			    <td><input type="text" value="" id="email" name="email" class="easyui-validatebox textbox" validType="email" invalidMessage="请填写正确的邮箱格式"/></td>
		    </tr>
		    
		   	<tr>
			   	<th><label >所属机构:</label></th>
				<td><select class="easyui-combotree" url="${path}/admin/org/tree.do?noShowRoot=true" name="deptId" id="deptId" style="width:200px;" data-options="required:true" ></select></td>
			    <th><label ></label></th>
			    <td></td>
		    </tr>
		    
		    <tr>
		    <th><label >角色：</label></th>
		    <td colspan="3">
		    <c:forEach items="${roleList }" var="role">
		     <input type="checkbox" value="${role.uid }" name="attr5" style="width: 20px"/>&nbsp;<font size="2px">${role.roleName }</font>
		    </c:forEach>
		   <!--  <button href="javascript:void()" onclick="openAddRoleDialog();" style="font-size: 12px;font-weight: bold;margin-left: 10px;">选择角色</button><span id="roleNames"></span> -->
		    </td>
		    </tr>
	 </table>
  </form>
  <!-- <div id="openDialogEditDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div> -->
  </div>
  <script type="text/javascript">
  function submitForm(){
    if($("#deptId").combotree("getValue")==0){
    	$.messager.alert("提示","<br>请选择部门！");
    	return;
    }
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/admin/user/save.do',
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
			if(obj.status){
				$.messager.alert("提示", obj.message);
				reflash();
				//刷新角色列表
				showRole();
				closeDialog();
			} else {
				$.messager.alert("提示", obj.message);
			}
			
		}
	});
}
  
   $.parser.onComplete=function(){
	    if($("#orgId").val()==null||$("#orgId").val()==""){
	    	$('#deptId').combotree('setValue', '0');
	    }else{
	    	$('#deptId').combotree('setValue', $("#orgId").val());
	    }
	} 
</script>
</body>  
</html>
