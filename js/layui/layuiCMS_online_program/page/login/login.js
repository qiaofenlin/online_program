var post = function (newD){
	console.log(newD)
	$.ajax({
		type: 'POST',
		url: 'http://callintest.aimango.net/callin/account/admin/anon/login/',
		data: JSON.stringify({data:newD}),
		// data: {data:newD},
		// contentType: 'application/json',
		dataType: "json",
		processData:false,
		async:false,
		success: function(r){
			window.location.href = "../../index.html";
			console.log(r)
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
	form.on("submit(login)",function(data){
		console.log(data)
		// var newD = {data:data.field};
		var newD = data.field;
		post(newD)
		// window.location.href = "../../index.html";
		return false;
	})
})
