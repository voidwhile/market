$(function() {
	$('#dbgrid').datagrid({
		url : tableListAction,
		method:"post",
		pagination:true,
		rownumbers:true,
		striped:true,
		loadMsg:"数据正在加载中",
		pageSize:25,
		onClickRow : function() {
			showPrice();
        },
        onCheck:function(){
        	showPrice();
        }
	});
			
	loadPagination();  
	
	$('#pricegrid').datagrid({
		url : _priceList,
		method:"post",
		pagination:true,
		rownumbers:true,
		striped:true,
		loadMsg:"数据正在加载中",
		pageSize:25
	});
});
$('#dbgrid').pagination.defaults.showPageList = false;
function loadPagination() {
	var p = $('#dbgrid').datagrid('getPager');
	if (p) {
		$(p).pagination({
			pageSize:25,
			beforePageText:'第',
			afterPageText:'页          共  {pages} 页',
			displayMsg : '当前显示从{from} - {to}条记录， 共{total}记录',
			onBeforeRefresh : function() {

			}
		});
	}
}

/**
 * 列表刷新
 */
function reflash() {
	$('#dbgrid').datagrid('clearSelections');
	$('#dbgrid').datagrid('reload');
}

/**
 * 添加功能
 */
function insert(){
	openEditorDialog(_addTitle, _dialogWidth, _dialogHeight, _addAction);
	$("#openDialogDiv").dialog("center");
}

/**
 * 添加价格
 */
function addPrice(){
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];	
		openPriceEditorDialog("添加价格", 400, 300, _addPrice+"?cmdId="+uid);
		$("#openDialogDiv").dialog("center");
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中商品!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}

/**
 * 修改价格
 */
function editPrice(){
	var selects =  $('#pricegrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][$('#pricegrid').attr('idField')];	
		openPriceEditorDialog("修改价格", 400, 300, path+"/cmd/price/edit.do?priceId="+uid);
		$("#openDialogDiv").dialog("center");
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中价格!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}

function removePrice(){
	var ids = [];
	var rows = $('#pricegrid').datagrid('getSelections');
	if (rows.length < 1) {
		$.messager.alert('提示', '<br>请选中您要删除的记录!', 'info');
		return;
	}
	
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i][$('#pricegrid').attr('idField')]);
	}	
	$.messager.confirm('警告', '<br>您确认要删除选中的记录数据吗?', function(r) {
		if (r) {
			$.ajax({
				type : "POST",
				url : path+"/cmd/price/del.do",
				traditional:true,
				dataType:"json",
				data : {ids:ids},
				success : function(obj) {
					if(obj.status){
						$.messager.alert("提示", obj.message);
						$('#pricegrid').datagrid('clearSelections');
						$('#pricegrid').datagrid('reload');
					} else {
						$.messager.alert("提示", obj.message);
					}
				}
			});
		}
	});
}

function showPrice(){
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];
		$('#pricegrid').datagrid({
			url : _priceList+"?cmdId="+uid,
			method:"post",
			pagination:true,
			rownumbers:true,
			striped:true,
			loadMsg:"数据正在加载中",
			pageSize:25
		});
	}
}



/**
 * 修改功能
 */
function edit() {	
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];	
		update(uid);
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}
function update(id){
	openEditorDialog(_updateTitle, _dialogWidth, _dialogHeight, _updateAction+"?uid="+id);
	$("#openDialogDiv").dialog("center");
}

/**
 * 删除功能
 */
function removeFn(){
	var ids = [];
	var rows = $('#dbgrid').datagrid('getSelections');
	if (rows.length < 1) {
		$.messager.alert('提示', '<br>请选中您要删除的记录!', 'info');
		return;
	}
	
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i][getId()]);
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
/**
 * 详情
 */
function detail(){
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];
		openDialog(_detailTitle,_dialogWidth,_dialogHeight,_detailAction+"?uid="+uid);
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中您要查看的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>查看详情，只能选择一条记录!', 'info');
	}
}
function getId() {
	return $('#dbgrid').attr('idField');
}

function setStatus(value,rowData,rowIndex){
	//1：为正常  0：不可用
	if(value==1){
		return "正常";
	}else if(value==0){
		return "不可用";
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

/**
 * 新增、编辑对话框
 * @param dialogTitle
 * @param dialogWidth
 * @param dialogHeight
 * @param url
 */
function openPriceEditorDialog(dialogTitle, dialogWidth, dialogHeight, url){
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
				priceSubmitForm();
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

