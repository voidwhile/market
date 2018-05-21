<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>编辑公告</title>
</head>
<body>
  <form id="editForm" class="easyui-form"  method="post">  
  	<input type="hidden" name="supplierId" value="${sessionAdmin.supplier.uid}"/>
    <input type="hidden" name="uid" value="${notice.uid}">
    <input type="hidden" value="${notice.status}" name="status" id="noticestatus"/>
  	<table class="tableBox" width="100%" border="0" cellspacing="1">
	       <tr>
		        <th><label>公告标题：</label></th>
		    	<td>
		    	<input type="text" value="${notice.title}" maxlength="100" style="width:300" id="title" name="title" value="${notice.title}" class="easyui-validatebox" data-options="required:true"/>
		    	<c:if test="${notice.status==0}">
		                            是否发布<input type="checkbox" id="isCheck">
		        </c:if>
		    	</td>
		   </tr>
		   <tr>
		    	<th><label >公告内容：</label></th>
		        <td>
		          <textarea id="editor_id" name="content" style="width:660px;height:280px;">
                          ${notice.content}
                  </textarea>
		        </td>
		    </tr>
		    <tr>
		      <th><label>发送人员：</label></th>
		    	<td>
	                <ul id="easyTree" class="easyui-tree" data-options="url:'${path}/admin/user/tree.do?selected=${selected}',animate:true,checkbox:true,lines:true,loadMsg:'数据正在加载中'">
	                </ul>
	                <input id="list" type="hidden" name="notifyUsers"/>
		    	</td>
		   </tr>
	 </table>
  </form>
  <script type="text/javascript">
  function submitForm(){
	 var title=$("#title").val();

	 var root=$("#easyTree").tree('getChildren');
	 if(title.length>100){
	    $.messager.alert("提示","<br>公告标题超过字数限制!","info");
	    $("#title").focus();
	    return false;
	 }
	 var content=$("#editor_id").val();
	 if(!content){
	   $.messager.alert("提示","<br>请输入公告内容!","info");
	   $("#editor_id").focus();
	   return false;
	 }
	 if(content.length>30000){
	    $.messager.alert("提示","<br>超过最大内容!","info");
	    $("#editor_id").focus();
	    return false;
	 }
	 var list="";
	 if(getChecked()){
	 var listdata=$("#list").val();
	  if(list=="" && listdata==""){
         $.messager.confirm('提示', '<br>你没有选择发送人员，默认发送所有人!', function(r) {
		 if (r) {
		   for(var i=0;i<root.length;i++){
	         if(list!='')
	         list+=',';
	         list+=root[i].id;    
          }
          $("#list").val(list);
        $.messager.progress({title:'请稍候',msg:'数据提交中...'});
		$('#editForm').form('submit', {
			url: '${path}/admin/notice/save.do',
			onSubmit: function(param){
				var isValid = $(this).form('enableValidation').form('validate');
				if (!isValid){
					$.messager.progress('close');
				}
				return isValid;	
			},
			success: function(data){
				$.messager.progress('close');
				var obj = jQuery.parseJSON(data);
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
		});
      }else{
        $.messager.progress({title:'请稍候',msg:'数据提交中...'});
		$('#editForm').form('submit', {
			url: '${path}/admin/notice/save.do',
			onSubmit: function(param){
				var isValid = $(this).form('enableValidation').form('validate');
				if (!isValid){
					$.messager.progress('close');
				}
				return isValid;	
			},
			success: function(data){
				$.messager.progress('close');
				var obj = jQuery.parseJSON(data);
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
	 }
	}
	var editor;
	KindEditor.ready(function(K) {
			editor = K.create('#editor_id',{
			afterBlur:function(){this.sync();},
		    themeType : 'simple',
	        items : ['removeformat', '|', 'formatblock', 'fontname', 'fontsize', 'bold',
	 				'italic', 'underline', 'strikethrough', '|','forecolor', 'hilitecolor', '|',  
	 				'justifyleft', 'justifycenter', 'justifyright', 'justifyfull','|', 'insertorderedlist', 
	 				'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript','|','source']
	 				//'cut', 'copy', 'paste', 'plainpaste', '|','image','insertfile', 'table', 
	 				//'hr', 'link', 'unlink', '|', 'source']
	    });
	});
	
   $(function(){
	 $("#isCheck").click(function(){
	   var check=$('#isCheck').attr("checked");
	   if(check){
	      $("#noticestatus").val(1);
	   }else{
	       $("#noticestatus").val(0);
	   }
	});
      
    });
    
//得到选择的树节点
function getChecked(){
	var nodes=$("#easyTree").tree('getChecked');
	var list="";
	for(var i=0;i<nodes.length;i++){
		if(list!=''){
        	list+=',';
		}
		if(nodes[i].id.indexOf('user')>=0){
         	list+=nodes[i].id;
		}
	}
	 if(list==""){
         return true;
      }else{
        $("#list").val(list);
        return true;
	}
}
</script>
</body>
</html>