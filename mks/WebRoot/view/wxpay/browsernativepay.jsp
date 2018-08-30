<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>一码游湖北</title>
 <meta name="viewport"
	content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<link rel="stylesheet" href="${path}/library/weixin/css/mui.min.css">
<link rel="stylesheet" type="text/css" href="${path}/library/weixin/css/app.css" />
<script type="text/javascript" src="${path}/library/weixin/js/jquery.1.11.1.js"></script>
    
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
            margin-top: .2rem;
            margin-right: 34%;
        }
        .right1{
            font-size: .4rem;
        }
        .right2{
            font-size: .24rem;
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
              
           <img alt="" src="${path}/weixinpay/createQrcode.do?url=${url}">
            </div>
            <div class="span">
                   微信扫码,向我付款
            </div>
            <div class="bt">
                <div class="item">
                    <img src="${path}/public/weixin/zf/1.png" alt=""/>
                </div>
                <div class="item">
                    <img src="${path}/public/weixin/zf/2.png" alt=""/>
                </div>
                <div class="item">
                    <img src="${path}/public/weixin/zf/3.png" alt=""/>
                </div>

            </div>
        </div>

</body>
</html>