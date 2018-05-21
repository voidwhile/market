<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
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
    <script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
    <style type="text/css">
    </style>
</head>
 <body class="easyui-layout">
 	<input type="hidden" id="driverUserIds" value='${driverUserIds}'/>
		<div data-options="region:'center',title:'用户列表',split:true,border:false" style="height:425px;width:785px;">
	         <table id="selUserGrid" fit="true" iconCls="icon-edit" idField="uid" toolbar="#selUsertb" singleSelect="false" style="height: 350px" pagination="true" rownumbers="true">
				<thead>
					<tr>
						<th field="uid" name="uid" width="50" checkbox="true" ></th>
						<th field="realName" width="100">姓名</th>
						<th field="gender" width="50" formatter="transGender">性别</th>
						<th field="phone" width="100" formatter="">手机</th>
						<th field="qq" width="100" formatter="">QQ</th>
						<th field="tel" width="100" formatter="">电话</th>
						<th field="deptName" width="100" formatter="">所在部门</th>
						<th field="status" width="100" formatter="setStatus">状态</th>
					</tr>
				</thead>
			</table>
		<div id="selUsertb">
			<table class="searchbar_table" border="0"  cellpadding="0" cellspacing="0">
				<tr>
				
					<th width="80" align="right">性别:</th>
	        		<td width="100">
		        		<div class="controls">
		        			<select name="gender" id="gender">
		        			    ${xbOption }
							</select>
						</div>
					</td>
					<th width="80" align="right">用户名:</th>
					<td width="100"><input type="text" name="userName" id="userName"/></td>
					<th width="80" align="right">手机:</th>
					<td width="100"><input type="text" name="mobile" id="mobile"/></td>
					<td align="left"><button  type="button" id="comSel_btnSearch" class="button button-primary" onclick="find();">搜索</button></td>
	        	</tr>
			</table>
		</div>
	</div>
  <script type="text/javascript">
  var tableSelUserListAction = "${path}/admin/carinfo/comUserSelList.do?uid=${uid}";
  var _dialogWidth=700;
  var _dialogHeight=500;
  var enumObj = {"nan":"男","nv":"女","0":"未知"}
  function transGender(value,data,index){
	  return enumObj[value];
  }
  //查询方法
  function find(){
	   var queryParams = $('#selUserGrid').datagrid('options').queryParams;  
       queryParams.gender = $("#gender").val();  
       queryParams.userName = $("#userName").val();  
       queryParams.mobile=$("#mobile").val();
       //tb.html("");
      	//重新加载datagrid的数据  
       $('#selUserGrid').datagrid('clearSelections');
       $("#selUserGrid").datagrid('reload');  
	   return false;
	   
  }

 $(function() {
	 var radiovar = document.getElementsByName("uid");
	 var driverUserIds = $('#driverUserIds').val()
	 var strs = driverUserIds.split(",")
		$('#selUserGrid').datagrid({
			url : tableSelUserListAction,
			method:"post",
			pagination:true,
			rownumbers:true,
			striped:true,
			loadMsg:"数据正在加载中",
			pageSize:13,
			onLoadSuccess:function(data){ 
				for(var a=0;a<strs.length;a++ ){
					for(var i=0;i<radiovar.length;i++)
					  {
					   if(radiovar[i].value==strs[a])
					    radiovar[i].checked="checked";
					  }
				}
			        }  
		});
		loadPagination();  
	});
 
 
 
	$('#selUserGrid').pagination.defaults.showPageList = false;
	function loadPagination() {
		var p = $('#selUserGrid').datagrid('getPager');
		if (p) {
			$(p).pagination({
				pageSize:13,
				beforePageText:'第',
				afterPageText:'页          共  {pages} 页',
				displayMsg : '当前显示从{from} - {to}条记录， 共{total}记录',
				onBeforeRefresh : function() {
					$('#selUserGrid').datagrid('clearSelections');
				    $("#selUserGrid").datagrid('reload');
				}
			});
		}
	}

	function getId() {
		return $('#selUserGrid').attr('idField');
	}
	
	
	
</script>
</body>
</html>