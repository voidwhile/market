<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

</head>
<body>
<div id="editDiv" class="easyui-panel" border="false" style="padding:5px 10px">
  <form id="editForm" class="easyui-form"  method="post"> 
	<table id="cmdTab" class="tableBox" width="100%" border="0" cellspacing="1">
		<tr>
			<th><label>名称:</label></th>
			<td><input class="easyui-validatebox textbox" type="text" id="cmdName" name="cmdName" style="width:250px" missingMessage="名称不能为空！" data-options="required:true" /></td>
		    
			<th><label>分类:</label></th>
			<td><select id="cmdType" name="ccCmdType" onchange="addLabel();">
			<option value="0">--请选择--</option>
			${typeOptions}
			</select></td>
		</tr>
		<tr>
			<th><label>标签:</label></th>
			<td  colspan="3" id="labels"></td>
		</tr>
		
		<tr>
			<th><label>品牌:</label></th>
			<td><select id="cmdBrand" name="cmdBrand" >
			<option value="0">--请选择--</option>
			${brandOptions}
			</select></td>
			<th><label>状态:</label></th>
			<td>
			<select name="cmdStatus" >
			${statusOptions}
			</select>
			</td>
	
		</tr>
		<tr>
			<th><label>价格:</label></th>
			<td><input class="easyui-validatebox textbox" type="text" id="price" name="price" style="width:250px" missingMessage="价格不能为空！" data-options="required:true" />元</td>
			<th><label>产地:</label></th>
			<td><input class="easyui-validatebox textbox" type="text" id="prodPlace" name="prodPlace" style="width:250px"  /></td>
		</tr>
		
		<tr>
			<th><label>储存条件:</label></th>
			<td colspan="3"><input class="easyui-validatebox textbox" type="text" id="storageCondition" name="storageCondition" style="width:250px" /></td>
		</tr>
		
		<tr>
			<th><label>图片:</label></th>
			<td  colspan="3">
				<input type="file" name="pic" id="pic" onchange="saveFile(this.id)">
	        </td>
		</tr>
		<tr>
			<th><label>预览:</label></th>
			<td  colspan="3" id="picShow"></td>
		</tr>
		<tr>
			<th><label>上架时间 :</label></th>
			<td><input  class="easyui-datebox textbox" type="text" name="dputawayTime" missingMessage="地址电话不能为空！" data-options="required:false" /></td>
	
			<th><label>生产日期:</label></th>
			<td><input class="easyui-datebox textbox" type="text" name="dproduceDate"   /></td>
		</tr>
		<tr>
			<th><label>保质期:</label></th>
			<td><input  class="easyui-textbox" type="text" name="shelfLife"/></td>
			<th><label>到期日:</label></th>
			<td><input class="easyui-datebox textbox" type="text" name="dexpirationDate"  /></td>
	    </tr>
		<tr id="tr_sup">
			<th><label>供应商:</label></th>
			<td>
			<select name="supplierId" >
			<option value="0">--请选择--</option>
			${supplierOptions}
			</select>
			</td>
			<th><label>添加规格:</label></th>
			<td>
				<a class="easyui-linkbutton" id="1" onclick="addSft();" iconCls="icon-add" plain="true" href="javascript:void(0)"></a>
			</td>
		</tr>
		<tr id="tr_sft">
			<th><label>规格:</label></th>
			<td  colspan="3">
			
			<select name="specification" >
			${specificationOptions}
			</select>
			<input type="text" id="sftValue" name="sftValue" style="width:400px" />
			<a class="easyui-linkbutton" id="1" onclick="rmSft(this);" iconCls="icon-remove" plain="true" href="javascript:void(0)"></a>
			</td>
		</tr>
		<tr>
			<th><label>商品详情:</label></th>
			<td  colspan="3">
			<script type="text/plain" id="description" name="description"
					style="width:95%;height:360px;"/>
			</td>
		</tr>
		
	</table>
	<div id="file-group"></div>
	<div id="showImage" style="position: absolute;top: 100px;left: 200px;" ></div>
</form>
</div>
<script type="text/javascript">
//初始化百度编辑器
var editorOption = {
	initialFrameWidth: '100%',//编辑器宽度
	elementPathEnabled: false,//是否启用元素路径
	zIndex: 9999,//编辑器层级的基数
 }
 var editor = new baidu.editor.ui.Editor(editorOption);
 editor.render('description');
//获取编辑器对象
var ue = UE.getEditor('description');	
function submitForm(){
	$.messager.progress({title:'请稍候',msg:'数据提交中...'});
	$('#editForm').form('submit', {
		url: path+'/cmd/cmd/save.do',
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
function addSft(){
	if(document.getElementById("sftValue")){
		var tr = $("#tr_sft");
		var newtr = tr.clone();
		tr.after(newtr);
		tr.attr("id","");
		newtr.find("input[type='text']").val("");
	}else{
		var tr = "<tr id='tr_sft'><th width='100' align='right'>规格:</th><td colspan='3'><select name='specification' >${specificationOptions}</select><input type='text' id='sftValue' name='sftValue' value='${sft.sftVal }' style='width:400px' />"
		+"<a class='easyui-linkbutton l-btn l-btn-small l-btn-plain' id='1' onclick='rmSft(this);' iconcls='icon-remove' plain='true' href='javascript:void(0)' group=''><span class='l-btn-left l-btn-icon-left'><span class='l-btn-text l-btn-empty'>&nbsp;</span><span class='l-btn-icon icon-remove'>&nbsp;</span></span></a>"			 
		+"</td></tr>";
		$("#tr_sup").after(tr);
	}
}

function rmSft(el){
	$(el).parent().parent().remove();
}
function addLabel(){
	var cmdType = $("#cmdType").val();
	if(cmdType!=0&&$("#"+cmdType)){
		var typeName = $("#cmdType").find("option:selected").text();
		var labels = $("#labels").html();
		labels = labels +"<span style='border-color: red;' id='"+cmdType+"' onclick='removeLabel("+cmdType+");'>&nbsp;&nbsp;"+typeName+"<input type='hidden' name='cmdLabel' value='"+cmdType+"'></span>";
		$("#labels").html(labels);
		brandChange();
	}
}
function removeLabel(id){
	$("#"+id).remove();
	brandChange();
}
function brandChange(){
	var types = "";
	var whereClause = "1=1";
	$("#cmdTab").find("input[name='cmdLabel']").each(function(){
		types = types + "," +  this.value;
	});
	if(types!=""){
		whereClause="cmd_type in ("+types.substr(1)+")";
	}
	$.ajax({
		type:"post",
		dataType:"json",
		url:"${path}/admin/param/options.do",
		data:{
			tableName:'mk_cmd_brand',
			code:'brand_id',
			des:'brand_name',
			whereClause:whereClause,
			selected:''
		},
		success:function(msg){
			$("#cmdBrand option").remove();
			$("#cmdBrand").append("<option value='0'>--请选择--</option>");
			$("#cmdBrand").append(msg.options);
			
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
                    var file = "<input type='hidden' name='imgs' value='"+data.filePath+"'>";
                    $("#file-group").append(file);
                    $("#picShow").append("<img width='25' height='25' src='"+data.imgurl+"' onclick='showPic(this.src)'/>&nbsp;&nbsp;");
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