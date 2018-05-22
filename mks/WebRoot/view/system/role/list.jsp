<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title></title>
<%@ include file="../../common/IncludeHead.jsp"%>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">  
    <script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${path}/view/js/role.js"></script>
    <style type="text/css">
    </style>
</head>
 <body class="easyui-layout container">
  <div data-options="region:'west',title:'角色列表',collapsible:false" border="false" style="width:360px; ">
	  <table id="dbgrid"  fit="true" iconCls="icon-edit" idField="uid" toolbar="#tb_role" singleSelect="true">
				<thead>
					<tr>
						<th field="uid" width="50" checkbox="true"></th>
						<th field="roleName" width="180">角色名</th>
						<th field="dataRange" formatter="formatterDataRange" width="120">数据范围</th>
					</tr>
				</thead>
			</table>
	<div id="tb_role">
		<div class="toolbar_class">
			<a id="addbtn" href="javascript:void(0)" onclick="insert()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a id="updbtn" href="javascript:void(0)" onclick="edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a id="delbtn" href="javascript:void(0)" onclick="removeFn()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
</div>
<div region="center" style="padding:0px;" border="false">
	<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'权限列表',split:true,border:false" style="height:50px">
	         <table id="treegrid" class="easyui-treegrid" style="height: 100%"
				data-options="url: '${path}/admin/menus/menus_tree.do',method: 'post',singleSelect:false,rownumbers: true,idField: 'uid',pagination:false,treeField: 'menuName',toolbar: '#tb_menu',loadMsg:'数据正在加载中',onClickRow:clickCheckOrCancel">
				<thead>
					<tr>
					    <th field="uid" checkbox="true"></th>
						<th field="menuName" width="500" formatter="setCheck">菜单名</th>
						<!-- <th field="url" width="150">url</th>
						<th field="status" width="80" formatter="transStatus">菜单状态</th>
						<th field="createTime" width="130" formatter="fmtDate">创建时间</th> -->
					</tr>
				</thead>
			</table>
	  <div id="tb_menu">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0" style="display: none;">
			<tr>
				<input type="hidden" name="roleId" id="roleId"/>
				<td align="left"><button  type="button" id="btnSearch" class="button button-primary" onclick="find();">搜索</button></td>
        	</tr>
		</table>
		<div class="toolbar_class">
			<a id="addMenubtn" href="javascript:void(0)" onclick="saveMenu()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">保存设置</a>
		</div>
	   </div>
		</div>
		<div data-options="region:'south',title:'用户列表',border:false" style="height: 350px;">
			  <table id="usergrid" fit="true" iconCls="icon-edit" toolbar="#tb_user">
			  <thead>
					<tr>
						<th field="roleCode" width="150">用户名</th>
						<th field="attr1" width="150">姓名</th>
						<th field="attr2" width="50" formatter="transSex">性别</th>
						<th field="attr3" width="100">手机</th>
						<th field="attr4" width="100">QQ</th>
						<th field="attr5" width="150">电话</th>
						<th field="birthday" width="100">出生日期</th>
					</tr>
				</thead>
			</table>
		<div id="tb_user">
			<div class="toolbar_class">
				<a id="addUserbtn" href="javascript:void(0)" onclick="allotUser()" class="easyui-linkbutton" iconCls="icon-add" plain="true">分配用户</a>
			</div>
		</div>
		</div>
    </div>
    <div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div> 
</div>
  <script type="text/javascript">
  var tableListAction = "${path}/admin/role/roleList.do";
  var userListAction="${path}/admin/role/list_ud.do";
  var _allotMenuAction = "${path}/admin/role/v_allot.do";//分配菜单
  var _allotUserAction = "${path}/admin/role/toAllotUser.do";//分配用户
  var _addAction="${path}/admin/role/v_add.do";//添加角色
  var _updateAction="${path}/admin/role/v_edit.do";//编辑角色
  var _deleteAction="${path}/admin/role/del.do";
  var _updateTitle="编辑角色";
  var _addMenuTitle="角色分配菜单";
  var _addUserTitle="角色分配用户";
  var _addTitle="添加角色";
  var _dialogWidth=500;
  var _dialogHeight=300;
  var _menuDialogWidth=600;
  var _menuDialogHeight=550;
  var _userDialogWidth=850;
  var _userDialogHeight=500;
  var enumObj = {"1":"正常","0":"不可用"}
  function transStatus(value,data,index){
	  return enumObj[value];
  }
  var sexObj = {"1":"男","2":"女","0":"未知"}//1男 2女 0未知
  function transSex(value,data,index){
	  return sexObj[value];
  }
  function formatterDataRange(value, row, index){
	  if(value=='full'){
			return "全部";
		}else if(value=='dept'){
			return "本部门";
		}else if(value=='owner'){
			return "自己";
		}
  }
  //查询方法
  function find(){
	   var queryParams = $('#treegrid').treegrid('options').queryParams; 
	   var userParams = $('#usergrid').datagrid('options').queryParams; 
	   queryParams.roleId=$("#roleId").val();
	   userParams.roleId=$("#roleId").val();
	    //设置树节点不要选中
	    $("#treegrid").treegrid("unselectAll"); 
	    //重新加载treegrid的数据  
      	$("#treegrid").treegrid('reload');  
      	//重新加载datagrid的数据  
      	$("#usergrid").datagrid('reload');  
	    return false;
  }
  function treeFormatter(node){
		var s = node.text;
	    return s;
	}
  //
  function checkRole(){
	  var selects =  $('#dbgrid').datagrid('getSelections');
	  var uid=selects[0][getId()];
	  $("#roleId").val(uid);
	  $("#btnSearch").click();
	}
  function fmtDate(value,row,index){
	  var date=new Date(value);
	  var year=date.getFullYear().toString();
	  var month=(date.getMonth()+1);
	  var day=date.getDate().toString();
	  var hour=date.getHours().toString();
	  var minutes=date.getMinutes().toString();
	  var seconds= date.getSeconds().toString();
	  if(month<10){
		  month="0"+month;
	  }
	  if(day<10){
		  day="0"+day;
	  }
	  if(hour<10){
		  hour="0"+hour;
	  }
	  if(minutes<10){
		  minuts="0"+minutes;
	  }
	  if(seconds<10){
		  seconds="0"+seconds;
	  }
	  return year+"-"+month+"-"+day+" "+hour+":"+minutes+":"+seconds;
  }
  
  function clickCheckOrCancel(node){
		$("#treegrid").treegrid("select",node._parentId);
	}
  //选中
  function setCheck(value,row){
	     if(row.flag==row.uid){
	    	 $("#treegrid").treegrid("select",row.uid); 
	     }
		 return value;
  }
  //保存菜单权限
  function saveMenu(){
	  var roleId=$("#roleId").val();
		if(roleId==""||roleId==null){
			$.messager.alert("提示","请选择角色！","info");
		}else{
	        var _allotMenuAction="${path}/admin/role/allotMenu.do"
		    var roleId=$("#roleId").val();
			$.messager.progress({title:'请稍候',msg:'数据提交中...'});
			var selects=$('#treegrid').datagrid('getSelections');
			if(selects.length<1){
				$.messager.alert('提示', '<br>请选中您要分配的菜单!', 'info');
			}else{
				var menuIds=[];
				for(var i=0; i<selects.length;i++){
					menuIds.push(selects[i]["uid"]);
				}
				
				$.ajax({
					type : "POST",
					url :_allotMenuAction ,
					dataType:"json",
					data : {menuIds: menuIds.join(","),roleId:roleId},
					success : function(data) {
						$.messager.progress('close');
						reflashmenu();
						closeDialog();
						$.messager.alert("提示",data.msg,"info");
					}
				});
			}
		}
  }
  </script>
</body>
</html>