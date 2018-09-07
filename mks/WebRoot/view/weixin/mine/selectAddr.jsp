<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>我的地址</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="${path }/library/weixin/css/mui.min.css" rel="stylesheet" />
        <link href="${path }/library/weixin/css/app.css" rel="stylesheet" />
   
	</head>
	<body class="sccg_bg grzl">
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">我的地址</h1>
			<a class="mui-icon mui-icon-right-nav mui-pull-right" id="a-select">完成</a>
		</header>
		<div class="mui-content">
			<form id="form-select" action="${path }/wx/order/book.wx" method="post">
				<input type="hidden" name="memberId" value="${memberId }">
	            <ul class="mui-table-view pro_list">
	            	<c:forEach items="${addrList }" var="addr">
	                <li class="mui-table-view-cell mui-media bg_white pro_list-item">
		                <div class="mui-media-body">
		                    <p class="mui-ellipsis splb-tit">${addr.title }&nbsp;&nbsp;${addr.realName }&nbsp;&nbsp;<c:if test="${addr.isDefault==true }">默认</c:if> </p>
		                    <p class="mui-ellipsis splb-tit">${addr.mp }</p>
		                    <p class="mui-ellipsis splb-sm">${addr.address }</p>
		                    <div class="spjg" style="right:15px;top:15px;position: absolute;">
	                            	<input type="radio" name="addrId" value="${addr.addrId }">
	                        </div>
		                </div>
	                </li>
	            	</c:forEach>
	                
	                
	            </ul>
			</form>
            <a class="save-btn" href="${path }/wx/mbe/toAddr.wx?memberId=${memberId}">添加地址</a> 
        </div>
			   
        <nav class="mui-bar mui-bar-tab nav-list">
              <a id="changeIndex" class="mui-tab-item">
                    <span class="mui-icon nav-list-index-icon"></span>
                    <span class="mui-tab-label">首页</span>
            </a>
            <a id="changeFl" class="mui-tab-item">
                <span class="mui-icon nav-list-fl-icon"></span>
                <span class="mui-tab-label">分类</span>
            </a>
            <a id="changeSc" class="mui-tab-item">
                <span class="mui-icon nav-list-sc-icon"></span>
                <span class="mui-tab-label">收藏</span>
            </a>
            <a id="changeGwc" class="mui-tab-item">
                <span class="mui-icon nav-list-cart-icon"></span>
                <span class="mui-tab-label">购物车</span>
            </a>
            <a id="mine" class="mui-tab-item  mui-active">
                <span class="mui-icon nav-list-my-icon"></span>
                <span class="mui-tab-label">我的</span>
            </a>
        </nav>
	</body>
	<script src="${path }/library/weixin/js/jquery.min.js"></script>
        <script src="${path }/library/weixin/js/mui.min.js"></script>
<script type="text/javascript" src="js/iscroll.js"></script>
<script type="text/javascript" src="js/demo.js"></script>

<script>
	$(function(){
		$(".top-nav li").click(function(){
			$(this).addClass("active").siblings("li").removeClass("active");
		});
		$("#a-select").click(function(){
			$("#form-select").submit();
		});
	})
</script>
  <!--js导航链接-->

<%@ include file="../../common/bottom.jsp"%>

</html>