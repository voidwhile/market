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
    <script type="text/javascript" src="${path}/library/js/notice_list.js"></script> 
    <style type="text/css">
    </style>
    <script type="text/javascript">
		  var tableListAction = "${path}/admin/notice/list.do";
		  var _addAction = "${path}/admin/notice/v_add.do";
		  var _updateAction="${path}/admin/notice/v_edit.do";
		  var _deleteAction="${path}/admin/notice/del.do";
		  var _releaseAction="${path}/admin/notice/release.do";
		  var _showAction="${path}/admin/notice/detail.do";
		  var _addTitle="添加公告";
		  var _updateTitle="修改公告";
		  var _showTitle="查看详情";
		  var _dialogWidth=800;
		  var _dialogHeight=500;
		  
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('#editor_id',{
		        themeType : 'simple',
		        items : ['undo', 'redo', 'removeformat', '|', 'formatblock', 'fontname', 'fontsize', 'bold',
		 				'italic', 'underline', 'strikethrough', '|','forecolor', 'hilitecolor', '|',  
		 				'justifyleft', 'justifycenter', 'justifyright', 'justifyfull','|', 'insertorderedlist', 
		 				'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', '/',
		 				'cut', 'copy', 'paste', 'plainpaste', '|','image','insertfile', 'table', 
		 				'hr', 'link', 'unlink', '|', 'source']
		    });
		});
		  //查询方法
		  function find(){
			   var queryParams = $('#dbgrid').datagrid('options').queryParams;  
		       queryParams.status = $("#status").val();  
		       queryParams.title = $("#title").val();  
		      	//重新加载datagrid的数据  
		       $("#dbgrid").datagrid('reload');  
			   return false;
		  }
		  
          //发布公告      
		   function release() {	
				var ids = [];
				var rows = $('#dbgrid').datagrid('getSelections');
				if (rows.length < 1) {
					$.messager.alert('提示', '<br>请选中您要发布的记录!', 'info');
					return;
				}
				for (var i = 0; i < rows.length; i++) {
				    var status=rows[i].status;
				    if(status=='1'){
				      $.messager.alert('提示','<br>请选择未发布的记录!', 'info')
				      return;
				    }
					ids.push(rows[i][getId()]);
				}	
				updateRelease(ids);
			}

            function updateRelease(ids) {		
				$.ajax({
					type : "POST",
					url : _releaseAction,
					traditional:true,
					dataType:"json",
					data : {ids:ids},
					success : function(msg) {
						reflash();
						$.messager.alert('提示', msg.msg, 'info');
					}
				});
			}
				
		function formatstatus(value, rowDate, rowIndex) {
		  if(value==0){
		    return "未发布";
		  } 
		  else if(value==1){
		    return "已发布";
		  }		
		}
		function formattime(value,rowDate,rowIndex){
		 if(rowDate.status==1)
		    return value;
		 else{
		    return "";
		 	}
		}
</script>
</head>
<body class="easyui-layout container">
  <div region="center" style="padding:0px;" border="false">
<div class="easyui-layout" data-options="fit:true">
	         <table id="dbgrid" fit="true" iconCls="icon-edit" idField="uid" toolbar="#tb">
				<thead>
					<tr>
						<th field="uid" width="50" checkbox="true"></th>
						<th field="title" halign="center" width="220">公告标题</th>
						<th field="releaseName" align="center" width="100">发布人</th>
						<th field="status" align="center" formatter="formatstatus">是否已发布</th>
						<th field="createTime" align="center" >创建时间</th>
						<th field="releaseTime" align="center" formatter="formattime">发布时间</th>
					</tr>
				</thead>
			</table>
	<div id="tb">
			<table class="searchbar_table" border="0" width="100%" cellpadding="0" cellspacing="0">
			<input type="hidden" id="orgId"/>
			<tr>
			    <th width="100" align="right">公告标题:</th>
				<td width="100"><input type="text" name="title" id="title"/></td>
				
        		<th width="100" align="right">发布状态:</th>
        		<td width="80">
	        		<div class="controls">
	        			<select name="status" id="status">
	        			    <option value="">全部</option>
	        			    <option value="0">未发布</option>
				  			<option value="1">已发布</option>

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
			<a id="updbtn" href="javascript:void(0)" onclick="release()" class="easyui-linkbutton"  plain="true">发布公告</a>
			
		</div>
	</div>
    <div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
    </div>
</body>
</html>