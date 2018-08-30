<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>编辑资料</title>
		<link href="${path }/library/weixin/css/mui.min.css" rel="stylesheet" />
        <link href="${path }/library/weixin/css/app.css" rel="stylesheet" />
        <link rel="stylesheet" href="${path }/library/weixin/css/LArea.css">
        <style>
        	.sc-hidden{
				width: 36px;
				height: 36px;
				display: block;
				position: absolute;
				top: 15px;
				right: 15px;
				opacity: 0;
				filter: alpha(opacity=0);
				-moz-opacity: 0;
				-khtml-opacity: 0.5;
				opacity: 0;
			}
        </style>
  
	</head>
	<body class="grzl">
		<header class="mui-bar mui-bar-nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left">返回</a>
			<h1 class="mui-title">编辑资料</h1>
		</header>
		<div class="mui-content">
          <ul class="mui-table-view bg_white grzx-list mt0">         
              <li class="mui-table-view-cell">
                <a class="mui-navigate-right grzx-tx-text">
                  <label style="line-height:36px;">头像</label>
                  <img class="mui-pull-right yjzz-input grzl_tx" src="${path }/library/weixin/images/tx.png">
                  <input id="sctx" class="sc-hidden" type="file"/>
                </a>
              </li>
              <li class="mui-table-view-cell">
                <a>
                  <label class="grzl-label">所在地区</label>
                  <input id="demo1" readonly class="yjzz-input"  value="省份&nbsp;&or;&nbsp;&nbsp;城市&nbsp;&or;&nbsp;&nbsp;县/区&nbsp;&or;">
                </a>
              </li>
              <li class="mui-table-view-cell">
                <a>
                  <label class="grzl-label">详细地址</label>
                  <input class="yjzz-input" >
                </a>
              </li>
               <li class="mui-table-view-cell">
                <a>
                   <label class="grzl-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
                   <input class="yjzz-input" >
                </a>
              </li>
               <li class="mui-table-view-cell">
                   <label class="grzl-label">手&nbsp;机&nbsp;号</label>
                   <input class="yjzz-input" />
              </li>
            </ul>
            <div class="line-hui"></div>
             <a class="save-btn" href="mine.html">保存</a>
		</div>
	</body>
	<script src="${path }/library/weixin/js/jquery.min.js"></script>
    <script src="${path }/library/weixin/js/mui.min.js"></script>
    <script src="${path }/library/weixin/js/LAreaData1.js"></script>
	<script src="${path }/library/weixin/js/LArea.js"></script>
    <script>
        var area1 = new LArea();
        area1.init({
            'trigger': '#demo1', //触发选择控件的文本框，同时选择完毕后name属性输出到该位置
            'valueTo': '#value1', //选择完毕后id属性输出到该位置
            'keys': {
                id: 'id',
                name: 'name'
            }, //绑定数据源相关字段 id对应valueTo的value属性输出 name对应trigger的value属性输出
            'type': 1, //数据源类型
            'data': LAreaData //数据源
        });
        area1.value=[1,13,3];//控制初始位置，注意：该方法并不会影响到input的value
    </script>

<%@ include file="../../common/bottom.jsp"%>
</html>
