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
    <script type="text/javascript" src="${path}/view/js/user_list.js"></script>
    <style type="text/css">
    </style>
</head>
 <body class="easyui-layout container">
  <c:if test="${sessionAdmin.dataRange  != 'owner' }">
  <div data-options="region:'west',title:'部门选择',collapsible:false" style="width:220px; padding: 0 0 0 5px;">
	<ul class="easyui-tree" data-options="url:'${path}/admin/org/tree.do',animate:true,lines:true,onClick:treeClick,loadMsg:'数据正在加载中'">
	</ul>
</div>
</c:if>
<div region="center" style="padding:0px;" border="false">
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'用户列表',split:true,border:false" style="height:50px">
	         <table id="dbgrid" fit="true" pagination="true" rownumbers="true" iconCls="icon-edit" idField="uid" toolbar="#tb"  singleSelect="true" >
				<thead>
					<tr>
						<th field="uid" width="50" checkbox="true"></th>
						<th field="userName" width="150">用户名</th>
						<th field="realName" width="100">姓名</th>
						<th field="gender" width="50" formatter="transGender">性别</th>
						<th field="userPosition" width="100" formatter="">职位</th>
						<th field="deptName" width="100" formatter="">所在部门</th>
						<th field="phone" width="100" formatter="">手机</th>
						<th field="qq" width="100" formatter="">QQ</th>
						<th field="tel" width="100" formatter="">电话</th>
						<th field="status" width="100" formatter="setStatus" hidden="true">状态</th>
						<th field="birthday" width="100" formatter="">出生日期</th>
						<th field="createTime" width="150" formatter="">创建时间</th>
					</tr>
				</thead>
			</table>
	<div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<input type="hidden" id="orgId"/>
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
				<td align="left"><button  type="button" id="btnSearch" class="button button-primary" onclick="find();">搜索</button></td>
        	</tr>
		</table>
		<div class="toolbar_class">
			<a id="addbtn" href="javascript:void(0)" onclick="insert()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a id="updbtn" href="javascript:void(0)" onclick="edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a id="delbtn" href="javascript:void(0)" onclick="removeFn()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
		</div>
		
		<div data-options="region:'south',title:'用户权限列表',border:false" style="height: 260px;">
			  <table id="tableBox" fit="true" iconCls="icon-edit" idField="userName" toolbar="#tb_role"  singleSelect="true">
			  <thead>
					<tr>
						<th  field="userName" width="50" checkbox="true"></th>
						<th  width="100" field="attr1">角色名称</th>
						<th  width="100" field="attr2">角色代码</th>
					</tr>
				</thead>
			</table>
			<div id="tb_role">
			<a id="addrolebtn" href="javascript:void(0)" onclick="empower();" class="easyui-linkbutton" iconCls="icon-add" plain="true">角色授权</a>
			<a id="delbtn" href="javascript:void(0)" onclick="delRole();" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			</div>
		</div>
    </div>
    <div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
</div>
  <script type="text/javascript">
  var tableListAction = "${path}/admin/user/list_data.do";
  var _addAction="${path}/admin/user/add.do";
  var _updateAction="${path}/admin/user/edit.do";
  var _deleteAction="${path}/admin/user/del.do";
  var _empowerAction="${path}/admin/user/v_empower.do"; //授权页面
  var _chanceRoleAction="${path}/admin/user/v_chanceRole.do"; //选中角色页面
  var _dialogRoleTitle="角色列表"
  var _addTitle="添加用户";
  var _updateTitle="修改用户";
  var _dialogWidth=700;
  var _dialogHeight=500;
  var enumObj = {"nan":"男","nv":"女","0":"未知"}
  function transGender(value,data,index){
	  return enumObj[value];
  }
  var tb=$("#rolelist");//角色列表
  function showRole(){
	  var selects =  $('#dbgrid').datagrid('getSelections');
	  if (selects.length==1) {
		  var uid = selects[0][getId()];
		  var _roleAction="${path}/admin/user/user_role_list.do?uid="+uid;
		  roleGrid(_roleAction);
	  }else{
		  tb.html(""); 
	  }
  }
  $(function(){
	  $('#tableBox').datagrid({
		    rownumbers:true,
		    striped:true,
			loadMsg:"数据正在加载中"
		});
  })
  function roleGrid(roleAction){
		$('#tableBox').datagrid({
			url : roleAction,
			method:"post",
			rownumbers:true,
			striped:true,
			loadMsg:"数据正在加载中",
			onClickRow:function(){
			}
		});
  }
  //删除
  function delRole(){
	  var row=$("#tableBox").datagrid("getSelected");
	  if (row==null) {
			$.messager.alert('提示', '<br>请选中您要删除的记录!', 'info');
			return;
		}
	     var userid=$("#dbgrid").datagrid("getSelected").uid;
	     var roleid=$("#tableBox").datagrid("getSelected").userName;
	     var url="${path}/admin/user/del_role.do";
	     $.messager.confirm('警告', '<br>您确认要删除选中的记录数据吗?', function(r) {
	 		if (r) {
	 			$.ajax({
	 				type : "POST",
	 				url : url,
	 				traditional:true,
	 				dataType:"json",
	 				data : {userid:userid,roleid:roleid},
	 				success : function(msg) {
	 					showRole();
	 					$.messager.alert('提示', "<br>"+msg.msg, 'info');
	 				}
	 			});
	 		}
	 	});
  }
  //查询方法
  function find(){
	   var queryParams = $('#dbgrid').datagrid('options').queryParams;  
       queryParams.gender = $("#gender").val();  
       queryParams.userName = $("#userName").val();  
       queryParams.orgId=$("#orgId").val();
       queryParams.mobile=$("#mobile").val();
       tb.html("");
      	//重新加载datagrid的数据  
       $("#dbgrid").datagrid('reload');  
	   return false;
  }
  
 function treeClick(node){
	 $("#orgId").val(node.id);
	 $("#btnSearch").click();
 }
 
 function openAddRoleDialog(){
		$("#openDialogEditDiv").dialog({
			title:"角色列表",
			href: _chanceRoleAction,
			closed:false,
	        width: 500,
	        height: 500,
	        buttons: [{
	            text: '确定',
	            id:"save",
	            iconCls: 'icon-save',
	            handler: function () {
	            	confirm();
	            }
	        }, {
	            text: '取消',
	            id:"cancel",
	            iconCls: 'icon-cancel',
	            handler: function () {
	                $('#openDialogEditDiv').dialog('close');
	            }
	        }]
		});
	}
//角色授权
  function empower(){
 	var selects=$('#dbgrid').datagrid('getSelections');
 	if (selects.length < 1) {
 		$.messager.alert('提示', '<br>请选中授权用户!', 'info');
 		return;
 	}
 	var uid=selects[0][getId()];
 	openEditorDialog(_dialogRoleTitle,500,500,_empowerAction+"?uid="+uid);
 } 
 
</script>
</body>
</html>