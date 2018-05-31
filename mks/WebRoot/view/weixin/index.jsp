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
<body>
		<nav class="search-list index-con">
        	<div  style="padding-left:10px">
            	
                 <div class="search-list-address search-list-small">
                        <a href="ss.html"><img src="${path }/library/weixin/images/search_ico.png" style="margin-top: 7px;"/>
                        <input class="search-list-input" type="text" placeholder="请输入您想要的食材"></a>
                    </div>
            </div>
		</nav>
		<div class="mui-content">
			<!-- 轮播图 -->
			<div class="mui-slider">
			  <div class="mui-slider-group mui-slider-loop">
			    <c:if test="${!empty banner}">
			    <div class="mui-slider-item mui-slider-item-duplicate">
			    	<a href="${l.url }">
			    		<img src="${imgUrl }${l.imgPath}" />
			    	</a>
			    </div>
			    <c:forEach items="${ banner}" var="b">
			    <div class="mui-slider-item">
			    	<a href="${b.url }">
			    		<img src="${imgUrl }${b.imgPath}" />
			    	</a>
			    </div>
			    </c:forEach>
			    
			    <c:set var="f" value="${banner[0] }" />
			    <div class="mui-slider-item mui-slider-item-duplicate">
			    	<a href="${f.url }">
			    		<img src="${imgUrl }${f.imgPath}" />
			    	</a>
			    </div>
			    </c:if>
			    
			    
			  </div>
			  
			</div>
            <!--轮播结束-->
            <!--商品分类开始-->
            <div id="Gallery" style="height:201px">
                <div class="mui-slider-group spfl" style="height:100px">
                    <div class="mui-slider-item">
                        <ul class="mui-table-view mui-grid-view mui-grid-9">
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/scl_ico.png"/>
                                    <span class="mui-media-body">
                                        	蔬菜类
                                    </span>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/sg_ico.png"/>
                                    <span class="mui-media-body">
                                        	水果类
                                    </span>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/mmly_ico.png"/>
                                    <span class="mui-media-body">
                                        	米面粮油
                                    </span>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/jg_ico.png"/>
                                    <span class="mui-media-body">
                                        	菌菇类
                                    </span>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/xr_ico.png"/>
                                    <span class="mui-media-body">
                                        	鲜肉类
                                    </span>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/st_ico.png"/>
                                    <span class="mui-media-body">
                                       	 副食类
                                    </span>
                                </a>
                            </li>
                               <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/jp_ico.png"/>
                                    <span class="mui-media-body">
                                        	精品区
                                    </span>
                                </a>
                            </li>
                            <li class="mui-table-view-cell mui-media mui-col-xs-3">
                                <a href="fl.html">
                                    <img src="${path }/library/weixin/images/qbfl_ico.png"/>
                                    <span class="mui-media-body">
                                        	全部分类
                                    </span>
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
            	<c:forEach items="${adAdverts }" var="ad">
            	<a href="${ad.url }">
                	<img src="${imgUrl }${ad.imgPath}" />
                </a>
            	</c:forEach>
            </div>
            <!--广告位结束-->
            <div class="line-hui"></div>
       	 </div>     
		
        <!--今日特价-新品推荐开始-->
		<div class="jrtj-list" style="background:url(${imgUrl }${runSale.cmdImg}) bottom right no-repeat;background-size:90px auto;background-position: 95% 92%;" onclick="showCmd('${runSale.rsCmdId}')">
        	<p class="jrtj-tit"><span class="jrtj-ico"></span>今日特价</p>
            <p class="jrtj-mc">${runSale.cmdName }</p>
            <p class="jrtj-mc-aside">${runSale.intro }</p>
            <p class="jrtj-jg">￥${runSale.salePrice }</p>
        </div>
        <div class="xptj-list" style="background:url(${imgUrl }${runRecommend.cmdImg}) bottom right no-repeat;background-size:90px auto;background-position: 95% 92%;" onclick="showCmd('${runRecommend.rrCmdId}')">
        	<p class="jrtj-tit"><span class="xptj-ico"></span>新品推荐</p>
            <p class="jrtj-mc">${runRecommend.cmdName }</p>
            <p class="jrtj-mc-aside">${runRecommend.intro }</p>
            <p class="jrtj-jg">￥${runRecommend.price }</p>
        </div>
        <!--今日特价-新品推荐结束-->
        <div class="line-hui clear"></div>
        <!--特价商品开始-->
        <div class="tjsp-tit-list">特价商品</div>
        <div class="tjsp-list pdb80">
        	<c:forEach items="${sales }" var="s">
        	  <a class="tjsp-list-item" href="javascript:showCmd('${s.rsCmdId }')">
                <img class="" src="${imgUrl }${s.cmdImg}">
                 <span class="xs-tit">${s.cmdName }</span>
                 <span class="xs-jg-list">
                    <span class="mui-pull-left xs-jg">￥<span>${s.salePrice }</span></span>
                    <span class="mui-pull-right xs-scx del-line">原价：￥${s.price }</span>
                 </span>
             </a>
        	</c:forEach>
             
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
<script src="${path}/library/weixin/js/mui.min.js"></script>
<script src="${path}/library/weixin/js/jquery-1.11.1.js"></script>
<script src="${path}/library/weixin/js/iscroll.js"></script>
<script type="text/javascript">

//获得slider插件对象(自动轮播)
$(function(){
	var gallery = mui('.mui-slider');
	gallery.slider({
	  interval:5000//自动轮播周期，若为0则不自动播放，默认为0；
	});

})
function showCmd(cmdId){
	location.href=path+"/wx/cmd/detail.wx?cmdId="+cmdId;
}
</script>
<!--js导航链接-->
<%@ include file="../common/bottom.jsp" %>
</html>