var $;
layui.config({
	base : "js/"
}).use(['form','layer','jquery'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage;
		$ = layui.jquery;

 	var addUserArray = [],addUser;
 	form.on("submit(addUser)",function(data){
 		//是否添加过信息
	 	if(window.sessionStorage.getItem("addUser")){
	 		addUserArray = JSON.parse(window.sessionStorage.getItem("addUser"));
	 	}

	 	var userStatus,userGrade,userEndTime;

 		addUser = '{"usersId":"'+ new Date().getTime() +'",';//id
 		addUser += '"user_tel":"'+ $(".user_tel").val() +'"}';  //添加用户的联系方式

 		console.log(addUser);

 		// addUserArray.unshift(JSON.parse(addUser));
 		// window.sessionStorage.setItem("addUser",JSON.stringify(addUserArray));
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
        setTimeout(function(){
            top.layer.close(index);
			post_add_user_star()
			top.layer.msg("用户添加成功！");
 			layer.closeAll("iframe");
	 		//刷新父页面
	 		parent.location.reload();
        },2000);
 		return false;
 	})


	var post_add_user_star = function (){
		var req_url = URLBASE + '/api/followers/add/'
		$.ajax({
			type: 'POST',
			url: req_url,
			data: JSON.stringify({data:{ "token": window.sessionStorage.getItem("token"),"tel":$(".user_tel").val()}}),
			// data: {data:newD},
			contentType: 'application/json',
			dataType: "json",
			processData:false,
			async:false,
			success: function(usersData){
				// window.location.href = "../../index.html";
				// usersData = userList['data']['user_info_list']
				console.log("success userinfo :",usersData)
				// usersList();
			},
			error:function (e) {
				console.log(e)
			}
		});
	};


})

//格式化时间
function formatTime(_time){
    var year = _time.getFullYear();
    var month = _time.getMonth()+1<10 ? "0"+(_time.getMonth()+1) : _time.getMonth()+1;
    var day = _time.getDate()<10 ? "0"+_time.getDate() : _time.getDate();
    var hour = _time.getHours()<10 ? "0"+_time.getHours() : _time.getHours();
    var minute = _time.getMinutes()<10 ? "0"+_time.getMinutes() : _time.getMinutes();
    return year+"-"+month+"-"+day+" "+hour+":"+minute;
}
