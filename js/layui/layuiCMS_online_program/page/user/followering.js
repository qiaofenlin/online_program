layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
	layer = parent.layer === undefined ? layui.layer : parent.layer,
	laypage = layui.laypage,
	$ = layui.jquery;
	var url;
	$.get("../../json/config.json",
		function(data){
			url = data[0].url;
			console.log("********* url config "+url)
		}
	)
	$.get("../../json/urls.json",
		function(data){
			url = url + data['get_user_list']['uri'];
			console.log("********* url urls "+url)

		}
	)
	console.log("********* url "+url)
	var usersData = '';
	var post_user_list = function (){
		$.ajax({
			type: 'POST',
			url: 'http://127.0.0.1:8080/api/followering/list/',
			data: JSON.stringify({data:{ "token": window.sessionStorage.getItem("token")}}),
			// data: {data:newD},
			contentType: 'application/json',
			dataType: "json",
			processData:false,
			async:false,
			success: function(userList){
				// window.location.href = "../../index.html";
				usersData = userList['data']['user_info_list']
				console.log("success userinfo :",usersData)
				usersList();

			},
			error:function (e) {
				console.log(e)
			}
		});
	};
	post_user_list();


	// $.get("../json/message.json",
	// 	function(data){
	// 		$(".newMessage span").text(data.length);
	// 	}
	// )


	//加载页面数据




	//获取用户信息
	// $.get("../../json/users_list.json", function(data){
	// 	usersData = data;
	// 	if(window.sessionStorage.getItem("addUser")){
	// 		var addUser = window.sessionStorage.getItem("addUser");
	// 		usersData = JSON.parse(addUser).concat(usersData);
	// 	}
	// 	//执行加载数据的方法
	// 	usersList();
	// })
	//
	// //
	// $.get("../../json/users_list.json", function(data){
	// 	usersData = data;
	// 	if(window.sessionStorage.getItem("addUser")){
	// 		var addUser = window.sessionStorage.getItem("addUser");
	// 		usersData = JSON.parse(addUser).concat(usersData);
	// 	}
	// 	//执行加载数据的方法
	// 	usersList();
	// })


	// console.log("=============== start")
	// var user_info = post()
	// console.log("=============== end ",user_info)



	//查询
	$(".search_btn").click(function(){
		var userArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	$.ajax({
					url : "../../json/users_list.json",
					type : "get",
					dataType : "json",
					success : function(data){
						if(window.sessionStorage.getItem("addUser")){
							var addUser = window.sessionStorage.getItem("addUser");
							usersData = JSON.parse(addUser).concat(data);
						}else{
							usersData = data;
						}
						for(var i=0;i<usersData.length;i++){
							var usersStr = usersData[i];
							var selectStr = $(".search_input").val();
		            		function changeStr(data){
		            			var dataStr = '';
		            			var showNum = data.split(eval("/"+selectStr+"/ig")).length - 1;
		            			if(showNum > 1){
									for (var j=0;j<showNum;j++) {
		            					dataStr += data.split(eval("/"+selectStr+"/ig"))[j] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>";
		            				}
		            				dataStr += data.split(eval("/"+selectStr+"/ig"))[showNum];
		            				return dataStr;
		            			}else{
		            				dataStr = data.split(eval("/"+selectStr+"/ig"))[0] + "<i style='color:#03c339;font-weight:bold;'>" + selectStr + "</i>" + data.split(eval("/"+selectStr+"/ig"))[1];
		            				return dataStr;
		            			}
		            		}
		            		//用户名
		            		if(usersStr.userName.indexOf(selectStr) > -1){
			            		usersStr["userName"] = changeStr(usersStr.userName);
		            		}
		            		//用户邮箱
		            		if(usersStr.userEmail.indexOf(selectStr) > -1){
			            		usersStr["userEmail"] = changeStr(usersStr.userEmail);
		            		}
		            		//性别
		            		if(usersStr.userSex.indexOf(selectStr) > -1){
			            		usersStr["userSex"] = changeStr(usersStr.userSex);
		            		}
		            		//会员等级
		            		if(usersStr.userGrade.indexOf(selectStr) > -1){
			            		usersStr["userGrade"] = changeStr(usersStr.userGrade);
		            		}
		            		if(usersStr.userName.indexOf(selectStr)>-1 || usersStr.userEmail.indexOf(selectStr)>-1 || usersStr.userSex.indexOf(selectStr)>-1 || usersStr.userGrade.indexOf(selectStr)>-1){
		            			userArray.push(usersStr);
		            		}
		            	}
		            	usersData = userArray;
		            	usersList(usersData);
					}
				})
            	
                layer.close(index);
            },2000);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加会员
	$(".usersAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "Following",
			type : 2,
			content : "addFollowering.html",
			success : function(layero, index){
				setTimeout(function(){
					layui.layer.tips('点击此处返回会员列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				},500)
			}
		})
		//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
		$(window).resize(function(){
			layui.layer.full(index);
		})
		layui.layer.full(index);
	})

	//批量删除
	$(".batchDel").click(function(){
		var $checkbox = $('.users_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.users_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
					console.log("==============data_id "+ $checked[0])

					for(var j=0;j<$checked.length;j++){
	            		for(var i=0;i<usersData.length;i++){
							console.log("==============data_id "+ $checked[0].parents("tr").find(".users_del").attr("data-id"))
							console.log("==============usersData "+ usersData[i].id)
							console.log("==============del "+ $checked.eq(j).parents("tr").find(".news_del").attr("data-id"))
							if(usersData[i].id == $checked.eq(j).parents("tr").find(".users_del").attr("data-id")){
								usersData.splice(i,1);
								usersList(usersData);
							}
						}
	            	}
	            	$('.users_list thead input[type="checkbox"]').prop("checked",false);
	            	form.render();
	                layer.close(index);
					layer.msg("删除成功");
	            },2000);
	        })
		}else{
			layer.msg("请选择需要删除的文章");
		}
	})

    //全选
	form.on('checkbox(allChoose)', function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		child.each(function(index, item){
			item.checked = data.elem.checked;
		});
		form.render('checkbox');
	});

	//通过判断文章是否全部选中来确定全选按钮是否选中
	form.on("checkbox(choose)",function(data){
		var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
		var childChecked = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"]):checked')
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})

	//操作
	$("body").on("click",".users_edit",function(){  //编辑
		layer.alert('您点击了会员编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'文章编辑'});
	})

	$("body").on("click",".users_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			console.log("=========== data_id "+_this.attr("data-id"))
			var user_info
			for(var i=0;i<usersData.length;i++){
				if(usersData[i].id == _this.attr("data-id")){
					// usersData.splice(i,1);
					user_info = usersData[i]
					post_del_user_star(user_info)
					post_user_list();
					console.log("================= usersData :" + usersData)
					usersList(usersData);
				}
			}
			layer.close(index);
		});
	})

	function usersList(){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			currData = usersData.concat().splice(curr*nums-nums, nums);
			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					console.log("************** data "+currData)
					dataHtml += '<tr>'
			    	+  '<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+  '<td>'+currData[i].userName+'</td>'
			    	+  '<td>'+currData[i].email+'</td>'
			    	+  '<td>'+checkout_sex(currData[i].sex)+'</td>'
			    	+  '<td>'+currData[i].age+'</td>'
			    	+  '<td>'+currData[i].tel+'</td>'
			    	+  '<td>'+currData[i].description+'</td>'
			    	+  '<td>'+currData[i].regTime+'</td>'
			    	+  '<td>'
					+    '<a class="layui-btn layui-btn-mini users_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
					+    '<a class="layui-btn layui-btn-danger layui-btn-mini users_del" data-id="'+data[i].id+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +  '</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="8">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		function checkout_sex(result_sex){
			if (result_sex) {
				return "男"
			}else{
				return "女"
			}
		}

		//分页
		var nums = 13; //每页出现的数据量
		laypage({
			cont : "page",
			pages : Math.ceil(usersData.length/nums),
			jump : function(obj){
				$(".users_content").html(renderDate(usersData,obj.curr));
				$('.users_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}

	var post_del_user_star = function (user_info){
		console.log("===============userData "+ user_info.id)
		$.ajax({
			type: 'POST',
			url: 'http://127.0.0.1:8080/api/followers/del/',
			data: JSON.stringify({data:{ "token": "e1ffe11015e74cda87e7e8e9b36c18a9","star_user_id":user_info.id}}),
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