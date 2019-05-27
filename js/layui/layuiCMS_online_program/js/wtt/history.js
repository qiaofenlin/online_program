$(function () {
    //设置或获取整个 URL 为字符串。
    console.log("url : "+window.location.href)
    //设置或获取 href 属性中跟在问号后面的部分。
    console.log("url ? : "+window.location.search);
    var userId = window.location.href.split("=")[1];
    console.log("Mycode Page Init userId : "+userId);
    // getHistoryCode(userId);
    getHistoryCode("1111");

})
function searchBtn() {
    var val = $('#kw').val();
    window.location.href = "../../page/wtt/search.html?kw="+val;
}
function getHistoryCode(userId) {
    if (userId!=undefined&&userId.trim()!=""){
        var data = "{\"userId\":\""+userId+"\"}";
        var obj = ajaxRequest("http://127.0.0.1:8080/code/history",data,false);
        if (obj&&obj.code == 200){
            if (obj.data.length>0){
                // $('.search').after("<p id=\"startAppend\">以下是您已经保存的代码</p>\n");
                for (var i=0;i<obj.data.length;i++){
                    var str = "<div id='"+obj.data[i].codeId+"'>" +
                        "        <textarea class='"+obj.data[i].codeId+"' rows='6' readonly='readonly' wrap='off' ondblclick='showFullContent(\""+obj.data[i].codeId+"\")' onclick='showPartContent(\""+obj.data[i].codeId+"\")'>"+obj.data[i].codeText+"</textarea>\n" +
                        "    " +
                        "    <p>创建于 "+obj.data[i].createTime+"，最后更新 "+obj.data[i].updateTime+"\n" +
                        "        <img src=\"../../images/wtt/del1.png\" id=\"del\" onclick='delcode(\""+obj.data[i].codeId+"\")'><a onclick='delcode(\""+obj.data[i].codeId+"\")'>删除</a></p>\n" +
                        "    <div id=\"line\"></div><div>";
                    $('.search').after(str);
                }
            }else {
                console.log("no code save ");
                $('.search').after("<h1>God help those who help themselves .</h1>\n" +
                    "    <img src=\"../../images/wtt/wdm.jpg\" id=\"hwc\">");
            }
        }
    }
}
function showFullContent(codeId) {
    console.log("--------showFullContent--------")
    var rows = countTxtRows(codeId);
    if (rows>6){
        $("."+codeId).attr("rows",rows);
    }
}

function showPartContent(codeId) {
    $("."+codeId).attr("rows",6);
}
function countTxtRows(codeId) {
    var rows = $("."+codeId)
        .text()
        .split("\n")
        .length;
    console.log("Rows : "+rows);
   /* var val = $("#"+codeId).val();
    var htm = $("#"+codeId).html();
    console.log("countValRows : "+val.split("\n").length);
    console.log("htmRows : "+htm.split("\n").length);*/
    return rows;
}

function delcode(codeId) {
    var data = "{\"codeId\":\""+codeId+"\"}";
    var obj = ajaxRequest("http://127.0.0.1:8080/code/delete",data,false);
    if (obj){
        if (obj.code==200){
            $("#"+codeId).remove();
            console.log("del code success");
        } else {
            console.log(obj.message)
        }
    }
}

function ajaxRequest(url, data, asyn, contentType) {
    var re = undefined;
    var ct = contentType;
    if (ct == undefined) {
        ct = "application/json; charset=UTF-8";
    }
    console.log("------ajaxRequest -------")
    console.log("url : " + url)
    console.log("data : " + data)
    console.log("asyn : " + asyn)
    console.log("contentType : " + contentType)
    console.log("-------------------------")
    $.ajax({
        type: "POST",//方法类型
        // dataType: "application/json;charset=UTF-8",
        contentType: ct,
        url: url,
        data: data,
        async: asyn,
        success: function (result) {
            console.log(result);
            // re = JSON.parse(result);
            re = result;
        },
        error: function (XMLHttpResponse, textStatus, errorThrown) {
            //todo 便于bug调试
            console.log("1 异步调用返回失败,XMLHttpResponse.readyState:" + XMLHttpResponse.readyState);
            console.log("2 异步调用返回失败,XMLHttpResponse.status:" + XMLHttpResponse.status);
            console.log("3 异步调用返回失败,textStatus:" + textStatus);
            console.log("4 异步调用返回失败,errorThrown:" + errorThrown);
        }
    });
    return re;
}

/*
* //设置或获取对象指定的文件名或路径。
alert(window.location.pathname);

//设置或获取整个 URL 为字符串。
alert(window.location.href);

//设置或获取与 URL 关联的端口号码。
alert(window.location.port);

//设置或获取 URL 的协议部分。
alert(window.location.protocol);

//设置或获取 href 属性中在井号“#”后面的分段。
alert(window.location.hash);

//设置或获取 location 或 URL 的 hostname 和 port 号码。
alert(window.location.host);

//设置或获取 href 属性中跟在问号后面的部分。
alert(window.location.search);*/