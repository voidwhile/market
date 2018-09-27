<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>我的订单</title>
		<link href="${path }/library/weixin/css/mui.min.css" rel="stylesheet" />
        <link href="${path }/library/weixin/css/app.css" rel="stylesheet" />
        
	<style>
			.mui-card .mui-control-content {
				padding: 10px;
			}
			.mui-control-content {
				height: 150px;
			}
		</style>
	</head>
	<body>
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">我的订单</h1>
		</header>
		<div class="mui-content wddd">
			<div style="padding: 10px 10px 0px;">
				<div id="segmentedControl" class="mui-segmented-control mui-segmented-control-inverted mui-segmented-control-primary">
					<a class="mui-control-item <c:if test="${item=='item1'}">mui-active</c:if> " href="#item1">
						全部
					</a>
							<a class="mui-control-item <c:if test="${item=='item2'}">mui-active</c:if>" href="#item2">
						待付款
					</a>
							<a class="mui-control-item <c:if test="${item=='item3'}">mui-active</c:if>" href="#item3">
						待发货
					</a>
		            			<a class="mui-control-item <c:if test="${item=='item4'}">mui-active</c:if>" href="#item4">
						待收货
					</a>
							<a class="mui-control-item <c:if test="${item=='item5'}">mui-active</c:if>" href="#item5">
						已完成
					</a>
				</div>
			</div>
			<div>
				<div id="item1" class="mui-control-content <c:if test="${item=='item1'}">mui-active</c:if>">
					<div id="scroll" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<c:forEach items="${orderList }" var="order">
                            <div class="ddbh-list">
                                <p class="ddbh"><img class="ddbh-img" src="${path }/library/weixin/images/mine_dd.png"><span class="ddbh-num">订单编号：${order.orderCode }</span><span class="ddbh-jyzt">交易关闭</span></p>
                                <ul class="mui-table-view pro_list">
                                	<c:forEach items="${order.detailList }" var="detail">
                                    <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                                        <a href="spxq.html">
                                            <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb1.png">
                                            <div class="mui-media-body">
                                                <p class="mui-ellipsis-2 splb-tit mui-pull-left">${detail.cmdName }</p>
                                                <span class="mui-pull-right splb-jg">￥${detail.unitPrice }</span>
                                                <div class="spjg clear">
                                                    500g<span class="mui-pull-right">&times;2</span>
                                                </div>
                                            
                                            </div>
                                        </a>
                                    </li>
                                	</c:forEach>
                                </ul> 
                                <p class="ddbh-hj">共2件商品&nbsp;合计：<span class="ddbh-hj-jg">116.00</span></p>
                            </div>
							</c:forEach>
                            <div class="line-hui"></div>
                            
                        </div>
					</div>
				</div>
				<div id="item2" class="mui-control-content <c:if test="${item=='item2'}">mui-active</c:if>">
					<div id="scroll" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<c:forEach items="${willPayList }" var="order">
							<div class="ddbh-list">
                                <p class="ddbh"><img class="ddbh-img" src="${path }/library/weixin/images/mine_dd.png"><span class="ddbh-num">订单编号：${order.orderCode }</span></p>
                                <ul class="mui-table-view pro_list">
                                    <c:forEach items="${order.detailList }" var="detail">
                                    <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                                        <a href="spxq.html">
                                            <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb1.png">
                                            <div class="mui-media-body">
                                                <p class="mui-ellipsis-2 splb-tit mui-pull-left">${detail.cmdName }</p>
                                                <span class="mui-pull-right splb-jg">￥${detail.unitPrice }</span>
                                                <div class="spjg clear">
                                                    500g<span class="mui-pull-right">&times;2</span>
                                                </div>
                                            
                                            </div>
                                        </a>
                                    </li>
                                	</c:forEach>
                                    
                                </ul> 
                                <p class="ddbh-hj">共2件商品&nbsp;合计：<span class="ddbh-hj-jg">116.00</span></p>
                            </div>
                            <div class="btn-bg">
                               <div class="dd-group">
                                     <a class="qxdd-btn" href="#">取消订单</a><a  class="fk2-btn" href="#">付款</a>
                               </div>
                           </div> 
                		</c:forEach>
                	</div>
                	</div>
				</div>
				<div id="item3" class="mui-control-content <c:if test="${item=='item3'}">mui-active</c:if>">
					<div id="scroll" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<c:forEach items="${willDeliverList }" var="order">
								<div class="ddbh-list">
	                                <p class="ddbh"><img class="ddbh-img" src="${path }/library/weixin/images/mine_dd.png"><span class="ddbh-num">订单编号：${order.orderCode }</span></p>
	                                <ul class="mui-table-view pro_list">
	                                    <c:forEach items="${order.detailList }" var="detail">
	                                    <li class="mui-table-view-cell mui-media bg_white pro_list-item">
	                                        <a href="spxq.html">
	                                            <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb1.png">
	                                            <div class="mui-media-body">
	                                                <p class="mui-ellipsis-2 splb-tit mui-pull-left">${detail.cmdName }</p>
	                                                <span class="mui-pull-right splb-jg">￥${detail.unitPrice }</span>
	                                                <div class="spjg clear">
	                                                    500g<span class="mui-pull-right">&times;2</span>
	                                                </div>
	                                            
	                                            </div>
	                                        </a>
	                                    </li>
	                                	</c:forEach>
	                                </ul> 
	                                <p class="ddbh-hj">共2件商品&nbsp;合计：<span class="ddbh-hj-jg">116.00</span></p>
	                            </div>
	                            <div class="btn-bg">
	                               <div class="dd-group">
	                                     <a class="qxdd-btn" href="#">取消订单</a><a  class="ddgz-btn" href="mine_ddgz.html">订单跟踪</a>
	                               </div>
	                           </div> 
	                           <div class="line-hui"></div>
                           	</c:forEach>
                        </div>
                    </div>
				</div>
                <div id="item4" class="mui-control-content <c:if test="${item=='item4'}">mui-active</c:if>">
                	<div id="scroll" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<c:forEach items="${willDoneList }" var="order">
							<div class="ddbh-list">
                                <p class="ddbh"><img class="ddbh-img" src="${path }/library/weixin/images/mine_dd.png"><span class="ddbh-num">订单编号：${order.orderCode }</span></p>
                                <ul class="mui-table-view pro_list">
                                    <c:forEach items="${order.detailList }" var="detail">
                                    <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                                        <a href="spxq.html">
                                            <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb1.png">
                                            <div class="mui-media-body">
                                                <p class="mui-ellipsis-2 splb-tit mui-pull-left">${detail.cmdName }</p>
                                                <span class="mui-pull-right splb-jg">￥${detail.unitPrice }</span>
                                                <div class="spjg clear">
                                                    500g<span class="mui-pull-right">&times;2</span>
                                                </div>
                                            
                                            </div>
                                        </a>
                                    </li>
                                	</c:forEach>
                                </ul> 
                                <p class="ddbh-hj">共2件商品&nbsp;合计：<span class="ddbh-hj-jg">116.00</span></p>
                            </div>
                            <div class="btn-bg">
                               <div class="dd-group">
                                     <a class="qrsh-btn" href="mine_jycg.html">确认收货</a><a href="mine_ddgz.html" class="qxdd-btn" href="#">订单跟踪</a>
                               </div>
                           </div> 
                           <div class="line-hui"></div>
                         </c:forEach>
                       </div>
                     </div>
				</div>
                <div id="item5" class="mui-control-content <c:if test="${item=='item5'}">mui-active</c:if>">
                	<div id="scroll" class="mui-scroll-wrapper">
						<div class="mui-scroll">
							<c:forEach items="${doneList }" var="order">
					  		<div class="ddbh-list">
                                <p class="ddbh"><img class="ddbh-img" src="${path }/library/weixin/images/mine_dd.png"><span class="ddbh-num">订单编号：${order.orderCode }</span></p>
                                <ul class="mui-table-view pro_list">
                                    <c:forEach items="${order.detailList }" var="detail">
                                    <li class="mui-table-view-cell mui-media bg_white pro_list-item">
                                        <a href="spxq.html">
                                            <img class="mui-media-object mui-pull-left" src="${path }/library/weixin/images/splb1.png">
                                            <div class="mui-media-body">
                                                <p class="mui-ellipsis-2 splb-tit mui-pull-left">${detail.cmdName }</p>
                                                <span class="mui-pull-right splb-jg">￥${detail.unitPrice }</span>
                                                <div class="spjg clear">
                                                    500g<span class="mui-pull-right">&times;2</span>
                                                </div>
                                            
                                            </div>
                                        </a>
                                    </li>
                                	</c:forEach>
                                </ul> 
                                <p class="ddbh-hj">共2件商品&nbsp;合计：<span class="ddbh-hj-jg">116.00</span></p>
                            </div>
                            <div class="btn-bg">
                               <div class="th-group">
                                     <a  class="th-btn" href="mine_th.html">退货</a>
                               </div>
                           </div> 
                           <div class="line-hui"></div>
                          </c:forEach>
                     </div>
                   </div>
				</div>
			</div>
		</div>
	</body>
<script src="${path }/library/weixin/js/jquery.min.js"></script>
<script src="${path }/library/weixin/js/mui.min.js"></script>
</html>