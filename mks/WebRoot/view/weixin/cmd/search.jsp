<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>    
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<title>向悦万家</title>
		<link rel="stylesheet" type="text/css" href="${path}/library/weixin/css/mui.min.css" />
		<link rel="stylesheet" type="text/css" href="${path}/library/weixin/css/app.css" />
	</head>

	<body>
    	<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">搜索</h1>
		</header>
		<div class="mui-content ss">
 		<nav class="search-list">
                <div class="search-list-con">
                    <div class="search-list-address search-list-small">
                        <img src="${path }/library/weixin/images/search_ico.png" style="margin-top: 7px;"/>
                        <input id="cmdName" class="search-list-input" type="text" placeholder="请输入您想要的商品">
                    </div>
                </div>
                <div class="search-list-small-cancel">
                    <a href="javascript:search();">搜索</a>
                </div>
            </nav>
            <div class="hot-list clear">
            	<div class="hot-tit">热门搜索</div>
                <div class="hot-content">
                	<ul class="hot-ul">
                    	<li><a href="${path }/wx/cmd/list.wx?ccCmdType=46&memberId=${memberId}">鸡蛋</a></li>
                        <li><a href="${path }/wx/cmd/list.wx?ccCmdType=46&memberId=${memberId}">蔬菜</a></li>
                        <li><a href="${path }/wx/cmd/list.wx?ccCmdType=46&memberId=${memberId}">青菜</a></li>
                    </ul>
                </div>
            
            </div>
        
        </div>
       
	</body>
<script src="${path}/library/weixin/js/mui.min.js"></script>
<script src="${path}/library/weixin/js/jquery-1.11.1.js"></script>
<script src="${path}/library/weixin/js/iscroll.js"></script>
<script type="text/javascript">
	function search(){
		window.location.href=path+"/wx/cmd/list.wx??memberId=${memberId}&cmdName="+$("#cmdName").val();
	}
</script>
</html>