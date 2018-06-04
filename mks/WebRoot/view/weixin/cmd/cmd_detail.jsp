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
<title>大手掌</title>
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/mui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/app.css" />
<style type="text/css">

</style>

</head>
<body >
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">商品详情</h1>
		</header>
		<div class="mui-content pdb60">
			<!-- 轮播图 -->
			<div class="mui-slider">
			  <div class="mui-slider-group">
			    <c:forEach items="${cmd.images}" var="i">
			    <div class="mui-slider-item">
		    		<img src="${imgUrl }${i.path}" />
			    </div>
			    </c:forEach>
			  </div>
			</div>
            <!--轮播结束-->
            <div class="spxq-list">
            	<p class="spxq-tit">${cmd.cmdName }</p>
                <div class="pro_list-price">
                	<span class="rmb">￥</span><span class="pro_list-price-red">
                	<c:if test="${ !empty cmd.eventPrice }">
                	${cmd.eventPrice }
                	</c:if>
                	<c:if test="${ empty cmd.eventPrice }">
                	${cmd.price }
                	</c:if>
                	</span>
                    <div class="mui-numbox"  data-numbox-min="0">
                        <button class="mui-btn mui-btn-numbox-minus" type="button">-</button>
                        <input class="mui-input-numbox" type="number" id="num" name="num">
                        <button class="mui-btn mui-btn-numbox-plus" type="button">+</button>
                    </div>
                
                </div>
                <div class="mui-inline">
                	<span class="spxq-yj">
                	<c:if test="${ !empty cmd.eventPrice }">
                	原价${cmd.price }
                	</c:if>
                	</span>
                	
                </div>
            </div>
            <div class="line-hui"></div>
            <div class="spxq-jj">
            	<div class="mui-inline">
                    <div class="mui-pull-left spxq-jj-xx">商品品牌：${cmd.brand.brandName }</div>
                    <div class="mui-pull-right spxq-jj-xx">商品分类：${cmd.typeName }</div>
                </div>
                <div class="mui-inline">
                    <div class="mui-pull-left spxq-jj-xx">产       地：${cmd.prodPlace }</div>
                    <div class="mui-pull-right spxq-jj-xx">保存条件：${cmd.storageCondition }</div>
                </div>
            </div>
            <div class="line-hui"></div>
                ${cmd.description }
           </div> 
           <div class="buy-jjr-zx">
        	 <div class="buy-jjr-zx-left">
                <a class="buy-jjr-list" href="javascript:addCollect()">
                    <img id="collect" class="buy-jjr-img" src="${path}/library/weixin/images/sc_img.png">
                    <div class="buy-jjr-text">收藏</div>
                </a>
            </div>
            <div class="buy-jjr-zx-right">
                <a href="javascript:addCart()">
               		 加入购物车
                </a>
            </div>
        </div>   
	</body>

<script src="${path}/library/weixin/js/mui.min.js"></script>
<script src="${path}/library/weixin/js/jquery-1.11.1.js"></script>
<script src="${path}/library/weixin/js/iscroll.js"></script>
<script type="text/javascript">
function addCollect(){
	$.ajax({
		url:"${path }/wx/collect/save.wx?cmdId=${cmd.cmdId}&mcMemberId=${memberId}",
		type:"get",
		dataType:"json",
		success:function(d){
			if(d.status){
				mui.toast('收藏成功！');
				$("#collect").attr("src","${path}/library/weixin/images/sc_img_after.png");
			}
		}
	});
}
function addCart(){
	var num = $("#num").val();
	if(num<=0){
		mui.toast('数量有误！');
		return;
	}
	$.ajax({
		url:"${path }/wx/cart/save.wx",
		type:"post",
		data:{num:num,ocCmdId:"${cmd.cmdId}",ocMemberId:"${memberId}"},
		dataType:"json",
		success:function(d){
			mui.toast('成功加入购物车！');
		}
	});
}
</script>
</html>