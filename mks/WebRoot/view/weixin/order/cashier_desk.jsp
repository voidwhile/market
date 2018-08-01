<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>收银台</title>
		<link href="${path }/library/css/mui.min.css" rel="stylesheet" />
        <link href="${path }/library/css/app.css" rel="stylesheet" />
          <style>
		  .picker .picker-panel {
			position:relative;
			z-index: 600;
			top:0% !important;
			bottom:0;
			left:0;
			right:0;
			/*margin-top:-122px;*/
			width: 100%;
			height: 243px;
			margin:auto;
			background: #fff;
			transform: translateY(243px);
			-webkit-transform: translateY(243px);
			transition: all .5s;
			-webkit-transition: all .5s;
		}
		.picker .picker-panel .picker-choose .confirm {
			right: 0;
			color:#666 !important;
		}
		</style>
	</head>
	<body>
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">收银台</h1>
		</header>
    	<div class="mui-content tjdd">
            <div class="mui-card ljzf-list" style="margin:0px">
                <form class="mui-input-group" id="form-pay">
                	<input type="hidden" name="orderId" value="${order.orderId }">
                	<input type="hidden" name="browser" >
                    <div class="mui-input-row mui-radio">                                
                        <label><img src="${path }/library/weixin/images/wx.png">微信支付</label>
                        <input name="payType" type="radio" value="1">
                    </div>
                    <div class="mui-input-row mui-radio">
                        <label><img src="${path }/library/weixin/images/zfb.png">支付宝支付</label>
                        <input name="payType" type="radio" value="2">
                    </div>
                    
                </form>
                <div class="buy-jjr-zx">
                     <div class="buy-jjr-zx-left">
                        <a class="buy-jjr-list">
                            <img id="photo" class="buy-jjr-img" src="${path }/library/weixin/images/sc_img.png">
                            <div class="buy-jjr-text">合计：<span class="color_red">￥0</span></div>
                        </a>
                    </div>
                    <div class="buy-jjr-zx-right">
                        <a href="javascript:pay()">
                       		支付
                        </a>
                    </div>
                </div>   
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
            <a id="changeGwc" class="mui-tab-item  mui-active">
                <span class="mui-icon nav-list-cart-icon"></span>
                <span class="mui-tab-label">购物车</span>
            </a>
            <a id="mine" class="mui-tab-item">
                <span class="mui-icon nav-list-my-icon"></span>
                <span class="mui-tab-label">我的</span>
            </a>
        </nav>
	</body>
	<script src="${path }/library/js/jquery-1.11.1.js"></script>
	<script src="${path }/library/js/mui.min.js"></script>
	<script type="text/javascript" src="${path }/library/js/iscroll.js"></script>
    <script type="text/javascript" src="${path }/library/js/demo.js"></script>
    <!-- 左右滑动 -->
    <script>
        $(function(){
            $(".top-nav li").click(function(){
                $(this).addClass("active").siblings("li").removeClass("active");
            })
        })
    </script>
      <!--js导航链接-->
    <script src="${path }/library/js/picker.min.js"></script>
	<script>
		function pay(){
			var ua = navigator.userAgent.toLowerCase();
			if(ua.match(/MicroMessenger/i)=="micromessenger") {
				$("#browser").val(1);
		 	} else {
		 		$("#browser").val(2);
			}
		}	
	</script>
</body>
</html>