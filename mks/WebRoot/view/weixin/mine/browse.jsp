<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>浏览记录</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link href="${path }/library/weixin/css/mui.min.css" rel="stylesheet" />
        <link href="${path }/library/weixin/css/app.css" rel="stylesheet" />
   
	</head>
	<body onLoad="loaded()" class="sclb">
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">浏览记录</h1>
		</header>
		<div class="mui-content pdb45 splb-list">
            <ul class="mui-table-view pro_list">
                <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                    <a href="spxq.html">
                        <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb1.png">
                        <div class="mui-media-body">
                            <p class="mui-ellipsis splb-tit">山东栖霞精品红富士1kg</p>
                            <p class="mui-ellipsis splb-sm">果径80-85mm 酸甜爽口</p>
                            <div class="spjg">
                            	<div class="mui-text-left pro_list-price"><span class="rmb">￥</span><span class="pro_list-price-red">15.9</span></div>
                                <button class="mui-text-right tjgwc"><img src="${path }/library/weixin/images/tjgwc.png"></button>
                            </div>
                        
                        </div>
                    </a>
                </li>
                  <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                    <a href="spxq.html">
                        <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb2.png">
                        <div class="mui-media-body">
                            <p class="mui-ellipsis splb-tit">鲁花5s一级花生油5.436L</p>
                            <p class="mui-ellipsis splb-sm">赠送900ml 食用油  健康</p>
                             <div class="spjg">
                            	<div class="mui-text-left pro_list-price"><span class="rmb">￥</span><span class="pro_list-price-red">15.9</span></div>
                                <button class="mui-text-right tjgwc"><img src="${path }/library/weixin/images/tjgwc.png"></button>
                            </div>
                        </div>
                    </a>
                </li>
                 <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                    <a href="spxq.html">
                        <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb1.png">
                        <div class="mui-media-body">
                            <p class="mui-ellipsis splb-tit">山东栖霞精品红富士1kg</p>
                            <p class="mui-ellipsis splb-sm">果径80-85mm 酸甜爽口</p>
                            <div class="spjg">
                            	<div class="mui-text-left pro_list-price"><span class="rmb">￥</span><span class="pro_list-price-red">15.9</span></div>
                                <button class="mui-text-right tjgwc"><img src="${path }/library/weixin/images/tjgwc.png"></button>
                            </div>
                        
                        </div>
                    </a>
                </li>
                  <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                    <a href="spxq.html">
                        <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb2.png">
                        <div class="mui-media-body">
                            <p class="mui-ellipsis splb-tit">鲁花5s一级花生油5.436L</p>
                            <p class="mui-ellipsis splb-sm">赠送900ml 食用油  健康</p>
                             <div class="spjg">
                            	<div class="mui-text-left pro_list-price"><span class="rmb">￥</span><span class="pro_list-price-red">15.9</span></div>
                                <button class="mui-text-right tjgwc"><img src="${path }/library/weixin/images/tjgwc.png"></button>
                            </div>
                        </div>
                    </a>
                </li>
                
                 
            </ul> 
          </div>
			
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
		})
	})
</script>
  <!--js导航链接-->

<%@ include file="../../common/bottom.jsp"%>

</html>