layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;

	//加载页面数据
	var projData = '';
	// $.ajax({
	// 	url : "../../json/linksList.json",
	// 	type : "get",
	// 	dataType : "json",
	// 	success : function(data){
	// 		projData = data;
	// 		if(window.sessionStorage.getItem("addLinks")){
	// 			var addLinks = window.sessionStorage.getItem("addLinks");
	// 			projData = JSON.parse(addLinks).concat(projData);
	// 		}
	// 		//执行加载数据的方法
	// 		linksList();
	// 	}
	// })


	var post_proj_list = function (){
		$.ajax({
			type: 'POST',
			url: 'http://47.93.221.91:8080/api/projectManage/list/',
			data: JSON.stringify({data:{ "token": window.sessionStorage.getItem("token"),"page":1,"size":10}}),
			// data: {data:newD},
			contentType: 'application/json',
			dataType: "json",
			processData:false,
			async:false,
			success: function(codeList){

				projData = codeList['data']['proj_info_list']
				console.log("success result_code_list :",projData)
				ProjList();

			},
			error:function (e) {
				console.log(e)
			}
		});
	};
	post_proj_list();
	
	
	//查询
	$(".search_btn").click(function(){
		var newArray = [];
		if($(".search_input").val() != ''){
			var index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
            setTimeout(function(){
            	$.ajax({
					url : "../../json/linksList.json",
					type : "get",
					dataType : "json",
					success : function(data){
						// if(window.sessionStorage.getItem("addLinks")){
						// 	var addLinks = window.sessionStorage.getItem("addLinks");
						// 	projData = JSON.parse(addLinks).concat(data);
						// }else{
						// 	projData = data;
						// }
						for(var i=0;i<projData.length;i++){
							var linksStr = projData[i];
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
		            		//网站名称
		            		if(linksStr.linksName.indexOf(selectStr) > -1){
			            		linksStr["linksName"] = changeStr(linksStr.linksName);
		            		}
		            		//网站地址
		            		if(linksStr.linksUrl.indexOf(selectStr) > -1){
			            		linksStr["linksUrl"] = changeStr(linksStr.linksUrl);
		            		}
		            		//
		            		if(linksStr.showAddress.indexOf(selectStr) > -1){
			            		linksStr["showAddress"] = changeStr(linksStr.showAddress);
		            		}
		            		if(linksStr.linksName.indexOf(selectStr)>-1 || linksStr.linksUrl.indexOf(selectStr)>-1 || linksStr.showAddress.indexOf(selectStr)>-1){
		            			newArray.push(linksStr);
		            		}
		            	}
		            	projData = newArray;
		            	linksList(projData);
					}
				})
            	
                layer.close(index);
            },2000);
		}else{
			layer.msg("请输入需要查询的内容");
		}
	})

	//添加友情链接
	$(".linksAdd_btn").click(function(){
		var index = layui.layer.open({
			title : "添加友情链接",
			type : 2,
			content : "linksAdd.html",
			success : function(layero, index){
				setTimeout(function(){
					layui.layer.tips('点击此处返回友链列表', '.layui-layer-setwin .layui-layer-close', {
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
		var $checkbox = $('.links_list tbody input[type="checkbox"][name="checked"]');
		var $checked = $('.links_list tbody input[type="checkbox"][name="checked"]:checked');
		if($checkbox.is(":checked")){
			layer.confirm('确定删除选中的信息？',{icon:3, title:'提示信息'},function(index){
				var index = layer.msg('删除中，请稍候',{icon: 16,time:false,shade:0.8});
	            setTimeout(function(){
	            	//删除数据
	            	for(var j=0;j<$checked.length;j++){
	            		for(var i=0;i<projData.length;i++){
							if(projData[i].linksId == $checked.eq(j).parents("tr").find(".links_del").attr("data-id")){
								projData.splice(i,1);
								linksList(projData);
							}
						}
	            	}
	            	$('.links_list thead input[type="checkbox"]').prop("checked",false);
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
		data.elem.checked;
		if(childChecked.length == child.length){
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = true;
		}else{
			$(data.elem).parents('table').find('thead input#allChoose').get(0).checked = false;
		}
		form.render('checkbox');
	})
 
	//操作
	$("body").on("click",".links_edit",function(){  //编辑
		layer.alert('您点击了友情链接编辑按钮，由于是纯静态页面，所以暂时不存在编辑内容，后期会添加，敬请谅解。。。',{icon:6, title:'友链编辑'});
	})

	$("body").on("click",".links_del",function(){  //删除
		var _this = $(this);
		layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
			//_this.parents("tr").remove();
			for(var i=0;i<projData.length;i++){
				if(projData[i].projId == _this.attr("data-id")){
					post_del_proj(projData[i])
				}
			}
			ProjList();
			layer.close(index);
		});
	})

	function ProjList(that){
		//渲染数据
		function renderDate(data,curr){
			var dataHtml = '';
			// if(!that){
			// 	currData = projData.concat().splice(curr*nums-nums, nums);
			// }else{
			// 	currData = that.concat().splice(curr*nums-nums, nums);
			// }
			console.log("============================>",projData)
			currData = projData.concat().splice(curr*nums-nums, nums);

			if(currData.length != 0){
				for(var i=0;i<currData.length;i++){
					dataHtml += '<tr>'
			    	+'<td><input type="checkbox" name="checked" lay-skin="primary" lay-filter="choose"></td>'
			    	+'<td align="left">'+currData[i].projName+'</td>'
			    	+'<td>'+ProjStatus(currData[i].projStatus)+'</td>'
			    	+'<td>'+ProjType(currData[i].projType)+'</td>'
			    	+'<td>'+currData[i].projCreateTime+'</td>'
			    	// +'<td>'+currData[i].projFromId+'</td>'
			    	+'<td>'
					+  '<a class="layui-btn layui-btn-mini links_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
					+  '<a class="layui-btn layui-btn-danger layui-btn-mini links_del" data-id="'+data[i].projId+'"><i class="layui-icon">&#xe640;</i> 删除</a>'
			        +'</td>'
			    	+'</tr>';
				}
			}else{
				dataHtml = '<tr><td colspan="7">暂无数据</td></tr>';
			}
		    return dataHtml;
		}

		//分页
		var nums = 13; //每页出现的数据量
		if(that){
			projData = that;
		}
		laypage({
			cont : "page",
			pages : Math.ceil(projData.length/nums),
			jump : function(obj){
				$(".links_content").html(renderDate(projData,obj.curr));
				$('.links_list thead input[type="checkbox"]').prop("checked",false);
		    	form.render();
			}
		})
	}

	function ProjStatus(status) {
		// public 0/ private 1/ friend 2
		if (status == 0) {
			return "Public"
		}else if (status == 1) {
			return "Private";
		}else if (status == 2) {
			return "Friend";
		} else {
			return "未知"
		}
	}

	function ProjType(type) {
		// star 0 fork 1 own 2
		if (type == 0) {
			return "收藏"
		}else if (type == 2) {
			return "创建";
		}else {
			return "未知"
		}
	}

	var post_del_proj = function (proj_info){
		console.log("===============userData "+ proj_info.projId)
        var req_url = URLBASE + '/api/projectManage/delete/'

        $.ajax({
			type: 'POST',
			url: req_url,
			data: JSON.stringify({data:{ "token": window.sessionStorage.getItem("token"),"proj_id":proj_info.projId,"is_active":false}}),
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
