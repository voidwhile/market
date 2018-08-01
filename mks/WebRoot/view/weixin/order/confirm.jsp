<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>  

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<title>一码游湖北</title>
		<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1,user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<link rel="stylesheet" href="${path}/weixin/css/mui.min.css">
		<link rel="stylesheet" type="text/css" href="${path}/weixin/css/app.css"/>
        <meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />
        <script type="text/javascript" src="${path}/weixin/js/jquery-1.10.2.min.js"></script>
	<script type="text/javascript">
	 $(function(){
		 
		$("form").submit()
	})
 
	</script>
	</head>
    
    <body style="background-color:#e9ecf1">
    <div style="background-color:#e9ecf1" class="mui-content">
	<header class="mui-bar mui-bar-nav" style="height:10%">
  <p class="mui-title" style="">旅游</p></br>
  <h1 class="mui-title" style="font-size:30px;padding-top:1%;"><span>￥${price}</span></h1>
</header>

 <div style="background-color:#e9ecf1" class="mui-content">
<ul class="mui-table-view" style="margin-top:5%">
	
	<li class="mui-table-view-cell ">
    <div><span style="color:#999">收款方</span><span style="width:50%;text-align:right; display:block;float:right;">旅游</span></div>
    <div><span style="color:#999">商品</span><span style="width:50%;text-align:right; display:block;float:right;color:#666;">【旅游-联票】${money}X ${num }</span></div>
    </li>
   
</ul>
</div>
<c:if test="${browser==0 }"><form method="post" action="${path}/weixinpay/browser_index.do"></c:if>
<c:if test="${browser==1 }"><form method="post" action="${path}/weixinpay/index.do"></c:if>
<input type="hidden" value="${openid }" name="openid" >
<input type="hidden" value="${orderNum }" name="orderNum" >
<input type="hidden" value="${fee}" name="fee" >
<input type="hidden" value="${lvy }" name="title" > 
<input type="hidden" value="${num }" name="num" >
<input type="hidden" value="${userid }" name="userid" >
<input type="hidden" value="${browser }" name="browser" >
</form>
 <button class="mui-btn mui-btn-primary mui-btn-block"  style="width:80%;margin:0 auto;margin-top:10%; background-color:#5dd2b7;border-color:#5dd2b7">正在进入支付...</button>
        </div>
	</body>
</html>