<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="../../common/IncludeHead.jsp"%>
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">  
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">
<link rel="stylesheet" type="text/css" href="${path}/library/css/form.css">          
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/view/js/organization.js"></script>
<script type="text/javascript">
var _addAction="${path}/admin/org/v_add.do";//添加URL
var _updateAction="${path}/admin/org/v_edit.do";//修改URL
var _deleteAction="${path}/admin/org/del.do" ;//删除URL
var _addTitle="添加组织机构";
var _updateTitle="组织机构修改";
var _dialogWidth=480;
var _dialogHeight=300;

$(function(){
	//查询方法
	$("#btnSearch").click(function(){
		var queryParams = $('#treegrid').treegrid('options').queryParams;
		queryParams.orgName = $("#orgName", $("#tb")).val();  
		queryParams.status = $("#status", $("#tb")).val(); 
		//重新加载treegrid的数据  
		$("#treegrid").treegrid('reload');  
		return false;
	});
});

</script>
</head>
<body class="easyui-layout container">
<div region="center">
	<table id="treegrid" title="组织机构管理" class="easyui-treegrid" border="false" 
			data-options="url: '${path}/admin/org/list.do',singleSelect: true,toolbar: '#tb',
                method: 'post',rownumbers: true,idField: 'uid',treeField: 'orgName'">
        <thead>
            <tr>
                <th field="orgName" width="320" halign="center">机构名称</th>
                <th field="orgCode" width="120" align="center">机构代码</th>
                <th field="ownerUsername" width="100" align="center">负责人</th>
                <th field="orgPhone" width="100" halign="center">联系电话</th>
                <th field="status" width="60" align="center" formatter="setStatus">状态</th>
            </tr>
        </thead>
    </table>
    <div id="tb">
		<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<tr>
				<th width="80" align="right">机构名称:</th>
				<td width="100"><input type="text" name="orgName" id="orgName" /></td>
				<th width="50" align="right">状态:</th>
				<td width="100">
					<div class="controls">
	        			<select name="status" id="status" style="width: 80px;">
				  			<option value="">全部</option>
				  			<option value="1">正常</option>
				  			<option value="0">不可用</option>
						</select>
					</div>
				</td>
				<td align="left">
					<button type="button" id="btnSearch" class="button button-primary">搜索</button>
				</td>
        	</tr>
		</table>
		<div class="toolbar_class">
			<a class="easyui-linkbutton" id="addbtn" iconCls="icon-add" plain="true" href="javascript:void(0)" onclick="insert()" >新增</a>
			<a class="easyui-linkbutton" id="updbtn" iconCls="icon-edit" plain="true" href="javascript:void(0)" onclick="edit()">修改</a>
			<a class="easyui-linkbutton" id="delbtn" iconCls="icon-remove" plain="true" href="javascript:void(0)" onclick="removeFn()">删除</a>
		</div>
	</div>
</div>
<div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
</body>
</html>