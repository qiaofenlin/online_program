layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','element'],function(){
	var form = layui.form(),
	layer = parent.layer === undefined ? layui.layer : parent.layer,
	laypage = layui.laypage,element = layui.element,
	$ = layui.jquery;

	if(window.sessionStorage.getItem("code_test")){
		var data = JSON.parse(window.sessionStorage.getItem("code_test"));
		fillData(data);
	}else{
		$.ajax({
			url : "../../json/code.json",
			type : "get",
			dataType : "json",
			success : function(data){
				init_code(data['get_code']['text']);
				// console.log(data['get_code']['text']);
			}
		})
	}

	//初始化用户参数
	function init_code(code) {
		window.editor_1 =CodeMirror.fromTextArea(document.getElementById("code_test"), {
			mode: {name: "text/x-cython",
				version: 2,
				singleLineStringErrors: false},
			lineNumbers: true,
			indentUnit: 4,
			matchBrackets: true,
		});
		console.log(editor_1.getTextArea().value)
		editor_1.setValue(code)
	}


	$(".run_code_btn").click(function(){
		$.ajax({
			url : "../../json/code_result.json",
			type : "get",
			dataType : "json",
			success : function(data){
				fillCodeRunResult(data)

				// console.log(data['get_code']['text']);
			}
		})
	})

	$(".upload_code_btn").click(function(){
		$.ajax({
			url : "../../json/code_result.json",
			type : "get",
			dataType : "json",
			success : function(data){
				console.log("--------------------------"+data);
			}
		})
	})

	function  fillCodeRunResult(data) {
		//判断字段数据是否存在
		function nullData(data){
			if(data == '' || data == "undefined"){
				return "未定义";
			}else{
				return data;
			}
		}

		var dataHtml = '';
		if(data.length != 0){
			for(var i=0;i<data.length;i++){
				dataHtml +=data[i] + ' <br> ';
			}
		}else{
			dataHtml = '暂无数据';
		}

		$(".run-rusult").html(nullData(dataHtml))
	}

	//触发事件
	var active = {
		tabAdd: function(){
			//新增一个Tab项
			element.tabAdd('demo', {
				title: '新选项'+ (Math.random()*1000|0) //用于演示
				,content: '内容'+ (Math.random()*1000|0)
				,id: new Date().getTime() //实际使用一般是规定好的id，这里以时间戳模拟下
			})
		}
		,tabDelete: function(othis){
			//删除指定Tab项
			element.tabDelete('demo', '44'); //删除：“商品管理”


			othis.addClass('layui-btn-disabled');
		}
		,tabChange: function(){
			//切换到指定Tab项
			element.tabChange('demo', '22'); //切换到：用户管理
		}
	};

	$('.site-demo-active').on('click', function(){
		var othis = $(this), type = othis.data('type');
		active[type] ? active[type].call(this, othis) : '';
	});

	//Hash地址的定位
	// var layid = location.hash.replace(/^#test=/, '');
	// element.tabChange('test', layid);
	//
	// element.on('tab(test)', function(elem){
	//     location.hash = 'test='+ $(this).attr('lay-id');
	// });


})