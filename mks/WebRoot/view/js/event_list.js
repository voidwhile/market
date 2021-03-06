$(function() {
	$('#dbgrid').datagrid({
		url : tableListAction,
		method:"post",
		pagination:true,
		rownumbers:false,
		striped:true,
		loadMsg:"数据正在加载中",
		pageSize:25,
		onClickRow : function() {
			showCmd();
        }
	});
	
	$('#cmdgrid').datagrid({
		url : cmdListAction,
		method:"post",
		pagination:true,
		rownumbers:false,
		striped:true,
		loadMsg:"数据正在加载中",
		pageSize:25
	});
			
	loadPagination();  
	
	//查询方法
	$("#btnSearch").click(function(){
		var queryParams = $('#dbgrid').datagrid('options').queryParams;
		queryParams.cmdName = $("#cmdName", $("#tb")).val();  
		queryParams.title = $("#title", $("#tb")).val();  
		queryParams.eveStatus = $("#eveStatus", $("#tb")).val();  
		queryParams.startTime = $("#startTime").datebox('getValue');  
		queryParams.endTime = $("#endTime").datebox('getValue');  
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
	
	$("#btnAddCmd").click(function(){
		var selects =  $('#dbgrid').datagrid('getSelections');
		if (selects.length==1) {
			var uid = selects[0][getId()];	
			openEditorDialog("添加商品", 800, 600, _addCmdAction+"?eventId="+uid);
			$("#openDialogDiv").dialog("center");
		} else if(selects.length < 1) {
			$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
		}else if(selects.length > 1) {
			$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
		}
		
	});
	
	
});
$('#dbgrid').pagination.defaults.showPageList = false;
$('#cmdgrid').pagination.defaults.showPageList = false;
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
	var p2 = $('#cmdgrid').datagrid('getPager');
	if (p2) {
		$(p2).pagination({
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
	openEditorDialog(_updateTitle, _dialogWidth, _dialogHeight, _updateAction+"?eventId="+id);
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
					$.messager.alert('提示', msg.message, 'info');
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

function closeDialog(){
    $('#openDialogDiv').dialog("close");
}

var statusTypes = {"0":"未开始","1":"进行中","2":"已结束"};
function getStatus(val,row){
	return statusTypes[val];
}
function showCmd(){
	var queryParams = $('#cmdgrid').datagrid('options').queryParams;
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var ids = selects[0][getId()];
		queryParams.eventId = ids;
	}
	
	//重新加载datagrid的数据  
	$("#cmdgrid").datagrid('reload');  
}
/**********************************************
 * 
*/




