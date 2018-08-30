<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">	
<title>大手掌</title>
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/mui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/app.css" />


</head>
<body class="fl">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">分类</h1>
		</header>
		
		<div class="mui-content mui-row mui-fullscreen fl-list">
			<div class="mui-col-xs-3">
				<div id="segmentedControls" class="mui-segmented-control mui-segmented-control-inverted mui-segmented-control-vertical">
					<c:forEach items="${typeList }" var="t">
					<a href="#content_${t.cmdType }" class="mui-control-item">${t.typeName }</a>
					</c:forEach>
				</div>
			</div>
			<div id="segmentedControlContents" class="mui-col-xs-9">
				<c:forEach items="${typeList }" var="t">
				<div id="content_${t.cmdType }" class="mui-control-content mui-active">
					<ul class="fenlei-nav">
						<c:forEach items="${t.children }" var="tc">
						<li class="mui-col-xs-4 mui-col-sm-4 mui-pull-left">
							<a href="${path }/wx/cmd/list.wx?cmdType=${tc.cmdType}&memberId=${memberId}">
								<img src="${imgUrl }${tc.imgPath}" alt="" style="width:140px;height:70px">
								<h4 class="mui-text-center j-tit  mui-ellipsis">${tc.typeName }</h4>
							</a>
						</li>
						</c:forEach>
                       
 					</ul>
				</div>
				</c:forEach>
			</div>
		</div>
		
       <nav class="mui-bar mui-bar-tab nav-list">
              <a id="changeIndex" class="mui-tab-item">
                    <span class="mui-icon nav-list-index-icon"></span>
                    <span class="mui-tab-label">首页</span>
            </a>
            <a id="changeFl" class="mui-tab-item mui-active">
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
            <a id="mine" class="mui-tab-item">
                <span class="mui-icon nav-list-my-icon"></span>
                <span class="mui-tab-label">我的</span>
            </a>
        </nav>
	</body>

<script src="${path}/library/weixin/js/mui.min.js"></script>
<script src="${path}/library/weixin/js/jquery-1.11.1.js"></script>
<script src="${path}/library/weixin/js/iscroll.js"></script>
<script>
			mui.init({
				swipeBack: false //启用右滑关闭功能
			});
	$(function(){
		$(".top-nav li").click(function(){
			$(this).addClass("active").siblings("li").removeClass("active");
		})
	})
</script>
  <!--js导航链接-->
<%@ include file="../common/bottom.jsp" %>
</html>