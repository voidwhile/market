<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>我的</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${path }/library/weixin/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${path }/library/weixin/css/app.css"/>
	</head>
	<body>
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">个人中心</h1>
		</header>
		<div class="mui-content mine">
        	<nav class="mine_grzx">
				<div class="mui-content-padded">
                    <div style=" float:left; width:60%;">
                    	<img  class="mine-tx"  src="${member.portrait }">
                       <div class="fl mine_nicheng">
                      	 <p class="mine-tit">${member.realName }</p>
                       </div> 
                    </div>
                    <a class="bjzl-btn" href="${path }/wx/mbe/info.wx?memberId=${memberId}"><img class="bj-icon" src="${path }/library/weixin/images/mine_bj.png"/>编辑资料<span class="mui-icon mui-icon-arrowright"></span></a>
             	</div>
		</nav>
			<ul class="mui-table-view">
              <li class="mui-table-view-cell">
                <a href="${path }/wx/mbe/order.wx?memberId=${memberId}" class="mui-navigate-right mine-ddtit">
                  我的订单<span class="mine-more">查看更多订单</span>
                </a>
              </li>
            </ul>  
            <div class="mine-shzt">
            	<div class="mine-shzt-item">
                    <a href="mine_wddd.html">
                    	<span class="mine-shzt-ico1"></span>
                        待付款
                    </a>
                </div>
                <div class="mine-shzt-item">
                    <a href="mine_wddd.html">
                    	<span class="mine-shzt-ico2"></span>
                       待发货
                    </a>
                </div>
                <div class="mine-shzt-item">
                    <a href="mine_wddd.html">
                    	<span class="mine-shzt-ico3"></span>
                        待收货
                    </a>
                </div>
                <div class="mine-shzt-item">
                    <a href="mine_wddd.html">
                    <span class="mine-shzt-ico4"></span>
                        已收货
                    </a>
                </div>
                 <div class="mine-shzt-item">
                    <a href="mine_wddd_yth.html">
                    <span class="mine-shzt-ico4"></span>
                        退货
                    </a>
                </div>
            </div>
        	
             <div class=" line-hui"></div>
             <ul class="mui-table-view">
              
            
              <li class="mui-table-view-cell">
                <a href="${path }/wx/mbe/address.wx?memberId=${memberId}" class="mui-navigate-right">
                <img  class="mine_img" src="${path }/library/weixin/images/map.png">
                  我的地址
                </a>
              </li>
              <li class="mui-table-view-cell">
                <a href="${path }/wx/mbe/feedback.wx?memberId=${memberId}" class="mui-navigate-right">
                <img  class="mine_img" src="${path }/library/weixin/images/mine_wyfk_icon.png">
                  我要反馈
                </a>
              </li>
              <li class="mui-table-view-cell">
                <a href="tel:17796651957" class="mui-navigate-right">
                <img  class="mine_img" src="${path }/library/weixin/images/mine_lxkf_icon.png">
                  联系客服
                </a>
              </li>
              <li class="mui-table-view-cell">
                <a class="mui-navigate-right">
                <img  class="mine_img" src="${path }/library/weixin/images/mine_tc_icon.png">
                  退出登录
                </a>
              </li>
            </ul>
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
            <a id="mine" class="mui-tab-item mui-active">
                <span class="mui-icon nav-list-my-icon"></span>
                <span class="mui-tab-label">我的</span>
            </a>
        </nav>
	</body>
	<script src="${path }/library/weixin/js/jquery-1.11.1.js"></script>
	<script src="${path }/library/weixin/js/mui.min.js"></script>
<script type="text/javascript" src="${path }/library/weixin/js/iscroll.js"></script>
<script type="text/javascript" src="${path }/library/weixin/js/demo.js"></script>
<!-- 左右滑动 -->
<script type="text/javascript">
var myScroll;
function loaded () {
	myScroll = new IScroll('#wrapper', { eventPassthrough: true, scrollX: true, scrollY: false, preventDefault: false });
}
</script>
<script>
	$(function(){
		$(".top-nav li").click(function(){
			$(this).addClass("active").siblings("li").removeClass("active");
		})
	})
</script>
  <!--js导航链接-->
  <%@ include file="../../common/bottom.jsp"%>
</html>