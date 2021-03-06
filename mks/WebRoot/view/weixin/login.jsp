<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/taglib.jsp" %>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>登录</title>
		<link href="${path}/library/weixin/css/mui.min.css" rel="stylesheet" />
        <link href="${path}/library/weixin/css/app.css" rel="stylesheet" />
        <script src="${path}/library/weixin/js/jquery.min.js"></script>
        <script src="${path}/library/weixin/js/mui.min.js"></script>
	</head>

	<body>
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">登录</h1>
		</header>
		<div class="mui-content zc">
            <form class="zc-form">
              <div class="mui-input-row mui-content-padded border-bottom">
                   <img src="${path}/library/weixin/images/zc_tel.png">
                   <input class="zc-input" type="number" placeholder="用户名/手机号">
               </div>
               <div class="mui-input-row mui-content-padded border-bottom">
                   <img src="${path}/library/weixin/images/zc_mm.png">
                   <input class="zc-input" type="password" placeholder="密码">
               </div>
               <p class="login-wj-text"><a href="wjmm.html">忘记密码?</a></p>
            </form>
            <a href="index.html" class="sall-btn">登录</a>
             <a href="zc.html" class="zc-btn">注册</a>
		</div>
	</body>

</html>