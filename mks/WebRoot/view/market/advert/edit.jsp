<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<div id="editDiv" class="easyui-panel" border="false" style="padding:5px 10px">
<form id="editForm" class="easyui-form" method="post" data-options="novalidate:true">
<input type="hidden" id="advertId" name="advertId" value="${advert.advertId }">
	<table id="cmdTab" class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr>
			<th width="10%" align="right">标题:</th>
			<td><input class="easyui-textbox" type="text" id="title" name="title" value="${advert.title }" style="width:250px" missingMessage="标题不能为空！" data-options="required:true" /></td>
			<th width="10%" align="right">商品:</th>
			<td>
			<select id="adCmdId" name="adCmdId" >
			<option value="0">--请选择--</option>
			${cmdOptions}
			</select>
			</td>
		</tr>
		<tr>
			<th align="right">开始时间:</th>
			<td><input class="easyui-datebox textbox" type="text" name="dstartTime" style="width:250px"  /></td>
			<th align="right">结束时间:</th>
			<td><input class="easyui-datebox textbox" type="text" name="dendTime" style="width:250px"  /></td>
		</tr>
		<tr>
			<th align="right">地址:</th>
			<td><input class="easyui-textbox" type="text" id="url" name="url" value="${advert.url }" style="width:250px"  /></td>
			<th width="10%" align="right">类型:</th>
			<td>
			<select id="adAdvertType" name="adAdvertType" >
			<option value="1" <c:if test="${advert.adAdvertType==1 }">selected</c:if> >轮播广告</option>
			<option value="2"<c:if test="${advert.adAdvertType==2 }">selected</c:if>>普通广告</option>
			
			</select>
			</td>
		
		</tr>
		<tr>
			<th width="10%" align="right">图片:</th>
			<td width="90%">
				<input type="file" name="pic" id="pic" onchange="saveFile(this.id)">
	        </td>
			<th width="10%" align="right">预览:</th>
			<td width="90%" id="picShow" ><img width='25' height='25' src='${imgUrl }${advert.imgPath}' onclick='showPic(this.src)'/> </td>
		</tr>
		<tr>
			<td align="center" colspan="4">详情</td>
		</tr>
		<tr>
			<td colspan="4"><textarea id="description" name="description" style="width:95%;height:360px;">${advert.description }</textarea></td>
		</tr>
		
	</table>
	<div id="file-group"></div>
	<div id="showImage" style="position: absolute;top: 100px;left: 200px; z-index: 9999" ></div>
</form>
</div>
<script type="text/javascript">
//初始化百度编辑器
var editorOption = {
	initialFrameWidth: '100%',//编辑器宽度
	elementPathEnabled: false,//是否启用元素路径
	zIndex: 8888,//编辑器层级的基数
 }
 var editor = new baidu.editor.ui.Editor(editorOption);
 editor.render('description');
//获取编辑器对象
var ue = UE.getEditor('description');	
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: path+'/evt/ad/save.do',
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
function saveFile(id){
	$.ajaxFileUpload({
		url:path+"/upload/imageUpload.do",
		type:'post',
		secureuri:false,
		fileElementId:id,
		dataType:'json',
		success:function(data){
			
			if (typeof (data.error) != 'undefined') {
                if (data.error != '0000') {
                    alert(data.error);
                } else {
                    var file = "<input type='hidden' name='imgPath' value='"+data.filePath+"'>";
                    $("#file-group").html(file);
                    $("#picShow").html("<img width='25' height='25' src='"+data.imgurl+"' onclick='showPic(this.src)'/>&nbsp;&nbsp;");
                }
            }
		},
		error: function (data, status, e)//服务器响应失败处理函数
        {
            alert("error:"+data+"|"+status+"|"+e);
        }
	});
}
function showPic(src){
	var pic = "<img width='400' src="+src+" onclick='hidePic()'/>";
	$("#showImage").html(pic);
	$("#showImage").show();
}
function hidePic(){
	$("#showImage").hide();
}
</script>
</body>
</html>