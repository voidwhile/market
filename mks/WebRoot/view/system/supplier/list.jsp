<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>公告管理</title>
<%@ include file="../../common/IncludeHead.jsp"%>
 <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">  
    <script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" charset="utf-8" src="${path}/library/editor/kindeditor.js"></script>
    <script type="text/javascript" src="${path}/view/js/common_list.js"></script> 
    <style type="text/css">
    </style>
    <script type="text/javascript">
		  var tableListAction = "${path}/admin/supplier/list.do";
		  var _addAction = "${path}/admin/supplier/v_add.do";
		  var _updateAction="${path}/admin/supplier/v_edit.do";
		  var _deleteAction="${path}/admin/supplier/del.do";
		  var _approvalAction="${path}/admin/supplier/approval.do";
		  var _addTitle="添加企业";
		  var _updateTitle="修改企业";
		  var _dialogWidth=500;
		  var _dialogHeight=400;
		  
		  //查询方法
		  function find(){
			   var queryParams = $('#dbgrid').datagrid('options').queryParams;  
		       queryParams.level = $("#level").val();  
		       queryParams.supplierName = $("#supplierName").val();  
		       queryParams.supplierCode = $("#supplierCode").val();  
		      	//重新加载datagrid的数据  
		       $("#dbgrid").datagrid('reload');  
			   return false;
		  }
		  
		  var enumObj = {"0":"免费客户","1":"普通客户","2":"VIP客户","3":"金牌客户","4":"钻石客户","5":"重点客户"}
		  function transSupplierLevel(value,data,index){
			  return enumObj[value];
		  }
		  function approval(value,data,index){
			  if(value==""||value==null){
				  return "未审核";
			  }else{
				  return "已审核";
			  }
		  }
		  //认证企业
              function approvalSu() {		
            	  var selects =  $('#dbgrid').datagrid('getSelections');
            		if (selects.length==1) {
            			var uid = selects[0][getId()];	
            			$.ajax({
    						type : "POST",
    						url : _approvalAction,
    						traditional:true,
    						dataType:"json",
    						data : {uid:uid},
    						success : function(msg) {
    							reflash();
    							$.messager.alert('提示', '<br>'+msg.msg, 'info');
    						}
    					});
            		} else if(selects.length < 1) {
            			$.messager.alert('提示', '<br>请选中您要审核的企业!', 'info');
            		}
				}
   </script>
</head>
<body class="easyui-layout container">
  <div region="center" style="padding:0px;" border="false">
<div class="easyui-layout" data-options="fit:true">
	         <table id="dbgrid" fit="true" iconCls="icon-edit" idField="uid" toolbar="#tb"  singleSelect="true">
				<thead>
					<tr>
						<th field="uid" width="50" checkbox="true"></th>
						<th field="supplierCode" align="center" width="200">企业编号</th>
						<th field="supplierName" align="center" width="200">企业名称</th>
						<th field="supplierLevel" align="center" width="180" formatter="transSupplierLevel">企业等级</th>
						<th field="provinceName" width="150">省份</th>
						<th field="cityName" width="150">市</th>
						<th field="countryName" width="150">区域</th>
						<th field="address" width="150">地址</th>
						<th field="contacts" align="center" width="130">联系人</th>
						<th field="contactNumber" align="center" width="130" formatter="">联系人号码</th>
						<th field="createTime" align="center" width="130" formatter="">创建日期</th>
						<th field="approvalTime" align="center" width="130" formatter="approval">审核状态</th>
					</tr>
				</thead>
			</table>
	<div id="tb">
			<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<input type="hidden" id="orgId"/>
			<tr>
			    <th width="100" align="right">企业名字:</th>
				<td width="100"><input type="text" id="supplierName"/></td>
			    <th width="100" align="right">企业编号:</th>
				<td width="100"><input type="text" id="supplierCode"/></td>
        		<th width="100" align="right">企业等级:</th>
        		<td width="80">
	        		<div class="controls">
	        			<select name="level" id="level">
	        			    <option value="">全部</option>
	        			    <option value="0">免费客户</option>
							<option value="1">普通客户</option>
							<option value="2">VIP客户</option>
							<option value="3">金牌客户</option>
							<option value="4">钻石客户</option>
							<option value="5">重点客户</option>
						</select>
					</div>
				</td>
				<td align="left"><button  type="button" id="btnSearch" class="button button-primary" onclick="find();">搜索</button></td>
        	</tr>
		</table>
		<div class="toolbar_class">
			<a id="addbtn" href="javascript:void(0)" onclick="insert()" class="easyui-linkbutton" iconCls="icon-add" plain="true">新增</a>
			<a id="updbtn" href="javascript:void(0)" onclick="edit()" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
			<a id="delbtn" href="javascript:void(0)" onclick="removeFn()" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除</a>
			<a id="appbtn" href="javascript:void(0)" onclick="approvalSu()" class="easyui-linkbutton" iconCls="icon-ok" plain="true">审核</a>
		</div>
	</div>
    <div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
    </div>
</body>
</html>