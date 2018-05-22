<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <title>备份管理</title>
        <%@ include file="../../common/IncludeHead.jsp"%>
        <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
        <link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">  
        <link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">
        <link rel="stylesheet" type="text/css" href="${path}/library/css/form.css">          
        <script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
        <script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
        <script type="text/javascript" src="${path}/view/js/common_list.js"></script>
    </head>
    <body class="easyui-layout container">
    <input type="hidden" id="currentOptType" value="${optType }">
        <div region="center" border="true">
            <table id="dbgrid" title="备份管理" fit="true" iconCls="icon-list" idField="backupId" pagination="true" rownumbers="true" toolbar="#tb" singleSelect="true">
                <thead>
                    <tr>
                        <th field="backupId" checkbox="true"></th>
                        <th field="title" align="center" width="150">备份名称</th>
                        <th field="user" align="center" width="150" formatter="setUser">操作人</th>
                        <th field="optType" align="center" width="150" formatter="setOptType">操作类型</th>
                        <th field="backupRange" align="center" width="150" formatter="setBackupRange">数据范围</th>
                        <th field="baseDir" align="center" width="150" formatter="setBase">基于</th>
                        <th field="backupDate" align="center" width="150">备份时间</th>
                    </tr>
                </thead>
            </table>
            <div id="tb">
				<div class="toolbar_class">
				<table>
				<tr><td width="30%">				
					操作类型
					<input type="radio" id="auto" name="optType" value="1" <c:if test="${optType==1 }">checked</c:if>> 自动 <input type="radio" id="hand" name="optType" value="2" <c:if test="${optType==2 }">checked</c:if>> 手动
					</td>
					<td width="10%">
					<button type="button" class="button button-primary" onclick="saveOptType()">保存</button>
					</td>
					<td  width="20%">
					</td>
					<td  width="20%">
					<button type="button" class="button button-primary" onclick="backup()">备份</button>
					</td>
					<td  width="10%">
					<button type="button" class="button button-primary" onclick="copyBack()">还原</button>
					</td>
				</tr>
				</table>
				</div>
            </div>
        </div>
        <div id="openDialogDiv" class="easyui-dialog" style="width: 720px; height: 450px;" data-options="iconCls:'icon-th-list',modal:true,closed:true"></div>
        <script type="text/javascript">
            var tableListAction = "${path}/admin/data/list_data.do";
            var optType = {"1":"自动","2":"手动"}
            var backupRange = {"1":"全量","2":"差异"}
            function setBase(value,data,index){
            	if(data.baseDir!=null){
	            	return data.baseDir.title;
            	}
            }
            function setOptType(value,data,index){
            	return optType[value];
            }
            function setBackupRange(value,data,index){
            	return backupRange[value];
            }
            function setUser(value,data,index){
            	if(data.user!=null){
            		return data.user.realName;
            	}
            }
            
            //保存备份类型
            function saveOptType(){
            	var optType = $("input[name='optType']:checked").val();
            	$.ajax({
            		url:path+"/admin/data/saveOptType.do",
            		dataType:"json",
            		data:{optType:optType},
            		type:"post",
            		success:function(obj){
            			if(obj.errCode=="0000"){
            				$.messager.alert("提示", "保存成功");
            				$("#currentOptType").val(optType);
            			}else{
            				$.messager.alert("提示", obj.errMsg);
            			}
            		}
            		
            	});
            }
            
            //备份
            function backup(){
            	var optType = $("#currentOptType").val();
            	if(optType==1){
            		$.messager.alert("提示", "操作类型是自动，不能手动备份");
            		return;
            	}
            	$.messager.progress({title:'请稍候',msg:'执行中...'});            	
            	$.ajax({
            		url:path+"/admin/data/backup.do",
            		dataType:"json",
            		type:"post",
            		success:function(obj){
            			if(obj.errCode=="0000"){
            				$.messager.progress('close');
            				$.messager.alert("提示", "备份成功");
            				$('#dbgrid').datagrid('reload');
            			}else{
            				$.messager.alert("提示", obj.errMsg);
            			}
            		}
            		
            	});
            }
            
            //还原
            function copyBack(){
            	var selects =  $('#dbgrid').datagrid('getSelections');
            	if (selects.length==1) {
            		var uid = selects[0][getId()];	
            		$.ajax({
            			url:path+"/admin/data/copyBack.do",
            			dataType:"json",
                		data:{backupId:uid},
                		type:"post",
                		success:function(obj){
                			if(obj.errCode=="0000"){
                				$.messager.alert("提示", "还原成功");
                			}else{
                				$.messager.alert("提示", obj.errMsg);
                			}
                		}
            		});
            	} else if(selects.length < 1) {
            		$.messager.alert('提示', '<br>请选中您要还原的记录!', 'info');
            	}else if(selects.length > 1) {
            		$.messager.alert('提示', '<br>修改，只能选择一条记录!', 'info');
            	}
            }
        </script>
    </body>
</html>