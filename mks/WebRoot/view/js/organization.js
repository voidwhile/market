/**
 * 列表刷新
 */
function reflash() {
	
	$('#treegrid').treegrid('clearSelections');
	$('#treegrid').treegrid('reload');
}

/**
 * 添加功能
 */
function insert(){
	openEditorDialog(_addTitle, _dialogWidth, _dialogHeight, _addAction);
}

/**
 * 修改功能
 */
function edit() {	
	var selects =  $('#treegrid').treegrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0]["uid"];	
		update(uid);
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}
function update(id){
	openEditorDialog(_updateTitle, _dialogWidth, _dialogHeight, _updateAction+"?uid="+id);
}

/**
 * 删除功能
 */
function removeFn(){
	var ids = [];
	var rows = $('#treegrid').treegrid('getSelections');
	if (rows.length < 1) {
		$.messager.alert('提示', '<br>请选中您要删除的记录!', 'info');
		return;
	}
	
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i]["uid"]);
	}	
	del(ids);
}

function del(ids) {
	$.messager.confirm('警告', '<br>您确认要删除选中的记录数据吗?', function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : _deleteAction,
				traditional:true,
				dataType:"json",
				data : {ids:ids},
				success : function(msg) {
					reflash();
					$.messager.alert('提示', msg.msg, 'info');
				}
			});
		}
	});
}

//格式化状态
function setStatus(value,rowData,rowIndex){
	//1：为正常  0：不可用
	if(value==1){
		return "正常";
	}else if(value==0){
		return "<font color='#999999'>不可用</font>";
	}
}

/*****************************
* 对话框处理
*/

/**
 * 无保存按钮对话框
 * @param dialogTitle
 * @param dialogWidth
 * @param dialogHeight
 * @param url
 */
function openDialog(dialogTitle, dialogWidth, dialogHeight, url){
	$("#openDialogDiv").dialog({
		title:dialogTitle,
		href: url,
		closed:false,
        width: dialogWidth,
        height: dialogHeight,
        buttons: []
	});
}

/**
 * 新增、编辑对话框
 * @param dialogTitle
 * @param dialogWidth
 * @param dialogHeight
 * @param url
 */
function openEditorDialog(dialogTitle, dialogWidth, dialogHeight, url){
	$("#openDialogDiv").dialog({
		title:dialogTitle,
		href: url,
		closed:false,
        width: dialogWidth,
        height: dialogHeight,
        buttons: [{
            text: '保存',
            id:"save",
            iconCls: 'icon-save',
            handler: function () {
            	submitForm();
            }
        }, {
            text: '取消',
            id:"cancel",
            iconCls: 'icon-cancel',
            handler: function () {
                $('#openDialogDiv').dialog('close');
            }
        }]
	});
}

function closeDialog(){
    $('#openDialogDiv').dialog("close");
}

