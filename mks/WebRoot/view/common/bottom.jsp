<!--tabs-->
<script>
 
/*底部导航开始*/
document.getElementById('index').addEventListener('tap',
function(e) {
  e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
  window.location.href = "${path}/weixin/home/home.do?userid=${userid}&openid=${openid}";
});
document.getElementById('hdyy').addEventListener('tap',
function(e) {
  e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
  window.location.href = "${path}/weixin/event/list.do?openid=${openid}&userid=${userid}";
});
document.getElementById('lynk').addEventListener('tap',
 function(e) {
		 e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
		  window.location.href = "${path}/weixin/card/card.do?openid=${openid}&userid=${userid}";
		});
document.getElementById('grzx').addEventListener('tap',
function(e) {
  e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
  window.location.href = "${path}/weixin/wechatuser/list.do?userid=${userid}&openid=${openid}";
});
/*底部导航结束*/
</script>