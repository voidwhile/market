<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>向悦万家</title>
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/mui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/app.css" />
<style type="text/css">
.mui-table-view .mui-media-object {
	height: 16%
}
</style>

</head>
<body  class="sclb">
	<header class="mui-bar mui-bar-nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
		<h1 class="mui-title">收藏</h1>
	</header>
	<div class="mui-content pdb45 splb-list">
		<ul class="mui-table-view pro_list">
		<c:forEach items="${cmdList }" var="cmd">
			<li class="mui-table-view-cell mui-media bg_white pro_list-item">
				<a href="${path }/wx/cmd/detail.wx?cmdId=${cmd.cmdId}&memberId=${memberId}"> 
				<img class="mui-media-object mui-pull-left"
					src="${imgUrl }${cmd.imgPath}">
					<div class="mui-media-body">
						<p class="mui-ellipsis splb-tit">${cmd.cmdName }</p>
						<div class="spjg">
	                            	<div class="mui-text-left pro_list-price">
	                            	<span class="rmb">￥</span>
	                            	<span class="pro_list-price-red">
	                            	<c:if test="${ empty cmd.eventPrice }">
	                            	${cmd.price }
	                            	</c:if>
	                            	<c:if test="${ !empty cmd.eventPrice }">
	                            	${cmd.eventPrice }
	                            	</c:if>
	                            	</span>
	                            	</div>
	                            	<div class="mui-inline">
					                	<span class="spxq-yj">
					                	<c:if test="${ !empty cmd.eventPrice }">
					                	原价${cmd.price }
					                	</c:if>
					                	</span>
					                	
					                </div>
	                            </div>

					</div>
				</a> 
				<a class="mui-text-right tjgwc" href="javascript:addCart('${cmd.cmdId}')"><img
					src="${path }/library/weixin/images/tjgwc.png"></a>
			</li>
		</c:forEach>
		</ul>
	</div>

	<nav class="mui-bar mui-bar-tab nav-list">
		<a id="changeIndex" class="mui-tab-item"> <span
			class="mui-icon nav-list-index-icon"></span> <span
			class="mui-tab-label">首页</span>
		</a> <a id="changeFl" class="mui-tab-item"> <span
			class="mui-icon nav-list-fl-icon"></span> <span class="mui-tab-label">分类</span>
		</a> <a id="changeSc" class="mui-tab-item  mui-active"> <span
			class="mui-icon nav-list-sc-icon"></span> <span class="mui-tab-label">收藏</span>
		</a> <a id="changeGwc" class="mui-tab-item"> <span
			class="mui-icon nav-list-cart-icon"></span> <span
			class="mui-tab-label">购物车</span>
		</a> <a id="mine" class="mui-tab-item"> <span
			class="mui-icon nav-list-my-icon"></span> <span class="mui-tab-label">我的</span>
		</a>
	</nav>
</body>

<script src="${path}/library/weixin/js/mui.min.js"></script>
<script src="${path}/library/weixin/js/jquery-1.11.1.js"></script>
<script src="${path}/library/weixin/js/iscroll.js"></script>
<script type="text/javascript">
function addCart(cmdId){
	$.ajax({
		url:"${path }/wx/cart/save.wx",
		type:"post",
		data:{num:1,ocCmdId:cmdId,ocMemberId:"${memberId}"},
		dataType:"json",
		success:function(d){
			mui.toast('成功加入购物车！');
		}
	});
}
</script>
<%@ include file="../../common/bottom.jsp"%>
</html>