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
			showDetail();
        }
	});
	
	$('#detailgrid').datagrid({
		url : detailListAction,
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
		queryParams.orderCode = $("#orderCode", $("#tb")).val();
		queryParams.startTime = $("#startTime").datebox('getValue');
		queryParams.endTime = $("#endTime").datebox('getValue');
		queryParams.memberName = $("#memberName", $("#tb")).val();
		//重新加载datagrid的数据  
		$("#dbgrid").datagrid('reload');  
		return false;
	});
	
	$("#btnDeliver").click(function(){
		deliver();
		return false;
	});
	
	
	
});
$('#dbgrid').pagination.defaults.showPageList = false;
$('#detailgrid').pagination.defaults.showPageList = false;
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
	var p2 = $('#detailgrid').datagrid('getPager');
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
function getId() {
	return $('#dbgrid').attr('idField');
}
/**
 * 列表刷新
 */
function reflash() {
	$('#dbgrid').datagrid('clearSelections');
	$('#dbgrid').datagrid('reload');
}

var payStatus = {"0":"未支付","1":"已支付"};
function getMemeber(val,row){
	return row.member.realName;
}
function getAddr(val,row){
	return row.addr.address;
}
function getPhone(val,row){
	return row.addr.mp;
}
function getPayStatus(val,row){
	return payStatus[val];
}
function getEvent(val,row){
	if(row.cmdPrice.event){
		return row.cmdPrice.event.title; 
	}
}
function showDetail(){
	var queryParams = $('#detailgrid').datagrid('options').queryParams;
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var ids = selects[0][getId()];
		queryParams.orderId = ids;
	}
	
	//重新加载datagrid的数据  
	$("#detailgrid").datagrid('reload');  
}

function deliver(){
	var selects =  $('#dbgrid').datagrid('getSelections');
	if (selects.length==1) {
		var uid = selects[0][getId()];	
		$.ajax({
			url:_deliverAction,
			dataType:"json",
			data:{orderId:uid},
			type:"post",
			success:function(data){
				if(data.status){
					reflash();
				}
				$.messager.alert('提示', data.message);
			}
			
		});
	} else if(selects.length < 1) {
		$.messager.alert('提示', '<br>请选中您要修改的记录!', 'info');
	}else if(selects.length > 1) {
		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
	}
}

