$(function() {
	$('#dbgrid').datagrid({
		url : tableListAction,
		method:"post",
		pagination:true,
		rownumbers:true,
		striped:true,
		loadMsg:"数据正在加载中",
		pageSize:25,
		onClickRow:function(index,row){
			showMap(row[getId()], row["userName"]);
		}
	});
	loadPagination();  
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

//查询方法
function find(){
	var queryParams = $('#dbgrid').datagrid('options').queryParams;  
	queryParams.nodeId=$("#nodeId").val();
	queryParams.type = $("#type").val();  
	queryParams.beginTime=$("#beginTime").datebox("getValue");
	queryParams.endTime=$("#endTime").datebox("getValue");  
	//重新加载datagrid的数据  
	$("#dbgrid").datagrid('reload');  
	return false;
}


function treeClick(node){
	 $("#nodeId").val(node.id);
	 if(listMode=="list"){
		 find();
	 } else {
		 showMap();
	 }
}

function setType(value,rowData,rowIndex){
	//1：为正常  0：不可用
	if(value=="signin"){
		return "签到";
	}else if(value=="signout"){
		return "签退";
	}else if(value=="submit"){
		return "实时定位";
	}
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

/*****************************
 * 地图处理
 */

function initmap(){
	var map = new BMap.Map("map");
	var point = new BMap.Point(lng, lat);
	map.centerAndZoom(point, 15); 
	var marker = new BMap.Marker(point);  // 创建标注
	map.addOverlay(marker);               // 将标注添加到地图中
	marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
}

function showMap(positionId, userName){
	if(positionId == ""){
		$.messager.alert('提示', '请选择一条位置信息', 'info');
		initmap();
		return;
	}
    
    $.ajax({
    	method:"POST",
    	url: basePath+'/admin/attendance/show_map.do',
    	data:{positionId:positionId},
    	dataType:'json',
    	success:function(data){
    		if(data.retCode=="0"){
    			initmap();
    			$.messager.alert('提示', '未查到位置信息!', 'info');
    		}else{
    			var map = new BMap.Map("map");
    			var position=data.data;
    			var point = new BMap.Point(position.lon, position.lat);
    			map.addControl(new BMap.NavigationControl()); 
				map.enableScrollWheelZoom(); 
				map.centerAndZoom(point, 15);
				var marker = new BMap.Marker(point);  // 创建标注
				map.addOverlay(marker);               // 将标注添加到地图中
				//marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
				
				var searchInfoWindow = null;
				var content="<p><b>上报时间：</b>"+position.positionTime+"</p><p><b>经度：</b>"+position.lon
							+"&nbsp;&nbsp;<b>纬度：</b>"+position.lat+"</p><p><b>详细位置：</b>"+position.address+"</p>";
				searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
					title  : userName,      //标题
					width  : 290,             //宽度
					height : 105,              //高度
					panel  : "panel",         //检索结果面板
					enableAutoPan : true,     //自动平移
					searchTypes   :[
					]
				});
				searchInfoWindow.open(marker);
    		}
    	}
	});
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

function closeDialog(){
    $('#openDialogDiv').dialog("close");
}

