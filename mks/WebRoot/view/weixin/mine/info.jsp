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
		<form id="form-info">
          <ul class="mui-table-view bg_white grzx-list mt0">         
              <li class="mui-table-view-cell">
                <div class="mui-navigate-right grzx-tx-text">
                  <label style="line-height:36px;" for="portrait">头像
                  <img id="img-portrait" class="mui-pull-right yjzz-input grzl_tx" src="${member.portrait }">
                  </label>
                  <input id="file" name="file" class="sc-hidden" type="file" onchange="upload(this.id)"/>
                  <input id="portrait" name="portrait" type="hidden"/>
                  <input id="memberId" name="memberId" type="hidden" value="${memberId }"/>
                </div>
              </li>
               <li class="mui-table-view-cell">
                <a>
                   <label class="grzl-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
                   <input class="yjzz-input" name="realName" value="${member.realName }">
                </a>
              </li>
               <li class="mui-table-view-cell">
                   <label class="grzl-label">手&nbsp;机&nbsp;号</label>
                   <input class="yjzz-input" name="mp" value="${member.mp }"/>
              </li>
            </ul>
		</form>
            <div class="line-hui"></div>
             <a class="save-btn" href="javascript:save();" >保存</a>
		</div>
	</body>
	<script src="${path }/library/weixin/js/jquery.min.js"></script>
    <script src="${path }/library/weixin/js/mui.min.js"></script>
    <script src="${path }/library/js/ajaxfileupload.js"></script>
	<script type="text/javascript">
	function upload(id){
		$.ajaxFileUpload({
			url:path+"/upload.do",
			type:'post',
			secureuri:false,
			fileElementId:id,
			dataType:'json',
			success:function(data){
				
				if (typeof (data.error) != 'undefined') {
	                if (data.error == '0000') {
	                	$("#portrait").val(data.imgurl);
	                	$("#img-portrait").attr('src',data.imgurl);
	                } else {
	                	
	                }
	            }
			},
			error: function (data, status, e)//服务器响应失败处理函数
	        {
				swal("error:"+data+"|"+status+"|"+e,"","error");
	        }
		});
	}
	function save(){
		$.ajax({
			url:path+"/wx/mbe/save.wx",
			type:"post",
			dataType:"json",
			data:$("#form-info").serialize(),
			success:function(data){
				if(data.rltCode=="0000"){
					mui.toast('保存成功！');
				}
			}
		});
	}
	</script>
</html>
