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
    <style type="text/css">
    </style>
</head>
 <body class="easyui-layout container">
  <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'center',title:'',split:true,border:false," style="height:50px">
	         <table id="allotgrid" fit="true" iconCls="icon-edit" idField="uid" toolbar="#tb_allot">
				<thead>
					<tr>
						<th field="uid" width="50" checkbox="true"></th>
						<th field="userName" width="150" formatter="setCheckUser">用户名</th>
						<th field="realName" width="100">姓名</th>
						<th field="gender" width="50" formatter="transGender">性别</th>
						<th field="phone" width="100">手机</th>
						<th field="qq" width="100" formatter="">QQ</th>
						<th field="tel" width="100" formatter="">电话</th>
						<th field="birthday" width="100" formatter="">出生日期</th>
						<th field="createTime" width="100" formatter="">创建日期</th>
					</tr>
				</thead>
			</table>
	<div id="tb_allot">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<input type="hidden" id="orgId"/>
			<tr>
        		<th width="80" align="right">性别:</th>
        		<td width="100">
	        		<div class="controls">
	        			<select name="gender" id="gender">
	        			    <option value="">请选择...</option>
	        			    <option value="0">未知</option>
				  			<option value="1">男</option>
				  			<option value="2">女</option>
						</select>
					</div>
				</td>
				<th width="80" align="right">用户名:</th>
				<td width="100"><input type="text" name="userName" id="userName"/></td>
				<td align="left"><button  type="button" id="btnUserSearch" class="button button-primary" onclick="findUser();">搜索</button></td>
        	</tr>
		</table>
	</div>
	</div>
    </div>
  <script type="text/javascript">
  var tableAllotListAction = "${path}/admin/user/list_data.do?roleId="+$("#roleId").val();
  var enumObj = {"1":"男","2":"女","0":"未知"}
  function transGender(value,data,index){
	  return enumObj[value];
  } 
  //查询方法
  function findUser(){
	   var queryParams = $('#allotgrid').datagrid('options').queryParams;  
       queryParams.gender = $("#gender").val();  
       queryParams.userName = $("#userName").val();  
       queryParams.orgId=$("#orgId").val();
      	//重新加载datagrid的数据  
       $("#allotgrid").datagrid('reload');  
	   return false;
  }
 
  function getId() {
		return $('#allotgrid').attr('idField');
	}
  $(function(){
	$('#allotgrid').datagrid({
		url : tableAllotListAction,
		method:"post",
		pagination:true,
		//rownumbers:true,
		striped:true,
		loadMsg:"数据正在加载中",
		pageSize:25
	});
	loadAllotPagination();  
	});
	$('#allotgrid').pagination.defaults.showPageList = false;
	function loadAllotPagination() {
	var p = $('#allotgrid').datagrid('getPager');
	if (p) {
		$(p).pagination({
			pageSize:25,
			beforePageText:'第',
			afterPageText:'页          共  {pages} 页',
			displayMsg : '当前显示从{from} - {to}条记录， 共{total}记录',
			onBeforeRefresh : function() {
			}
		});
	}
	}
	
	
	//提交
	function submitForm(){
		    var _allotUserAction="${path}/admin/role/allotRole.do"
		    var roleId=$("#roleId").val();
			$.messager.progress({title:'请稍候',msg:'数据提交中...'});
			var selects=$('#allotgrid').datagrid('getSelections');
			if(selects.length<1){
				$.messager.alert('提示', '<br>请选中您要分配的用户!', 'info');
			}else{
				var userIds=[];
				for(var i=0; i<selects.length;i++){
					userIds.push(selects[i][getId()]);
				}
				var userId=userIds.join(",");
				$.ajax({
					type : "POST",
					url :_allotUserAction ,
					dataType:"json",
					data : {userIds: userId,roleId:roleId},
					success : function(data) {
						$.messager.progress('close');
						closeDialog();
						$.messager.alert("提示",data.msg,"info");
						reflashuser();
					}
				});
			}
		}
	
	function setCheckUser(value,row,index){
		 <c:forEach items="${listUser}" var="user">
			 if(row.uid=="${user.uid}"){
				 $("#allotgrid").datagrid("selectRow",index); 
			 }
	     </c:forEach> 
	     return value;
	}
  </script>
</body>
</html>