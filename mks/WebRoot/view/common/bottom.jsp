<!--tabs-->
<script>
 
/*底部导航开始*/
document.getElementById('changeIndex').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "${path}/wx/index/index.wx";
    });
    document.getElementById('changeFl').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "${path}/wx/type/list.wx";
    });
	document.getElementById('changeSc').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "${path}/wx/index/index.wx";
    });
	document.getElementById('changeGwc').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "${path}/wx/index/index.wx";
    });
	document.getElementById('mine').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "${path}/wx/index/index.wx";
    });
/*底部导航结束*/
</script>