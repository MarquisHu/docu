(function (window, document, $, undefined) {
$.fn.iselect=function(){
	var container = $(this);
	var b = $(this).children("a").children("div").children("b");
	var input = $(this).children("a").children("input");
	var li = $(this).children(".i-select-drop").children().children();
	var length = li.length;
	var selectArray =  new Array(length);
	li.each(function(index ){ 
		var value = $(this).children().html();
		selectArray[index] =value;
	});

	b.click(function(){
		if($(this).parents(".i-select-container").hasClass("i-select-dropdown-open")){
			$(this).parents(".i-select-container").removeClass("i-select-dropdown-open");
			$(this).parents(".i-select-container").children(".i-select-drop").addClass("i-select-offscreen");
		}else{
			$(this).parents(".i-select-container").addClass("i-select-dropdown-open");
			$(this).parents(".i-select-container").children(".i-select-drop").removeClass("i-select-offscreen");
			li.show();
		};
	})
	li.hover(function(){
		$(this).addClass("i-select-highlighted");
	},function(){
		$(this).removeClass("i-select-highlighted");
	})
	
	
	// $(document).click(function() { 
		
	// }); 
	
	$(document).bind("click",function(e){ 
	var target = $(e.target); 
	if(target.closest(".i-select-container").length == 0){ 
		container.removeClass("i-select-dropdown-open");
		container.children(".i-select-drop").addClass("i-select-offscreen");
	} 
	}) 


	li.click(function(){
		var value = $(this).children().html();
		$(this).parents(".i-select-container").children("a").children("input").attr({"value":value});
		$(this).parents(".i-select-container").removeClass("i-select-dropdown-open");
		$(this).parents(".i-select-container").children(".i-select-drop").addClass("i-select-offscreen");
	})
	//组合数组

	input.keyup(function() {
		$(this).parents(".i-select-container").removeClass("i-select-dropdown-open");
		$(this).parents(".i-select-container").children(".i-select-drop").addClass("i-select-offscreen");
  		var value = this.value;
  		if(value!=""){
  			var seachArray =  new Array();
	  		li.each(function(index ){ 
	  			var searchindex = selectArray[index].indexOf(value);
	  			if(searchindex >= 0){
	  				seachArray.push(index); 
	  					li.eq(index).show();	
	  			}
	  			else if(searchindex < 0){ 
	  					li.eq(index).hide();
	  			}
	  		});
	  		if(seachArray !=""){
	  			$(this).parents(".i-select-container").addClass("i-select-dropdown-open");
	  			$(this).parents(".i-select-container").children(".i-select-drop").removeClass("i-select-offscreen");
	  		}

  		}
	  		
  	})

	

}	
}(window, document, jQuery));
