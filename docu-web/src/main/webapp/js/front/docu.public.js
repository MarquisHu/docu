;(function (window, document, $, undefined) {
	//登录框
	$('.fancybox').fancybox();
	//登录
	$('#login-form').submit(function(){
		$.ajax({
	        url:"/data/login.json",
	        data:$('#login-form').serialize(),
	        dataType:"json",
	        error:function(data){
	        console.log(data);
	        $('#login-form').children(".error").html(data.vcode_error).show();
	        },
	        success:function(data){
	        //console.log(data);
		        $('#login-form').children(".error").html("提示："+data.success).show();
		        $('#login-buttom').hide();
		        $('#top-bar-user').show();
		        setTimeout(function(){
					$.fancybox.close();
				},1000);
	     	}
	    });
	        return false;
     });  
	
	  //登录后
       		$("#top-bar-user").hover(function(){
       			$(this).children("ul").show();
       		},function(){
       			$(this).children("ul").hide();
       		});

       //goto Top
       $(window).scroll(function(){
		offsetTop = $(window).scrollTop();
		$("#goToTop")
		//根据计算判断回到顶部按钮显示与消失
		if (offsetTop > 300){
			$("#goToTop").slideDown();
		}else{
			$("#goToTop").slideUp();	
		}
		}); 
	 	$("#goToTop").click(function(){ 
			$("html,body").animate({scrollTop:0},700);
		});	


}(window, document, jQuery));

(function($) { 
	$.fn.easytabs=function(options){
		var opts = $.extend({},$.fn.easytabs.defaults, options);
		var easytab = $(this);
		opts.li = $(opts.etTab).find("li");
		opts.li.click(function(){
			var i = $(this).index();
			if(	!$(this).hasClass(opts.etcurrent)){
				$(this).siblings().removeClass(opts.etcurrent);
				$(this).addClass(opts.etcurrent);
				$(this).parent().parent().parent().find(opts.etContent).siblings(opts.etContent).hide();
				$(this).parent().parent().parent().find(opts.etContent).eq(i).show();
			}
				
		})
	}
	$.fn.easytabs.defaults  = {
			etTab	: ".tab-nav",
			etTabLi : "li",
			etContent :".tab-content",
			etcurrent : "current"
	};	
})(jQuery);   

/**/
(function($) { 
	$.fn.easyMenu=function(options){
		var opts = $.extend({},$.fn.easyMenu.defaults, options);
		var easyMenu = $(this);
		opts.li = $(opts.eMTab).find("li");
		switch(opts.etevent){
		case "hover":
		  opts.li.hover(function(){
			 methorHover($(this));
		})
		break;
		case "click":
		 opts.li.click(function(){
		 	 methorHover($(this));
			
		})
		break;
		default: opts.li.hover(function(){
			 methorHover($(this));
		})
		}
		
		function methorHover(_this){
			_this.addClass(opts.eMactive);
			_this.siblings().removeClass(opts.eMactive);
			if(_this.find("ul").length>0){
				_this.find("ul").stop(true,true).slideDown();
				_this.siblings().stop(true,true).find("ul").slideUp();
			}
		}
	}
	$.fn.easyMenu.defaults  = {
			eMTab	: ".menu-nav",
			eMTabLi : "li",
			eMTabSon: "ul",
			eMactive :"active",
			eMevent:"click",
			methor:""
	};	
})(jQuery);    