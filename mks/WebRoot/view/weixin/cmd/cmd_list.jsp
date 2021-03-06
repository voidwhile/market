<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>向悦万家</title>
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/mui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${path}/library/weixin/css/app.css" />
<style type="text/css">
.mui-table-view .mui-media-object{
height:16%
}
</style>

</head>
<body  class="sclb">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">商品列表</h1>
		</header>
		<nav class="fl-search-list fl-search-2">
        	<div class="fl-search-list-con">
                 <div class="fl-search-list-address fl-search-list-small">
                       
                        <a href="${path }/wx/cmd/toSearch.wx?memberId=${memberId}"><img src="${path}/library/weixin/images/search_ico.png"/>
                        <input class="fl-search-list-input" type="text" placeholder="请输入您想要的商品">
                        </a>
                    </div>
            </div>
		</nav>
		<div class="mui-content pdb45 splb-list splb-xj">
        	<div class="line-hui"></div>
            <div class="mui-clearfix">
            	<div id="pullrefresh" class="mui-scroll-wrapper" style="margin-top: 123px;">
    				<div class="mui-scroll">
			            <ul id="ul-cmd-list" class="mui-table-view">
			            	<c:forEach items="${cmdList }" var="cmd">
			                <li class="mui-table-view-cell ">
			                    <a href="${path }/wx/cmd/detail.wx?cmdId=${cmd.cmdId}&memberId=${memberId}">
			                        <img class="mui-media-object mui-pull-left" src="${imgUrl }${cmd.imgPath}">
			                        <div class="mui-media-body">
			                            <p class="mui-ellipsis splb-tit">${cmd.cmdName }</p>
			                            
			                            <div class="spjg">
			                            	<div class="mui-text-left pro_list-price">
			                            	<span class="rmb">￥</span>
			                            	<span class="pro_list-price-red">
			                            	<c:if test="${ empty cmd.eventPrice }">
			                            	${cmd.price }
			                            	</c:if>
			                            	<c:if test="${ !empty cmd.eventPrice }">
			                            	${cmd.eventPrice }
			                            	</c:if>
			                            	</span>
			                            	</div>
			                            	<div class="mui-inline">
							                	<span class="spxq-yj">
							                	<c:if test="${ !empty cmd.eventPrice }">
							                	原价${cmd.price }
							                	</c:if>
							                	</span>
							                	
							                </div>
			                                
			                            </div>
			                        
			                        </div>
			                    </a>
			                    <button class="mui-text-right tjgwc" onclick="addCart('${cmd.cmdId}')"><img src="${path }/library/weixin/images/tjgwc.png"></button>
			                </li>
			            	</c:forEach>
			            </ul>
            		</div>
            	</div> 
          </div>
			
		</div>
        <nav class="mui-bar mui-bar-tab nav-list">
              <a id="changeIndex" class="mui-tab-item">
                    <span class="mui-icon nav-list-index-icon"></span>
                    <span class="mui-tab-label">首页</span>
            </a>
            <a id="changeFl" class="mui-tab-item mui-active">
                <span class="mui-icon nav-list-fl-icon"></span>
                <span class="mui-tab-label">分类</span>
            </a>
            <a id="changeSc" class="mui-tab-item">
                <span class="mui-icon nav-list-sc-icon"></span>
                <span class="mui-tab-label">收藏</span>
            </a>
            <a id="changeGwc" class="mui-tab-item">
                <span class="mui-icon nav-list-cart-icon"></span>
                <span class="mui-tab-label">购物车</span>
            </a>
            <a id="mine" class="mui-tab-item">
                <span class="mui-icon nav-list-my-icon"></span>
                <span class="mui-tab-label">我的</span>
            </a>
        </nav>
	</body>

<script src="${path}/library/weixin/js/mui.min.js"></script>
<script src="${path}/library/weixin/js/jquery-1.11.1.js"></script>
<script type="text/javascript">
var page = 1;
$(function(){
	mui.init({ 
		 pullRefresh : {
		　　 swipeBack: false, //关闭左滑关闭功能
		    container:"#pullrefresh",//下拉刷新容器标识，querySelector能定位的css选择器均可，比如：id、.class等
		    down : {
		      style:'circle',//必选，下拉刷新样式，目前支持原生5+ ‘circle’ 样式
		      color:'#2BD009', //可选，默认“#2BD009” 下拉刷新控件颜色
		      height:'50px',//可选,默认50px.下拉刷新控件的高度,
		      range:'100px', //可选 默认100px,控件可下拉拖拽的范围
		      offset:'0px', //可选 默认0px,下拉刷新控件的起始位置
		      auto: false,//可选,默认false.首次加载自动上拉刷新一次
		      callback :pulldownRefresh //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
		   },
		　　up:{
		　　　　contentrefresh: '正在加载...',
		　　　　contentnomore:'没有更多数据了',
		　　　　callback:pulluploading //上拉加载下一页
		　　}
		}
	});
	$("#pullrefresh").on('tap','a',function(event){
		this.click();
	});
	$("#pullrefresh").find('button').on('tap',function(event){
		this.click();
	});
})
function pulluploading(){
	$.ajax({
		url:path+"/wx/cmd/pulluploading.wx",
		type:"post",
		dataType:"json",
		data:{ccCmdType:'${label}',cmdName:'${cmdName}',page:page+1},
		success:function(data){
			if(data.rltCode=="0000"){
				var cmdList = data.cmdList;
				if(cmdList.length>0){
					page=page+1;
					for(var i=0;i<cmdList.length;i++){
						var cmd = cmdList[i];
						var price = 0;
						var orgPrice;
						if(cmd.eventPrice){
							price = cmd.eventPrice;
							orgPrice = '原价'+cmd.price;
						} else {
							price = cmd.price;
							orgPrice = ''
						}
						var cmdhtml = '\
							<li class="mui-table-view-cell ">\
		                    <a href="${path }/wx/cmd/detail.wx?cmdId='+cmd.cmdId+'&memberId=${memberId}">\
		                        <img class="mui-media-object mui-pull-left" src="${imgUrl }'+cmd.imgPath+'">\
		                        <div class="mui-media-body">\
		                            <p class="mui-ellipsis splb-tit">'+cmd.cmdName +'</p>\
		                            <div class="spjg">\
		                            	<div class="mui-text-left pro_list-price">\
		                            	<span class="rmb">￥</span>\
		                            	<span class="pro_list-price-red">'+price+'</span>\
		                            	</div>\
		                            	<div class="mui-inline">\
						                	<span class="spxq-yj">'+orgPrice+'</span>\
						                </div>\
		                            </div>\
		                        </div>\
		                    </a>\
		                    <button class="mui-text-right tjgwc" onclick="addCart('+cmd.cmdId+')"><img src="${path }/library/weixin/images/tjgwc.png"></button>\
		                </li>\
						';
		                $("#ul-cmd-list").append(cmdhtml);
					}
				}
			}
		}
		
	});
}
function pulldownRefresh(){
	window.location.href=path+"/wx/cmd/list.wx?memberId=${memberId}&cmdName=${cmdName}&ccCmdType=${label}";
}
function addCart(cmdId){
	$.ajax({
		url:"${path }/wx/cart/save.wx",
		type:"post",
		data:{num:1,ocCmdId:cmdId,ocMemberId:"${memberId}"},
		dataType:"json",
		success:function(d){
			mui.toast('成功加入购物车！');
		}
	});
}
</script>
<%@ include file="../../common/bottom.jsp" %>
</html>