<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登陆</title>
<link href="${pageContext.request.contextPath }/library/css/login.css" rel="stylesheet" rev="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="${pageContext.request.contextPath }/library/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/library/js/jquery.SuperSlide.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/library/js/Validform_v5.3.2_min.js"></script>
<script type="text/javascript">
if(self.frameElement != null && (self.frameElement.tagName == "IFRAME" || self.frameElement.tagName == "iframe")){
    parent.parent.location = "${pageContext.request.contextPath}/login.do";
}
</script>
</head>
<body>
<div class="header">
  	<%-- <h1 class="headerLogo"><a title="后台管理系统" target="_blank" href="#"><img alt="logo" src="${pageContext.request.contextPath}/library/images/logo.png"></a></h1> --%>
	<!-- <div class="headerNav">
		<a target="_blank" href="#">首页</a>&nbsp;|&nbsp;
		<a target="_blank" href="#">帮助中心</a>&nbsp;|&nbsp;
		<a target="_blank" href="#">意见反馈</a>&nbsp;|&nbsp;
		<span>服务热线：<b>400-622-1082</b></span>
	</div> -->
</div>
<div class="banner">
	<div class="login-aside">
	  	<div id="o-box-up"></div>
	  	<div id="o-box-down"  style="table-layout:fixed;">
	   	<form class="loginform" action="${pageContext.request.contextPath}/login.do" method="post">
	  	
		  	<div class="fm-item">
			   <label for="logonId" class="form-label">用户名：<span style="float: right;padding-right: 80px;" onclick="register()">注册</span></label>
			   <input type="text" value="admin" maxlength="50" id="username" name="username" class="i-text" datatype="s4-18" nullmsg="请输入用户名！" errormsg="用户名至少4个字符,最多18个字符！"  >    
		  	</div>
		  	<div class="fm-item">
			   <label for="logonId" class="form-label">密码：</label>
			   <input type="password" value="123456" maxlength="50" name="password" id="password" class="i-text" datatype="*6-16" nullmsg="请输入密码！" errormsg="密码范围在6~16位之间！">    
		  	</div>
		 <!--  	<div class="fm-item pos-r">
			   <label for="logonId" class="form-label">验证码</label>
			   <input type="text" value="" maxlength="10" id="captcha" name="captcha" class="i-text yzm" datatype="*" nullmsg="请输入验证码！" >    
		       <div class="ui-form-explain"><img src="${pageContext.request.contextPath}/captcha.svl" onclick="this.src='${pageContext.request.contextPath}/captcha.svl?d='+new Date()*1" class="yzm-img"/></div>
		  	</div> -->
		  	<div class="fm-item">
			   <label for="logonId" class="form-label"></label>
			   <input type="submit" value="" tabindex="4" id="send-btn" class="btn-login"> 
		       <div class="ui-form-explain"></div>
		  	</div>
	  		</form>
		   	<div class="error-box" style="float: left;">
				<c:if test="${!empty error}">
				${error}
				</c:if>
		   	</div>
		   	<div>
				<span style="float: right;padding:5px 80px 0px 0px;color: #fff;font-size: 14px" onclick="forget()">忘记密码？</span>
		   	</div>
	  	</div>
	</div>
	<div class="bd">
		<ul>
			<li style="background:url(${pageContext.request.contextPath}/library/theme-pic1.jpg) #157bd7 center 0 no-repeat;"></li>
		</ul>
	</div>
	<div class="hd"><ul></ul></div>
</div>
<script type="text/javascript">jQuery(".banner").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",  autoPlay:true, autoPage:true, trigger:"click" });</script>
<div class="banner-shadow"></div>
<div class="footer">
	<p class="footer_nav"><a target="_blank" href="#">关于</a> | 客户端&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="#">iphone</a> <a target="_blank" href="#">android</a>
   	<p>Copyright &copy; 2018.Windawn.Inc All Rights Reserved. 云动科技 版权所有</p>
</div>
<script type="text/javascript">
$(function(){
	$(".i-text").focus(function(){
		$(this).addClass('h-light');
	});

	$(".i-text").focusout(function(){
		$(this).removeClass('h-light');
	});
	
	
	$(".loginform").Validform({
		tiptype:function(msg,o,cssctl){
			var objtip=$(".error-box");
			if(o.type=="2"){
				msg="";
			}
			cssctl(objtip,o.type);
			objtip.text(msg);
		},
		ajaxPost:false
	});
});
function register() {
	alert("注册")
}
function forget() {
	alert("忘记密码")
}
</script>
</body>
</html>