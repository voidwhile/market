(function(doc, win) {
				var docEl = doc.documentElement,
					resizeEvt = 'orientationchange' in window ? 'orientationchange' : 'resize',
					recalc = function() {
						var clientWidth = docEl.clientWidth;
						if(!clientWidth) return;
						if(clientWidth >= 640) {
							docEl.style.fontSize = '100px';
						} else {
							docEl.style.fontSize = 100 * (clientWidth / 640) + 'px';
						}
					};
			
				if(!doc.addEventListener) return;
				win.addEventListener(resizeEvt, recalc, false);
				doc.addEventListener('DOMContentLoaded', recalc, false);
			})(document, window);
$(function(){
	$("input[type=radio]").click(function(){
		$("input[type=radio]").prev().removeClass("mylable");
		$(this).prev().addClass("mylable");
		
	})
	$("#addSome").click(function(){
	$(".content_f_online").slideDown(600,function(){
		$height = $(".footer_online").height();
		$(".content_online").animate({
			paddingBottom:$height+50
		},300)
	});
		
})
	$(".content_online").click(function(){
	$(".content_f_online").slideUp(600,function(){
		$height = $(".footer_online").height();
		$(".content_online").animate({
			paddingBottom:$height+50
		},300)
	})
})
})
