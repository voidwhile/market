<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>添加子分类</title>
</head>
<body>
  <div id="editDiv" class="easyui-panel" border="false" style="padding:5px 10px">
  <form id="editForm" class="easyui-form"  method="post">  
  	<table class="tableBox" width="100%" border="0" cellspacing="1">
	       <tr>
		        <th><label>父级分类：</label></th>
		    	<td>
		    	<select name="parentId">
		    		<option value="0">无</option>
		    		<c:if test="${parentId!=null }">
		    		<option value="${parentId }" selected="selected">${parentName}</option>
		    		</c:if>
		    	</select>
		    	</td>
		   </tr>
		   <tr>
		    	<th><label >子分类：</label></th>
		        <td><input type="text" id="typeName" name="typeName" class="easyui-validatebox textbox" data-options="required:true" missingMessage="请输子分类！" value=""   /></td>
		    </tr>
	         
	 </table>
  </form>
  <!-- <div id="openDialogEditDiv" class="easyui-dialog" title="编码维护" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div> -->
  </div>
  <script type="text/javascript">
  function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: '${path}/cmd/type/save.do',
		onSubmit: function(user){
			var isValid = $(this).form('enableValidation').form('validate');
			if (!isValid){
				$.messager.progress('close');
			}
			return isValid;	
		},
		success: function(data){
			$.messager.progress('close');
			var obj = $.parseJSON(data);
			if(obj.status){
				$.messager.alert("提示", obj.message);
				reflash();
				closeDialog();
			} else {
				$.messager.alert("提示", obj.message);
			}
			
		}
	});
}
  
   $.parser.onComplete=function(){
	    
	} 
</script>
</body>  
</html>
