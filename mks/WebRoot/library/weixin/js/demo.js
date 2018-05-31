//meishi开始
$(document).ready(function(){
    $(".meishi").click(function(){
        if ($('.meishi22').hasClass('grade-w-roll')) {
            $('.meishi22').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.meishi22').removeClass('meishi22-height');
			$('.screening').attr('style',' ');
        } else {
            $('.meishi22').addClass('grade-w-roll');
			$('.meishi22').addClass('meishi22-height');
			$(this).addClass('current');
			$(".Regional").removeClass('current');
			$(".Brand").removeClass('current');
			$(".Sort").removeClass('current');
			//$('.screening').attr('style','position: fixed;top:0;');
        }
    });
});

$(document).ready(function(){
    $(".meishia-w>li").click(function(){
        $(".meishia-t")
            .css("left","50%")
    });
});

$(document).ready(function(){
    $(".meishia-t>li").click(function(){
        $(".meishia-s")
            .css("left","50%")
    });
});





//Regional开始
$(document).ready(function(){
    $(".Regional").click(function(){
        if ($('.grade-eject').hasClass('grade-w-roll')) {
            $('.grade-eject').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.screening').attr('style',' ');
        } else {
            $('.grade-eject').addClass('grade-w-roll');
			$(this).addClass('current');
			$(".meishi").removeClass('current');
			$(".Brand").removeClass('current');
			$(".Sort").removeClass('current');
			//$('.screening').attr('style','position: fixed;top:0;');
			$('.meishi22').removeClass('meishi22-height');

        }
    });
});

$(document).ready(function(){
    $(".grade-w>li").click(function(){
        $(".grade-t")
            .css("left","50%")
    });
});

$(document).ready(function(){
    $(".grade-t>li").click(function(){
        $(".grade-s")
            .css("left","50%")
    });
});

//Brand开始

$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')) {
            $('.Category-eject').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.screening').attr('style',' ');
        } else {
            $('.Category-eject').addClass('grade-w-roll');
			$(this).addClass('current');
			$(".meishi").removeClass('current');
			$(".Regional").removeClass('current');
			$(".Sort").removeClass('current');
			//$('.screening').attr('style','position: fixed;top:0;');
						$('.meishi22').removeClass('meishi22-height');

        }
    });
});



//Sort开始

$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')) {
            $('.Sort-eject').removeClass('grade-w-roll');
			$(this).removeClass('current');
			$('.screening').attr('style',' ');
        } else {
            $('.Sort-eject').addClass('grade-w-roll');
			$(this).addClass('current');
			$(".meishi").removeClass('current');
			$(".Regional").removeClass('current');
			$(".Brand").removeClass('current');
			//$('.screening').attr('style','position: fixed;top:0;');
						$('.meishi22').removeClass('meishi22-height');

        }
    });
});


//判断页面是否有弹出
$(document).ready(function(){
    $(".meishi").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".meishi").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
    });
});$(document).ready(function(){
    $(".meishi").click(function(){
        if ($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
        };
    });
});






$(document).ready(function(){
    $(".Regional").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".Regional").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".Regional").click(function(){
        if ($('.meishi22').hasClass('grade-w-roll')){
            $('.meishi22').removeClass('grade-w-roll');
        };

    });
});





$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.Sort-eject').hasClass('grade-w-roll')){
            $('.Sort-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".Brand").click(function(){
        if ($('.meishi22').hasClass('grade-w-roll')){
            $('.meishi22').removeClass('grade-w-roll');
        };
    });
});





$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.Category-eject').hasClass('grade-w-roll')){
            $('.Category-eject').removeClass('grade-w-roll');
        };
    });
});
$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.grade-eject').hasClass('grade-w-roll')){
            $('.grade-eject').removeClass('grade-w-roll');
        };

    });
});
$(document).ready(function(){
    $(".Sort").click(function(){
        if ($('.meishi22').hasClass('grade-w-roll')){
            $('.meishi22').removeClass('grade-w-roll');
        };

    });
});



//js点击事件监听开始

function meishia(wbj){
    var arr = document.getElementById("meishia").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
    wbj.style.background = "#eee"
}

function meishib(tbj){
    var arr = document.getElementById("meishib").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
}

function meishis(sbj){
    var arr = document.getElementById("meishis").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.borderBottom = "";
    };
    sbj.style.borderBottom = "solid 1px #ff7c08"
}





function grade1(wbj){
    var arr = document.getElementById("gradew").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
    wbj.style.background = "#eee"
}

function gradet(tbj){
    var arr = document.getElementById("gradet").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
}

function grades(sbj){
    var arr = document.getElementById("grades").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.borderBottom = "";
    };
    sbj.style.borderBottom = "solid 1px #ff7c08"
}

function Categorytw(wbj){
    var arr = document.getElementById("Categorytw").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
	wbj.style.background = "#eee"
}

function Categoryt(tbj){
    var arr = document.getElementById("Categoryt").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
}

function Categorys(sbj){
    var arr = document.getElementById("Categorys").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.borderBottom = "";
    };
    sbj.style.borderBottom = "solid 1px #ff7c08"
}

function Sorts(sbj){
    var arr = document.getElementById("Sort-Sort").getElementsByTagName("li");
    for (var i = 0; i < arr.length; i++){
        var a = arr[i];
        a.style.background = "";
    };
    sbj.style.background = "#eee"
}

/*更多筛选开始*/
$(function(){
	$(".filtrate-address li a").click(function() {
		var addressLi = $(this).parent("li");
		var addressState = addressLi.hasClass("active");
		if (addressState == true) {
			addressLi.removeClass("active");
			address = "";
		} else {
			addressLi.addClass("active").siblings("li").removeClass("active");
			address = $(this).html();
		}
	});
})
/*更多筛选结束*/

/*底部导航开始*/
document.getElementById('changeIndex').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "index.html";
    });
    document.getElementById('changeFl').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "fl.html";
    });
	document.getElementById('changeSc').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "sc.html";
    });
	document.getElementById('changeGwc').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "gwc.html";
    });
	document.getElementById('mine').addEventListener('tap',
    function(e) {
      e.detail.gesture.preventDefault(); //修复iOS 8.x平台存在的bug，使用plus.nativeUI.prompt会造成输入法闪一下又没了
      window.location.href = "mine.html";
    });
/*底部导航结束*/

//获得slider插件对象(自动轮播)
$(function(){
	var gallery = mui('.mui-slider');
	gallery.slider({
	  interval:1000//自动轮播周期，若为0则不自动播放，默认为0；
	});

})

