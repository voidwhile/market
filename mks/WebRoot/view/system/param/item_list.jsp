<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
</head>
<body>
<table id="itemGrid" border="false" class="easyui-datagrid" data-options="iconCls: 'icon-edit',singleSelect: true,toolbar:'#itemToolbar',
	url:'${path}/admin/param/item_list.do?paramId=${paramId}',method: 'get',onClickRow: onClickRow">
	<thead>
		<tr>
			<th data-options="field:'itemCode',halign:'center',width:100,editor:{type:'validatebox', options:{required:true}}">编码</th>
     		<th data-options="field:'itemName',halign:'center',width:180,editor:{type:'validatebox', options:{required:true}}">内容</th>
     		<th data-options="field:'rank',align:'center',width:50,editor:'numberbox'">排序</th>
     		<th data-options="field:'status',align:'center',width:50,formatter:setStatus,editor:{type:'checkbox',options:{on:'1',off:'0'}}">状态</th>
     		<th data-options="field:'remark',halign:'center',width:200,editor:'text'">描述</th>
		</tr>
	</thead>
</table>
<div id="itemToolbar" style="height:auto">
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" onclick="append()">添加</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true" onclick="removeit()">删除</a>
	<a href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok',plain:true" onclick="accept()">保存</a>
</div>
<script type="text/javascript">
	var editIndex = undefined;
	function endEditing(){
		if (editIndex == undefined){
			return true
		}
		if ($('#itemGrid').datagrid('validateRow', editIndex)){
			$('#itemGrid').datagrid('endEdit', editIndex);
			editIndex = undefined;
			return true;
		} else {
			return false;
		}
	}
	function onClickRow(index){
		if (editIndex != index){
			if (endEditing()){
                    $('#itemGrid').datagrid('selectRow', index)
                            .datagrid('beginEdit', index);
                    editIndex = index;
 			} else {
                    $('#itemGrid').datagrid('selectRow', editIndex);
			}
		}
	}
        function append(){
            if (endEditing()){
                $('#itemGrid').datagrid('appendRow',{rank:'10',status:'1'});
                editIndex = $('#itemGrid').datagrid('getRows').length-1;
                $('#itemGrid').datagrid('selectRow', editIndex)
                        .datagrid('beginEdit', editIndex);
            }
        }
	function removeit(){
 		if (editIndex == undefined){return}
		$.messager.confirm('确认','是否真的删除?',function(r){
			if(r){
				$('#itemGrid').datagrid('cancelEdit', editIndex)
                    	.datagrid('deleteRow', editIndex);
				editIndex = undefined;
			}
		});
	}
	function accept(){
		if (endEditing()){
			var inserted = $('#itemGrid').datagrid('getChanges', "inserted");
			var deleted = $('#itemGrid').datagrid('getChanges', "deleted");
			var updated = $('#itemGrid').datagrid('getChanges', "updated");
			
			var	inserted_json = JSON.stringify(inserted);
			var	updated_json = JSON.stringify(updated);
			var	deleted_json = JSON.stringify(deleted);
			
			$.post("${path}/admin/param/item_save.do", {paramId:'${paramId}',inserted:inserted_json,deleted:deleted_json,updated:updated_json}, function(rsp) {
				if(rsp.status){
					$.messager.alert("提示", rsp.message);
					$('#itemGrid').datagrid('acceptChanges');
				} else {
					$.messager.alert("提示", rsp.message);
				}
			}, "json").error(function() {
				$.messager.alert("提示", "提交错误了！");
			});
		}
	}
</script>
</body>
</html>