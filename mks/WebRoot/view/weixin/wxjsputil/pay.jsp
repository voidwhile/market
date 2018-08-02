<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../common/taglib.jsp" %>  
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>支付</title>
	</head>
<body>
</body>
<script type="text/javascript">
	function onBridgeReady(){
		WeixinJSBridge.invoke(
			'getBrandWCPayRequest', 
			${json},
			function(res){
				// 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
				if(res.err_msg == "get_brand_wcpay_request:ok" ) {
					if("${type}"==""){
					window.location.href="${path}/api/orderRemind?openid=${openid}&orderno=${orderno}&type=1&appid=${appid}";
					}else if("${type}"=="3"){
						window.location.href="${path}/api/chargeRemind?openid=${openid}&orderno=${orderno}&type=1&appid=${appid}";
					}
				}else{
					if("${type}"==""){
					window.location.href="${path}/api/orderRemind?openid=${openid}&orderno=${orderno}&type=2&appid=${appid}";
					}
				}
			}
		); 
	}
	if (typeof WeixinJSBridge == "undefined"){
		if( document.addEventListener ){
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		}else if (document.attachEvent){
			document.attachEvent('WeixinJSBridgeReady', onBridgeReady); 
			document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
		}
	}else{
		onBridgeReady();
	}
</script>
</html>