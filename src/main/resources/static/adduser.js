function check() {
	$.ajax({
		url : "/isexist",
		type : "POST",
		data : {"userName":$("#name").val()},
		success : function(data) {
			$("#message").html(data=="yes"?"已存在":"不存在");
		}
	});

	
}