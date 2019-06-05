
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
	console.log("*************************************11111")

	//登录按钮事件
	form.on("submit(login)",function(data){

		console.log("*************************************0000")
        window.location.href = "../../index.html";

        // console.log(data)
		// var newD = {data:data.field};
		// var newD = data.field;
		// var login_result = post(newD)
		// if (login_result.equals("OK")){
		// 	console.log(login_result)
		//
		// }else {
		// 	console.log(login_result);
		// }

	})


})


function jump()
{
    console.log("----------------------------------======",URLBASE)
    var tel = $("#tel").val();
    var pwd = $("#pwd").val();
    console.log("tel:",tel,"pwd ",pwd)
    // window.location.href = "../../index.html";
    var req_data = {"tel":tel,"pwd":pwd}
    console.log("------------------>"+req_data)
    post(req_data);


}

var post = function (newD){
    console.log(newD)
    var req_url = URLBASE + '/api/user/login/'
    $.ajax({
        type: 'POST',
        url: req_url,
        data: JSON.stringify({data:newD}),
        // data: {data:newD},
        contentType: 'application/json',
        dataType: "json",
        processData:false,
        async:false,
        success: function(r){
            if (r.code == 200){
                sessionStorage.setItem('token',r.data.token);
                sessionStorage.setItem('user_id',r.data.id);
                sessionStorage.setItem('userName',r.data.userName);
                sessionStorage.setItem('age',r.data.age);
                sessionStorage.setItem('birthday',r.data.birthday);
                sessionStorage.setItem('description',r.data.description);
                sessionStorage.setItem('email',r.data.email);
                sessionStorage.setItem('tel',r.data.tel);
                sessionStorage.setItem('is_super',r.data.is_super);
                console.log("=========== newD.tel ",newD.tel)
                console.log("=========== r.data.token ",r.data.token)
                console.log(" result data ",r.data)
                if (r.data.is_super) {
                    window.location.href = "../../index_admin.html";

                }else
                    window.location.href = "../../index.html";
            }
            else
                alert(r.message)
            return r
        },
        error:function (e) {
            console.log(e)
        }
    });
};



function login()
{
    console.log("----------------------------------======",URLBASE)
    var tel = $("#tel").val();
    var pwd = $("#pwd").val();
    console.log("tel:",tel,"pwd ",pwd)
    // window.location.href = "../../index.html";
    var req_data = {"tel":tel,"pwd":pwd}
    console.log("------------------>"+req_data)
    login_post(tel,pwd);


}

var login_post = function (tel,pwd){
    var req_url = URLBASE + '/api/user/login/'
    $.ajax({
        type: 'POST',
        url: req_url,
        data: JSON.stringify({data:{"tel":tel,"pwd":pwd}}),
        // data: {data:newD},
        contentType: 'application/json',
        dataType: "json",
        processData:false,
        async:false,
        success: function(r){
            if (r.code == 200){
                sessionStorage.setItem('token',r.data.token);
                sessionStorage.setItem('user_id',r.data.id);
                sessionStorage.setItem('userName',r.data.userName);
                sessionStorage.setItem('age',r.data.age);
                sessionStorage.setItem('birthday',r.data.birthday);
                sessionStorage.setItem('description',r.data.description);
                sessionStorage.setItem('email',r.data.email);
                sessionStorage.setItem('tel',r.data.tel);
                sessionStorage.setItem('is_super',r.data.is_super);
                sessionStorage.setItem('follower_count',r.data.follower_count);
                sessionStorage.setItem('following_count',r.data.following_count);
                sessionStorage.setItem('repositories_count',r.data.repositories_count);
                sessionStorage.setItem('code_count',r.data.code_count);
                // console.log("=========== newD.tel ",newD.tel)
                console.log("=========== r.data.token ",r.data.token)
                console.log(" result data ",r.data)
                if (r.data.is_super) {
                    window.location.href = "../../index_admin.html";

                }else
                    window.location.href = "../../index.html";
            }
            else
                alert(r.message)
            return r
        },
        error:function (e) {
            console.log(e)
        }
    });
};