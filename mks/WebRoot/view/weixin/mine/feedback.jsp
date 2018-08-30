<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>我要反馈</title>
		<link href="${path }/library/weixin/css/mui.min.css" rel="stylesheet" />
        <link href="${path }/library/weixin/css/app.css" rel="stylesheet" />
        <script src="${path }/library/weixin/js/jquery.min.js"></script>
        <script src="${path }/library/weixin/js/mui.min.js"></script>
	</head>
	<body class="sccg_bg">
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">我要反馈</h1>
		</header>
		<div class="mui-content">
           <textarea class="fk-wby" rows="15" cols="1" placeholder="请输入您想要反馈的内容"></textarea>
           <a href="mine_sccg.html" class="fk-btn">提交</a>
		</div>
	</body>
</html>