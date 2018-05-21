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
  <form id="editForm" class="easyui-form"  method="post">  
    <input type="hidden" value="${sysUser.uid }" name="uid"/>
    	<table class="tableBox" width="100%" border="0" cellspacing="1">
	       <tr>
		        <th><label >用户名：</label></th>
		    	<td><label >${sysUser.userName}</label></td>
		    	<th><label >密码：</label></th>
		        <td><input type="password" id="plainPassword" name="plainPassword" class="easyui-validatebox textbox"  value="${sysUser.plainPassword}"  validType= "length[6,1000]" invalidMessage="密码最小长度为6！" data-options="required:true"/></td>
		    </tr>
	         <tr>
		        <th><label >姓名：</label></th>
		    	<td><input type="text" value="${sysUser.realName}" id="realName" name="realName" class="easyui-validatebox textbox" data-options="required:true" missingMessage="请输入姓名！"/></td>
		    	<th><label >性别：</label></th>
		        <td><select name="gender" style="width:130px;">${xbOption }</select>
		        </td>
		    </tr>
	         <tr>
		        <th><label>出生日期：</label></th>
		    	<td><input type="text"  value="<fmt:formatDate value='${sysUser.birthday}' pattern='yyyy-MM-dd' />" id="birthday" name="Ubirthday" class="easyui-datebox textbox" /></td>
		    	<th><label >手机：</label></th>
		        <td><input type="text" value="${sysUser.phone }" name="phone" class="easyui-numberbox textbox" missingMessage="请输入手机号！" validType="length[11,11]" invalidMessage="手机输入位数有误！"/></td>
		    </tr>
	         <tr>
		        <th><label >电话：</label></th>
		    	<td><input type="text" value="${sysUser.tel }" id="tel" name="tel" class="easyui-numberbox textbox"/></td>
		    	<th><label >QQ：</label></th>
		        <td><input type="text" value="${sysUser.qq }" name="qq" class="easyui-numberbox textbox"/></td>
		    </tr>
		    
		    <tr>
		    	<th><label >职位:</label></th>
				<td><input type="text" value="${sysUser.userPosition }" id="userPosition" name="userPosition" class="easyui-validatebox textbox" data-options="required:true" missingMessage="请输入职位！"/></td>
			    <th><label >邮箱：</label></th>
			    <td><input type="text" value="${sysUser.email }" id="email" name="email" class="easyui-validatebox textbox" validType="email" invalidMessage="请填写正确的邮箱格式"/></td>
		    </tr>
		    
		   	<tr>
				<th><label >所属机构:</label></th>
				<td><select class="easyui-combotree" url="${path}/admin/org/tree.do?noShowRoot=true" name="deptId" id="deptId" style="width:300px;" data-options="required:true"></select></td>
			    <th><label ></label></th>
			    <td></td>
		    </tr>
		    
		       <%--<td colspan="2"><button href="javascript:void()" onclick="openAddRoleDialog();" style="font-size: 12px;font-weight: bold;margin-left: 10px;">选择角色</button><span id="roleNames">
		       <c:forEach items="${sysRoleList }" var="item" varStatus="status">
		       <c:choose>
		       <c:when test="${status.last }">${item.roleName }</c:when>
		       <c:otherwise>${item.roleName },</c:otherwise>
		       </c:choose>
		       </c:forEach>
		       </span></td> --%>
		    <tr>
		    <th><label >角色：</label></th>
		    <td colspan="3">
		    <c:forEach items="${roleList }" var="role">
		     <input type="checkbox" value="${role.uid }" name="attr5" class="attr5" style="width: 20px"/>&nbsp;<font size="2px">${role.roleName }</font>
		    </c:forEach>
		    </td>
		    </tr>
	    </table>
     </form>
     <!-- <div id="openDialogEditDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div> -->
  </div>
<script type="text/javascript">
function submitForm(){
	//
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
				showRole();
				closeDialog();
			} else {
				$.messager.alert("提示", obj.message);
			}
			
		}
	});
}
$.parser.onComplete=function(){
	$('#deptId').combotree('setValue', '${sysUser.deptId}');
}
setCheck();
function setCheck(){
	var checkboxs=$("input[type='checkbox']");
    var roleids=roleid.split(",");
    for(var j=0;j<checkboxs.length;j++){
    	for(var i=0;i<roleids.length;i++){
    		if(roleids[i]==checkboxs[j].value){
  	  			checkboxs[j].checked=true;
  	  		}
    	}
    }
}
</script>
</body>  
</html>
