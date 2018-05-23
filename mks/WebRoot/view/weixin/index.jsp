<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<title>大手掌</title>
	<link href="${path}/library/weixin/css/mui.min.css" rel="stylesheet"/>
	<link rel="stylesheet" type="text/css" href="${path}/library/weixin/css/app.css"/>
	<link rel="stylesheet" href="${path}/library/weixin/css/style.css" />
	<link rel="stylesheet" href="${path}/library/weixin/css/tabs.css" />
	<link rel="stylesheet" href="${path}/library/weixin/css/swiper.min.css" />
	<link rel="stylesheet" href="${path}/library/weixin/css/iconfont.css" />  
	<link rel="stylesheet" href="${path}/library/weixin/css/slide.css" />  
	<script type="text/javascript" src="${path}/library/weixin/js/idangerous.swiper.min.js" ></script>
	<script type="text/javascript" src="${path}/library/weixin/js/iconfont.js" ></script>
	<script src="${path}/library/weixin/js/mui.min.js"></script>
	<script src="${path}/library/weixin/js/jquery-1.11.1.js"></script>
	<script src="${path}/library/weixin/js/slide.js"></script>
	<script type="text/javascript" src="${path}/library/weixin/js/demo.js"></script>
	<script type="text/javascript" src="${path}/library/weixin/js/iscroll.js"></script>
	<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.1.0.js"></script>
	 
	<style type="text/css">
		.mui-slider .mui-slider-group .mui-slider-item {height: auto !important;}
		.m-tabs {height: auto;}
		.m-news-activity {padding: 0;}
		.pd65{padding-bottom:65px;}
		.m-kind ul li img{width:58%;margin:0 21%;}
	</style>
</head>
<body onLoad="loaded()">
		<nav class="search-list index-con">
        	<div class="search-list-con" style="padding-left:10px">
            	<div class="search-list-city">
                	<a href="csxz.html">
                    	郑州<span class="mui-icon mui-icon-arrowdown"></span>
                     </a>
                </div>
                 <div class="search-list-address search-list-small">
                        <a href="ss.html"><img src="${path }/library/weixin/images/search_ico.png"/>
                        <input class="search-list-input" type="text" placeholder="请输入您想要的食材"></a>
                    </div>
            </div>
		</nav>
		<div class="mui-content">
			<!-- 轮播图 -->
			<div class="mui-slider">
			  <div class="mui-slider-group mui-slider-loop">
			    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
			    <div class="mui-slider-item mui-slider-item-duplicate">
			    	<a href="#">
			    		<img src="${path }/library/weixin/images/banner.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
			    	<a href="#">
			    		<img src="${path }/library/weixin/images/banner.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
			    	<a href="#">
			    		<img src="${path }/library/weixin/images/banner.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
			    	<a href="#">
			    		<img src="${path }/library/weixin/images/banner.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
					<a href="#">
			    		<img src="${path }/library/weixin/images/banner.png" />
			    	</a>
			    </div>
			    <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
			    <div class="mui-slider-item mui-slider-item-duplicate">
			    	<a href="#">
			    		<img src="${path }/library/weixin/images/banner.png" />
			    	</a>
			    </div>
			  </div>
			  <div class="mui-slider-indicator mui-text-center">
					<div class="mui-indicator mui-active"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
					<div class="mui-indicator"></div>
				</div>
			</div>
            <!--轮播结束-->
            <!--商品分类开始-->
            <div id="Gallery">
                <div class="mui-slider-group spfl">
                    <div class="mui-slider-item">
                        <ul class="mui-table-view mui-grid-view mui-grid-9">
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/scl_ico.png"/>
                                    <div class="mui-media-body">
                                        蔬菜类
                                    </div>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/sg_ico.png"/>
                                    <div class="mui-media-body">
                                        水果类
                                    </div>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/mmly_ico.png"/>
                                    <div class="mui-media-body">
                                        米面粮油
                                    </div>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/jg_ico.png"/>
                                    <div class="mui-media-body">
                                        菌菇类
                                    </div>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/xr_ico.png"/>
                                    <div class="mui-media-body">
                                        鲜肉类
                                    </div>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/st_ico.png"/>
                                    <div class="mui-media-body">
                                        食堂类
                                    </div>
                                </a>
                            </li>
                               <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/jp_ico.png"/>
                                    <div class="mui-media-body">
                                        精品区
                                    </div>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/qbfl_ico.png"/>
                                    <div class="mui-media-body">
                                        全部分类
                                    </div>
                                </a>
                            </li>
                        
                        </ul>
                    </div>
                </div>
            </div>
            <!--商品分类结束-->
            <div class="line-hui"></div>
            <!--广告位开始-->
            <div class="ad-list">
            	<a href="fl_splb_ggw.html">
                	<img src="${path }/library/weixin/images/ad.png"/>
                </a>
            </div>
            <!--广告位结束-->
            <div class="line-hui"></div>
       	 </div>     
		</div>
        <!--今日特价-新品推荐开始-->
		<div class="jrtj-list">
        	<p class="jrtj-tit"><span class="jrtj-ico"></span>今日特价</p>
            <p class="jrtj-mc">小台农芒500g</p>
            <p class="jrtj-mc-aside">果汁香浓</p>
            <p class="jrtj-jg">￥8.5</p>
        </div>
        <div class="xptj-list">
        	<p class="jrtj-tit"><span class="xptj-ico"></span>新品推荐</p>
            <p class="jrtj-mc">佳沛绿奇异果6个</p>
            <p class="jrtj-mc-aside">酸甜爽口</p>
            <p class="jrtj-jg">￥19.9</p>
        </div>
        <!--今日特价-新品推荐结束-->
        <div class="line-hui clear"></div>
        <!--特价商品开始-->
        <div class="tjsp-tit-list">特价商品</div>
        <div class="tjsp-list pdb80">
        	  <a class="tjsp-list-item" href="spxq.html">
                <img class="" src="${path }/library/weixin/images/tjsp.png">
                 <p class="xs-tit">鲁花5S一级花生油5.436L 赠送900ml 食用油 健康</p>
                 <div class="xs-jg-list">
                    <span class="mui-pull-left xs-jg">￥<span>169.9</span></span>
                    <span class="mui-pull-right xs-scx del-line">原价：￥199.9</span>
                 </div>
             </a>
             <a class="tjsp-list-item"  href="spxq.html">
                <img class="" src="${path }/library/weixin/images/tjsp2.png">
                 <p class="xs-tit">山东栖霞精品红富士1kg果径80-85mm 苹果</p>
                 <div class="xs-jg-list">
                    <span class="mui-pull-left xs-jg">￥<span>169.9</span></span>
                    <span class="mui-pull-right xs-scx del-line">原价：￥199.9</span>
                 </div>
             </a>
               <a class="tjsp-list-item"  href="spxq.html">
                <img class="" src="${path }/library/weixin/images/tjsp.png">
                 <p class="xs-tit">鲁花5S一级花生油5.436L 赠送900ml 食用油 健康</p>
                 <div class="xs-jg-list">
                    <span class="mui-pull-left xs-jg">￥<span>169.9</span></span>
                    <span class="mui-pull-right xs-scx del-line">原价：￥199.9</span>
                 </div>
             </a>
               <a class="tjsp-list-item"  href="spxq.html">
                <img class="" src="${path }/library/weixin/images/tjsp2.png">
                 <p class="xs-tit">山东栖霞精品红富士1kg果径80-85mm 苹果</p>
                 <div class="xs-jg-list">
                    <span class="mui-pull-left xs-jg">￥<span>169.9</span></span>
                    <span class="mui-pull-right xs-scx del-line">原价：￥199.9</span>
                 </div>
             </a>
        </div>
               

	<!--特价商品结束-->
        <nav class="mui-bar mui-bar-tab nav-list">
          <a id="changeIndex" class="mui-tab-item mui-active" href="#tabbar">
                <span class="mui-icon nav-list-index-icon"></span>
                <span class="mui-tab-label">首页</span>
            </a>
            <a id="changeFl" class="mui-tab-item" href="#tabbar-with-chat">
                <span class="mui-icon nav-list-fl-icon"></span>
                <span class="mui-tab-label">分类</span>
            </a>
            <a id="changeSc" class="mui-tab-item" href="#tabbar-with-sms">
                <span class="mui-icon nav-list-sc-icon"></span>
                <span class="mui-tab-label">收藏</span>
            </a>
            <a id="changeGwc" class="mui-tab-item" href="#tabbar-with-sms">
                <span class="mui-icon nav-list-cart-icon"></span>
                <span class="mui-tab-label">购物车</span>
            </a>
            <a id="mine" class="mui-tab-item" href="#tabbar-with-map">
                <span class="mui-icon nav-list-my-icon"></span>
                <span class="mui-tab-label">我的</span>
            </a>
        </nav>
	</body>
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

</html>