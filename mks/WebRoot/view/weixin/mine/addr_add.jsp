<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>向悦万家</title>
		<link href="${path }/library/weixin/css/mui.min.css" rel="stylesheet" />
        <link href="${path }/library/weixin/css/app.css" rel="stylesheet" />
  
	</head>
	<body class="grzl">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">编辑地址</h1>
		</header>
		<div class="mui-content">
			<form id="form-addr" action="${path }/wx/mbe/addAddr.wx" method="post">
				<input type="hidden" id="addr-id" name="addrId" value="${addr.addrId }">
				<input type="hidden" id="memberId" name="memberId" value="${memberId }">
	          <ul class="mui-table-view bg_white grzx-list mt0">         
	               <li class="mui-table-view-cell">
	                   <label class="grzl-label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
	                   <input class="yjzz-input" name="title" value="${addr.title }">
	              </li>
	               <li class="mui-table-view-cell">
	                   <label class="grzl-label">收&nbsp;件&nbsp;人</label>
	                   <input class="yjzz-input" name="realName" value="${addr.realName }">
	              </li>
	               <li class="mui-table-view-cell">
	                   <label class="grzl-label">手&nbsp;机&nbsp;号</label>
	                   <input class="yjzz-input" name="mp" value="${addr.mp }"/>
	              </li>
	              <li class="mui-table-view-cell">
	                  <label class="grzl-label">详细地址</label>
	                  <input class="yjzz-input" name="address" value="${addr.address }">
	              </li>
	              <li class="mui-table-view-cell">
	                  <label class="grzl-label">是否默认</label>
	                  <input type="checkbox" name="isDefault" <c:if test="${addr.isDefault==true }">checked</c:if> >
	              </li>
	              
	            </ul>
			</form>
            <div class="line-hui"></div>
            <a class="save-btn" id="btn-save" href="#">保存</a>
		</div>
	</body>
	<script src="${path }/library/weixin/js/jquery.min.js"></script>
    <script src="${path }/library/weixin/js/mui.min.js"></script>
    <script type="text/javascript">
    $(function(){
    	$("#btn-save").bind("click",function(){
    		$("#form-addr").submit();
    	});
    })
    </script>

</html>