<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="../../common/IncludeHead.jsp"%>
    <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">  
    <script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
</head>
 <body class="easyui-layout container">
  <div class="easyui-layout" data-options="fit:true">
	         <table id="rolegrid" fit="true" iconCls="icon-edit" class="easyui-datagrid" idField="uid">
				<thead>
					<tr>
						<th field="uid" width="50" checkbox="true"></th>
						<th field="roleCode" width="150">角色代码</th>
						<th field="roleName" width="100">角色名称</th>
						<th field="createTime" width="150">创建日期</th>
					</tr>
				</thead>
			</table>
    </div>
  <script type="text/javascript">
  var tableRoleListAction="${path}/admin/role/roleList.do?uid=<%=request.getParameter("uid")%>";
  function submitForm(){
		var selects=$('#rolegrid').datagrid('getSelections');
		 var _allotUserAction="${path}/admin/role/allotUser.do";
	      var userId="<%=request.getParameter("uid")%>";
	      
		if(selects.length<1){
			$.messager.alert('提示', '<br>请选中您要授权给用户的角色!', 'info');
		}else{
			$.messager.progress({title:'请稍候',msg:'数据提交中...'});
			var roleIds=[];
			for(var i=0; i<selects.length;i++){
				roleIds.push(selects[i][getIdByGridid()]);
			}
			  var roleId=roleIds.join(",");
			  $.ajax({
					type : "POST",
					url :_allotUserAction ,
					data : {roleIds:roleId,userId: userId},
					success : function(data){
						$.messager.progress('close');
						var msg=$.parseJSON(data);
						showRole();
						closeDialog();
						$.messager.alert("提示",msg.msg,"info");
					}
				}); 
		}
}
function getIdByGridid() {
	return $("#rolegrid").attr('idField');
}

$(function(){
	$('#rolegrid').datagrid({
		url : tableRoleListAction,
		method:"post",
		striped:true,
		rownumbers: true,
		pagination:false,
		loadMsg:"数据正在加载中"
	});
	});
  </script>
</body>
</html>