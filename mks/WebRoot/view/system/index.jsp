<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>大手掌管理系统</title>
<%@ include file="../common/IncludeHead.jsp" %>
<link rel="stylesheet" type="text/css" href="${path}/library/css/dpl-min.css" />
<link rel="stylesheet" type="text/css" href="${path}/library/css/bui-min.css" />
<link rel="stylesheet" type="text/css" href="${path}/library/css/scroll.css" />
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css" />

<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">  
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">
	var _updateAction="${path}/admin/user/indexEdit.do";
	var _updatepwdAction="${path}/admin/user/index_password_edit.do";
  	var _updateTitle="修改用户信息";
  	var _updatePwdTitle="修改用户密码";
  	var _dialogWidth=500;
  	var _dialogHeight=300;
	$(function(){
		  showlist();
		  $('.all-nav .item').hover(function () {
			$(this).addClass('active').find('s').hide();
			$(this).find('.product-wrap').show();
		  }, function () {
			$(this).removeClass('active').find('s').show();
			$(this).find('.product-wrap').hide();
		  });
		  //设置关闭按钮
	});
  	function showlist(){
		$(".click_list").mouseover(function(){
			$(".listbox").show();
		});
		$(".click_list").mouseout(function(){
			$(".listbox").hide();
		});
  	}
 	//修改资料
   	function edit(){
   		openEditorDialog(_updateTitle, _dialogWidth, _dialogHeight, _updateAction+"?uid=${sessionAdmin.user.uid}");
   	}
  	//修改密码
  	function updatepwd(){
  		openEditorDialog(_updatePwdTitle, 500, 220, _updatepwdAction);
  	}
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
   	            	closeDialog();
   	            }
   	        }]
   		});
   	}
	//关闭
   	function closeDialog(){
   	    $('#openDialogDiv').dialog("close");
   	 	window.location.reload(true);
   	}
</script>
<style type="text/css">
	.bui-select-list{
		width:120px;
	}
</style>
</head>

<body>
<!-- top -->
<div class="header">
	<div class="topleft">
		<a style="font-size:18px;" href="#">大手掌</a>
	</div>
	<div class="hnav">
		<div class="topright">    
	    	<ul>
				<li class="click_list" >用户名：${sessionAdmin.user.realName}
				<div class="listbox">
					<div class="setup-arr">
						<span></span>
					</div>
					<ul id="list1"  class="bui-select-list">
			        <li><a onclick="edit()" >账号设置</a></li>
			        <li><a onclick="updatepwd()">密码修改</a></li>
			      </ul>
				</div>
				
				</li>
				<li>企业名称：${sessionAdmin.supplier.supplierName}</li>
	    		<li><a href="${path}/logout.do" target="_parent">退出</a></li>
	    	</ul>    
		</div>
	</div>
	<div id="openDialogDiv" title="My Dialog"  class="easyui-window" title="My Window" closed="true" style="width:600px;height:480px;padding:5px;"></div>

 	<%-- <div id="win" class="easyui-window" title="修改密码" style="padding:5px 10px;" data-options="modal:true"> 
 		<form id="editForm" class="easyui-form" method="post" data-options="novalidate:true">
 			<input type="hidden" name="uid" value="${sessionAdmin.user.uid}"/>
			<table class="tableBox" border="0" width="100%" cellpadding="0" cellspacing="0">
				<tr>
					<th width="100" align="right">原始密码:</th>
					<td><input id="oldpwd" class="easyui-validatebox textbox" type="password" name="oldpwd" style="width:150px" missingMessage="原始密码不能为空！" data-options="required:true" validType= "length[6,1000]" invalidMessage="密码最小长度为6！"/></td>
				</tr>
				<tr>
					<th width="100" align="right">新密码:</th>
					<td><input id="newpwd" class="easyui-validatebox textbox" type="password" name="newpwd" style="width:150px" missingMessage="新密码不能为空！" data-options="required:true" validType= "length[6,1000]" invalidMessage="密码最小长度为6！"/></td>
				</tr>
				<tr>
					<th width="100" align="right">确定新密码:</th>
					<td><input id="rnewpwd" class="easyui-validatebox textbox" type="password" name="rnewpwd" style="width:150px" required="required" validType="equals['#newpwd']"/></td>
				</tr>
			</table>
	
			<div style="padding:5px;text-align:right;">
				<a href="javascript:void(0)" onclick="subForm()" class="easyui-linkbutton" iconCls="icon-ok" >保存</a>
				<a href="javascript:void(0)" onclick="cancelForm()" class="easyui-linkbutton" iconCls="icon-cancel" >取消</a>
			</div>
		</form>
	</div> --%>
	<div class="clear"></div>
</div>
<!-- top end -->
<div id="poupcover" class="overlay"></div>
<!-- left menu begin -->
<div class="nav">
	<div class="all-nav">
	<!--左侧导航开始-->
	<c:if test="${sessionAdmin.menuList ne null}">
		<c:forEach items="${sessionAdmin.menuList}" var="menu" varStatus="status">
		<c:if test="${menu.childs ne null}">
		<div class="item">
          	<div class="product">
            	<h3><a class="icn ${menu.menuIcon}"><span>${menu.menuName }</span></a></h3>
          	</div>
          	<div class="product-wrap pos01" style="display: none; left: 66px!important;">
          		<h2>${menu.menuName }</h2>
          		<div class="gdt mCustomScrollbar _mCS_1" style="height:90%">
          			<div class="mCustomScrollBox mCS-light" id="mCSB_${status.index}" style="position:relative; height:100%; overflow:hidden; max-width:100%;">
          				<div class="mCSB_container mCS_no_scrollbar" style="position: relative; top: 0px;">
          					<ul>
          						<c:forEach items="${menu.childs}" var="child">
				                <li>
				                	<a href="#" data-href="${path}/${child.menuUrl }" title="${child.menuName }" data-id="menu_${child.menuCode}"><span class="ui-all ${child.menuIcon}"></span><span>${child.menuName }</span></a>
				                </li>
				                </c:forEach>
				       		</ul>
          				</div>
          				<div class="mCSB_scrollTools" style="position: absolute; display: none;">
							<div class="mCSB_draggerContainer">
								<div class="mCSB_dragger" style="position: absolute; top: 0px;" oncontextmenu="return false;">
									<div class="mCSB_dragger_bar" style="position:relative;"></div>
								</div>
								<div class="mCSB_draggerRail"></div>
							</div>
						</div>
          			</div>
          		</div>
          	</div>
        </div>
        </c:if>
        </c:forEach>
		</c:if>
		<!--左侧导航结束-->
      </div>
    </div>
<!-- left menu end -->
<!-- middle begin -->
<div class="mainhome">
	<div class="inner">
		<div id="tab"></div>
	</div>
</div>
<!-- middle end -->
<div class="clear"></div> 

<script type="text/javascript" src="${path}/library/js/bui-min.js"></script>
<script type="text/javascript" src="${path}/library/js/sea.js"></script>
<script type="text/javascript" src="${path}/library/js/config-min.js" data="true"></script>
<script type="text/javascript">
	BUI.use('bui/tab',function(Tab){
		var tab = new Tab.NavTab({
          	render:'#tab',
          	height:$(window).height()-$("#tab").offset().top,
          	children : [{
              	title : '首页',
              	href : '${path}/admin/main.do',
              	closeable:false,
              	actived:true
            }]
		});
		tab.render();
		$('.mCS_no_scrollbar li a').on('click',function(){
          	var config = {
			  	id:$(this).attr('data-id'),
              	title : $(this).attr('title'),
              	href : $(this).attr('data-href')
			};
          	tab.addTab(config);
        });
	});
</script>
</body>
</html>