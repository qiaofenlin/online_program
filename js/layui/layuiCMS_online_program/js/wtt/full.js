var kw = undefined;//搜索关键字
var num = 5;//每页展示的数据条数

$(function () {
    kw = window.location.href.split("=")[1];
    // kw = "CountDownLatch RandomAccessFile";
    appendSearchResultHtml();
    $('.kwb').click(function () {
        kw = $('.kwi').val();
        console.log("*** kw *** : "+kw);
        appendSearchResultHtml();
    })
})

function appendSearchResultHtml() {
    $('.kwi').attr("value",kw);
    var data = "{\"keyword\":\"" + kw + "\",\"num\":\"" + num + "\"}";
    var obj = ajaxRequest(URLBASE+"/search/fulltext", data, false);
    if (obj && obj.code == 200) {
        // {"count":200,"pages":8,"list":[{"codeId":"0213","codeText":"xxx"},{},{}]}
        if (obj.data) {
            $('#first').empty();//删除被选元素的子元素
            $('#first').append("<p id=\"fullre\">共找到相关结果约<em>" + obj.data.count + "</em>个</p>\n");
            if (obj.data.list.length > 0) {
                for (var i = 0; i < obj.data.list.length; i++) {
                    $('#first').append("<div id=\"full\" class='" + obj.data.list[i].codeId + "'>\n" +
                        "            <a id=\"fulink\" href=\"#\" onclick='showCodeDetail(\""+obj.data.list[i].codeId+"\")'>\n" +
                        "           " + obj.data.list[i].codeText + "</a>\n" +
                        "        </div>");
                }
                appendSplitPageDiv(obj);
            }
        }
    }
}

function appendSplitPageDiv(obj) {
    var sumPage = obj.data.pages;
    console.log("sumPage : "+sumPage);
    console.log("appendSplitPageDiv -- Num : "+num);
    if (obj.data.count > num) {
        console.log("[ --- append page div --- ]");
        $('#first').append("<div id=\"page\"></div>");
        $("#page").Page({
            totalPages: sumPage,//分页总数
            liNums: 8,//分页的数字按钮数(建议取奇数)
            activeClass: 'activP', //active 类样式定义
            callBack: function (page) {
                console.log("click page : "+page);
                listenerOnPage(page);
            }
        });
    }
}

function listenerOnPage(page) {
    var data = "{\"keyword\":\"" + kw + "\",\"page\":\"" + page + "\",\"num\":\"5\"}";
    var obj = ajaxRequest(URLBASE+"/search/fulltext", data, false);
    if (obj && obj.code==200){
        console.log("---- listenerOnPage -----")
        $('div').filter('#full').remove();
        // console.log("---- listenerOnPage After ---- ")
        for (var i=0;i<obj.data.list.length;i++){
            $('#page').before("<div id=\"full\" class='" + obj.data.list[i].codeId + "'>\n" +
                "            <a id=\"fulink\" href=\"#\" onclick='showCodeDetail(\""+obj.data.list[i].codeId+"\")'>\n" +
                "           " + obj.data.list[i].codeText + "</a>\n" +
                "        </div>");
        }
    }else{
        console.log("listenerOnPage Else")
    }
}

/*function removeSplitPage() {
    $('#page')
}*/
function showCodeDetail(codeId) {
    var obj = ajaxRequest(URLBASE+"/code/show", "{\"codeId\":\"" + codeId + "\"}", false);
    if (obj.data){
        if (!($('#second').length>0)) {
            $('#first').after("<div id=\"second\">\n" +
                "        <textarea rows=\"54\" wrap='off' onclick=\"showPartContent()\" ondblclick=\"showFullContent()\"></textarea>\n" +
                "    </div>");
        }
        $('textarea').text(obj.data);
    }
}

function showFullContent() {
    console.log("--------showFullContent--------")
    var rows = $("textarea")
        .text()
        .split("\n")
        .length;
    console.log("Rows : " + rows);
    if (rows > 54) {
        $("textarea").attr("rows", rows);
    }
}

function showPartContent(codeId) {
    $("textarea").attr("rows", 54);
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