var post = function (newD){
	console.log(newD)
	$.ajax({
		type: 'POST',
		url: 'http://127.0.0.1:8080/api/user/login/',
		data: JSON.stringify({data:newD}),
		// data: {data:newD},
		contentType: 'application/json',
		dataType: "json",
		processData:false,
		async:false,
		success: function(r){
			console.log("********************************"+r.data)
			return r.data
			// window.location.href = "../../index.html";
			// console.log(r)
		},
		error:function (e) {
			console.log(e)
		}
	});
};
layui.config({
	base : "js/"
}).use(['form','layer'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		$ = layui.jquery;
	//video背景
	$(window).resize(function(){
		if($(".video-player").width() > $(window).width()){
			$(".video-player").css({"height":$(window).height(),"width":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}else{
			$(".video-player").css({"width":$(window).width(),"height":"auto","left":-($(".video-player").width()-$(window).width())/2});
		}
	}).resize();
	
	//登录按钮事件
	form.on("submit(login1)",function(data){
		console.log(data)
		// var newD = {data:data.field};
		var newD = data.field;
		var login_result = post(newD)
		if (login_result.equals("OK")){
			console.log(login_result)
			window.location.href = "../../index.html";
		}else {
			console.log(login_result);
		}

	})
})
