$('#start_datetimepicker').datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 1
}).on('changeDate', function(ev){
	
	var end=$("#createEnd").val();
	if(end!="" && ev.date != null){
		var endDate = (new Date(end)).getTime()
		
		if(ev.date.valueOf() > endDate){
			$("#select_info").html("开始日期不能大于结束日期");
			$('#select_date').modal('show');
			$("#createStart").val("");
		}else{
		
		}
	}
	
});

$('#end_datetimepicker').datetimepicker({
    language: 'zh-CN',
    weekStart: 1,
    todayBtn: 1,
    autoclose: 1,
    todayHighlight: 1,
    startView: 2,
    minView: 2,
    forceParse: 1
}).on('changeDate', function(ev){
	
	var start=$("#createStart").val();
	if(start!="" && ev.date != null){
		var startDate = (new Date(start)).getTime()
		if(ev.date.valueOf() < startDate){
			$("#select_info").html("结束日期不能小于开始日期");
			$('#select_date').modal('show');
			$("#createEnd").val("");
		}
	}
	
});
