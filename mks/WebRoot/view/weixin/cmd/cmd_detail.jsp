<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<title>管理系统</title>
<%@ include file="../../common/IncludeHead.jsp"%>
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${path}/library/easyui/themes/icon.css">  
<link rel="stylesheet" type="text/css" href="${path}/library/css/style.css">
<link rel="stylesheet" type="text/css" href="${path}/library/css/form.css">            
<link rel="stylesheet" type="text/css" href="${path}/library/css/mui.min.css">            
<link rel="stylesheet" type="text/css" href="${path}/library/css/app.css">            
<script type="text/javascript" src="${path}/library/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/library/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${path}/view/js/common_list.js"></script>
<script type="text/javascript" src="${path}/library/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="${path}/library/ueditor/ueditor.all.js"></script>
<script src="${path}/library/js/jquery-1.11.1.js"></script>
<script src="${path}/library/js/mui.min.js"></script>
<script type="text/javascript" src="${path}/library/js/iscroll.js"></script>
<script type="text/javascript">
window.onload = function() {
	var img1=document.getElementById('photo2');
	img1.onclick = function() {
		if (this.src.search("images/sc_img.png") != -1) {
			this.src = "images/sc_img_after.png";
		} else {
			this.src = "images/sc_img.png";
		}
	}
}
</script>
</head>
<body onLoad="loaded()">
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">商品详情</h1>
		</header>
		<div class="mui-content pdb60">
			<!-- 轮播图 -->
			<div class="mui-slider">
			  <div class="mui-slider-group mui-slider-loop">
			    <!-- 额外增加的一个节点(循环轮播：第一个节点是最后一张轮播) -->
			    <div class="mui-slider-item mui-slider-item-duplicate">
			    	<a href="#">
			    		<img src="${path}/library/images/banner_xq.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
			    	<a href="#">
			    		<img src="${path}/library/images/banner_xq.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
			    	<a href="#">
			    		<img src="${path}/library/images/banner_xq.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
			    	<a href="#">
			    		<img src="${path}/library/images/banner_xq.png" />
			    	</a>
			    </div>
			    <div class="mui-slider-item">
					<a href="#">
			    		<img src="${path}/library/images/banner_xq.png" />
			    	</a>
			    </div>
			    <!-- 额外增加的一个节点(循环轮播：最后一个节点是第一张轮播) -->
			    <div class="mui-slider-item mui-slider-item-duplicate">
			    	<a href="#">
			    		<img src="${path}/library/images/banner_xq.png" />
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
            <div class="spxq-list">
            	<p class="spxq-tit">山东绿宝石香瓜 新鲜水果甜瓜 当季现摘蜜瓜 甜瓜绿宝香瓜包邮</p>
                <div class="pro_list-price">
                	<span class="rmb">￥</span><span class="pro_list-price-red">15.9</span>
                    <div class="mui-numbox"  data-numbox-min="0">
                        <button class="mui-btn mui-btn-numbox-minus" type="button">-</button>
                        <input class="mui-input-numbox" type="number">
                        <button class="mui-btn mui-btn-numbox-plus" type="button">+</button>
                    </div>
                
                </div>
                <div class="mui-inline">
                	<span class="spxq-yj">原价39.8</span><span class="spxq-xl">库存1000&nbsp;&nbsp;&nbsp;&nbsp;已售598</span>
                </div>
            </div>
            <div class="line-hui"></div>
            <div class="spxq-jj">
            	<div class="mui-inline">
                    <div class="mui-pull-left spxq-jj-xx">商品品牌：**品牌</div>
                    <div class="mui-pull-right spxq-jj-xx">商品分类：蔬菜类>根茎类</div>
                </div>
                <div class="mui-inline">
                    <div class="mui-pull-left spxq-jj-xx">产       地：河南郑州</div>
                    <div class="mui-pull-right spxq-jj-xx">保存条件：常温</div>
                </div>
            </div>
                <img class="spxq-jj-img" src="${path}/library/images/spxq1.png"/>
                <img class="spxq-jj-img" src="${path}/library/images/spxq2.png"/>
                <img class="spxq-jj-img" src="${path}/library/images/spxq3.png"/>
           </div> 
           <div class="buy-jjr-zx">
        	 <div class="buy-jjr-zx-left">
                <a class="buy-jjr-list">
                    <img id="photo2" class="buy-jjr-img" src="${path}/library/images/sc_img.png">
                    <div class="buy-jjr-text">收藏</div>
                </a>
            </div>
            <div class="buy-jjr-zx-right">
                <a href="gwc.html">
                加入购物车
                </a>
            </div>
        </div>   
	</body>
</html>