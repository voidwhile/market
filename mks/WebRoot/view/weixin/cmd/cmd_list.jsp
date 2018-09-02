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
.mui-table-view .mui-media-object{
height:16%
}
</style>

</head>
<body  class="sclb">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">商品列表</h1>
		</header>
		<nav class="fl-search-list fl-search-2">
        	<div class="fl-search-list-con">
                 <div class="fl-search-list-address fl-search-list-small">
                       
                        <input class="fl-search-list-input" type="text" placeholder="请输入您想要的食材">
                         <a href="ss.html"><img src="images/search_ico.png"/></a>
                    </div>
            </div>
		</nav>
		<div class="mui-content pdb45 splb-list splb-xj">
        	<div class="line-hui"></div>
            <div class="mui-clearfix">
            <ul class="mui-table-view pro_list">
            	<c:forEach items="${cmdList }" var="cmd">
                <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                    <a href="${path }/wx/cmd/detail.wx?cmdId=${cmd.cmdId}&memberId=${memberId}">
                        <img class="mui-media-object mui-pull-left" src="${imgUrl }${cmd.imgPath}">
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
                                <button class="mui-text-right tjgwc" onclick="addCart('${cmd.cmdId}')"><img src="${path }/library/weixin/images/tjgwc.png"></button>
                            </div>
                        
                        </div>
                    </a>
                </li>
            	</c:forEach>
                  
                
                 
            </ul> 
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
<%@ include file="../../common/bottom.jsp" %>
</html>