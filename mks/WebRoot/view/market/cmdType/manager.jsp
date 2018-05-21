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
    <script type="text/javascript" src="${path}/view/js/cmd_type_list.js"></script>
    <style type="text/css">
    </style>
</head>
 <body class="easyui-layout container">
  <c:if test="${sessionAdmin.dataRange  != 'owner' }">
  <div data-options="region:'west',title:'分类选择',collapsible:false" style="width:220px; padding: 0 0 0 5px;">
	<ul id="tree" class="easyui-tree" data-options="url:'${path}/cmd/type/tree.do',animate:true,lines:true,onClick:treeClick,loadMsg:'数据正在加载中'">
	</ul>
</div>
</c:if>
<div region="center" style="padding:0px;" border="false">
<div class="easyui-layout" data-options="fit:true">
		<div data-options="region:'center',title:'子分类',split:true,border:false" style="height:50px">
	         <table id="dbgrid" fit="true" pagination="true" rownumbers="true" iconCls="icon-edit" idField="cmdType" toolbar="#tb" >
				<thead>
					<tr>
						<th field="cmdType" width="50" checkbox="true"></th>
						<th field="typeName" width="150">分类</th>
					</tr>
				</thead>
			</table>
	<div id="tb">
			<input type="hidden" id="parentId"/>
		<div class="toolbar_class">
			<a id="addbtn" href="javascript:void(0)" onclick="insert()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a id="updbtn" href="javascript:void(0)" onclick="edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a id="delbtn" href="javascript:void(0)" onclick="removeFn()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
		</div>
	</div>
		</div>
		
		
    </div>
    <div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
</div>
  <script type="text/javascript">
  var tableListAction = "${path}/cmd/type/list.do";
  var _addAction="${path}/cmd/type/add.do";
  var _updateAction="${path}/cmd/type/edit.do";
  var _deleteAction="${path}/cmd/type/del.do";
  var _addTitle="添加子分类";
  var _updateTitle="修改子分类";
  var _dialogWidth=700;
  var _dialogHeight=500;
  $(function(){
	  $('#tableBox').datagrid({
		    rownumbers:true,
		    striped:true,
			loadMsg:"数据正在加载中"
		});
  })
  function insert(){
	  _addAction = "${path}/cmd/type/add.do?parentId="+$("#parentId").val();
	  openEditorDialog(_addTitle, _dialogWidth, _dialogHeight, _addAction);
  }
  //查询方法
  function find(){
	   var queryParams = $('#dbgrid').datagrid('options').queryParams;  
       queryParams.parentId=$("#parentId").val();
      	//重新加载datagrid的数据  
       $("#dbgrid").datagrid('reload');  
	   return false;
  }
  
 function treeClick(node){
	 $("#parentId").val(node.id);
	 find();
 }
 
 

 
</script>
</body>
</html>