<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<title>一码游湖北</title>
<meta name="viewport"
 content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />
<link rel="stylesheet" href="${path}/library/weixin/css/mui.min.css">
<link rel="stylesheet" type="text/css" href="${path}/library/weixin/css/app.css" />
<script type="text/javascript" src="${path}/library/weixin/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
$(function(){
	  pay();
}) 
	function onBridgeReady(){
		   WeixinJSBridge.invoke(
		       'getBrandWCPayRequest', {
		           "appId": $("#appId").val(),     //公众号名称，由商户传入     
		           "timeStamp":$("#timeStamp").val(),         //时间戳，自1970年以来的秒数     
		           "nonceStr": $("#nonceStr").val(), //随机串     
		           "package": $("#perpayid").val(),     
		           "signType": "MD5",         //微信签名方式：     
		           "paySign" : $("#paySign").val() //微信签名 
		       },
		       function(res){
		    	  
		           if(res.err_msg == "get_brand_wcpay_request:ok" ) {
		        	  
		           }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。 
		       }
		   ); 
		}
	 function pay(){		   
		if (typeof WeixinJSBridge == "undefined"){
		   if( document.addEventListener ){
		       document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		   }else if (document.attachEvent){
		       document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
		       document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		   }
		}else{
		   onBridgeReady();
		}
	}
	 window.setInterval(showalert, 3000); 
	 function showalert(){
		 var userid=$("#userid").val();
		 var openid=$("#openid").val();
	  $.ajax({
			type : "POST",
			url : "${path}/weixin/card/ordersuc.do",
			dataType : "text",
			data : {				 
				orderno : '${orderNum}',				 
			},
			success : function(data) {
				 var obj = eval('(' + data + ')');
				 if(obj.code==0){
					 if(obj.stats==2){
						 if(obj.paystate==1){
							 var url="${path}/weixin/home/home.do?userid="+userid+"&openid="+openid;
							     url = encodeURI(encodeURI(url));
				 	    	     window.location.href = url;
						 }
						 if(obj.paystate==2){
							 var url="${path}/weixin/wechatuser/list.do?userid="+userid+"&openid="+openid; 
						     url = encodeURI(encodeURI(url));
			 	    	     window.location.href = url;
						 }
						
					 }
				 }
			}
		});
	}
	 
</script>
 <script>
        !function (e) {
            function t(a) {
                if (i[a])return i[a].exports;
                var n = i[a] = {exports: {}, id: a, loaded: !1};
                return e[a].call(n.exports, n, n.exports, t), n.loaded = !0, n.exports
            }
            var i = {};
            return t.m = e, t.c = i, t.p = "", t(0)
        }([function (e, t) {
            "use strict";
            Object.defineProperty(t, "__esModule", {value: !0});
            var i = window;
            t["default"] = i.flex = function (normal, e, t) {
                var a = e || 100, n = t || 1, r = i.document, o = navigator.userAgent, d = o.match(/Android[\S\s]+AppleWebkit\/(\d{3})/i), l = o.match(/U3\/((\d+|\.){5,})/i), c = l && parseInt(l[1].split(".").join(""), 10) >= 80, p = navigator.appVersion.match(/(iphone|ipad|ipod)/gi), s = i.devicePixelRatio || 1;
                p || d && d[1] > 534 || c || (s = 1);
                var u = normal ? 1 : 1 / s, m = r.querySelector('meta[name="viewport"]');
                m || (m = r.createElement("meta"), m.setAttribute("name", "viewport"), r.head.appendChild(m)), m.setAttribute("content", "width=device-width,user-scalable=no,initial-scale=" + u + ",maximum-scale=" + u + ",minimum-scale=" + u), r.documentElement.style.fontSize = normal ? "50px" : a / 2 * s * n + "px"
            }, e.exports = t["default"]
        }]);
        flex(false, 100, 1);
    </script>
    <style>
        *{
            margin: 0;
            padding: 0;
        }
        .top{
            width: 100%;
            height: 1.32rem;

            background-color: #fff;
        }
        .test{
            width: .87rem;
            height: .87rem;
            float: left;
            margin-left: 33.5%;
            margin-top: .2rem;
        }
        .test img{
            width: 100%;
            height: 100%;
            border: 0;
            display: block;
        }
        .right{
            width: 1rem;
            height: .87rem;
           float: right;
            margin-top: .35rem;
            margin-right: 34%;
        }
        .right1{
            font-size: .4rem;
        }
        .right2{
            font-size: .24rem;
            margin-top:20px
        }
        .con{
            width: 100%;
            background-color: #00a642;
            padding-top: .2rem;
        }
        .name{
            width:80%;
            height: .8rem;
            margin: 0 auto;
            background-color: #ffffff;
            color: #00a642;
            border-radius: .22rem;
            font-size: .4rem;
            text-align: center;
            line-height: .8rem;
            font-weight: 500;
        }
        .ma{
            width: 80%;
            height: 6rem;
            margin:  .4rem auto;
            border: 9px solid #fff;
            border-radius: .5rem;
        }
        .ma img{
            width: 80%;
            height: 80%;
            margin: 10% 10%;
        }
        .span{
            width: 100%;
            text-align: center;
            font-size: .48rem;
            color: #ffffff;
        }
        .bt{
            width: 80%;
            margin: .2rem auto;
            height: 1.28rem;
            border-top: 5px dotted #ffffff;
            display: flex;
        }
        .bt .item{
            flex: 1;
        }
        .item img{
            display: block;
            width: 45%;
            margin: .3rem auto;
        }
    </style>
</head>

<body style="background-color: #00a642">
<input type="hidden" id="appId" name="" value="${appId}">
<input type="hidden" id="timeStamp" name="" value="${timeStamp}">
<input type="hidden" id="nonceStr" name="" value="${nonceStr}">
<input type="hidden" id="perpayid" name="" value="${perpayid}">
<input type="hidden" id="paySign" name="" value="${paySign}">
<input type="hidden" id="userid" name="" value="${userid}">
<input type="hidden" id="openid" name="" value="${openid}">
 <!-- <div style="background-color: #e9ecf1" class="mui-content">


		<ul class="mui-table-view" style="margin-top: 5%">

			<li class="mui-table-view-cell ">
				<div>
					<span style="color: #999"></span><span
						style="width: 50%; text-align: right; float: right;">
												</span>
				</div>
				<div>
					 
				</div>
			</li>

		</ul>
	</div> -->
	  <div class="top">
            <div class="test">
                <img src="${path}/library/weixin/zf/wec.png" />
            </div>
            <div class="right">
                <div class="right1">微信</div>
                <div class="right2">Wechat</div>
            </div>
        </div>
        <div class="con">
            <div class="name">
                湖北一码游科技有限公司
            </div>
            <div class="ma">
              
           <img alt="" src="${path}/library/weixin/zf/icon.png">
            </div>
            <div class="span">
              
            </div>           
        </div>
</body>
</html>