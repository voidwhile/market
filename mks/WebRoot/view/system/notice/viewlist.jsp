<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>公告查看</title>
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
		  var tableListAction = "${path}/admin/notice/viewlist.do";
		  var _addAction = "${path}/admin/notice/v_add.do";
		  var _updateAction="${path}/admin/notice/v_edit.do";
		  var _deleteAction="${path}/admin/notice/del.do";
		  var _releaseAction="${path}/admin/notice/release.do"
		  var _showAction="${path}/admin/notice/detail.do";
		  var _addTitle="添加公告";
		  var _updateTitle="修改公告";
		  var _showTitle="查看详情";
		  var _dialogWidth=800;
		  var _dialogHeight=600;
		  
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
   </script>
</head>
<body class="easyui-layout container">
  <div region="center" style="padding:0px;" border="false">
<div class="easyui-layout" data-options="fit:true">
	         <table id="dbgrid" fit="true" iconCls="icon-edit" idField="uid">
				<thead>
					<tr>
						<th field="uid" width="50" checkbox="true"></th>
						<th field="title" align="center" width="200">公告标题</th>
						<th field="releaseName" align="center" width="180">发布人</th>
						<th field="releaseTime" align="center" width="130" formatter="">发布时间</th>
					</tr>
				</thead>
			</table>
    <div id="openDialogDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
    </div>
</body>
</html>