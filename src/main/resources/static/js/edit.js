//重命名之前的节点名称
var oldText = undefined;

$(function () {
    $('#tt').tree({
        dnd: false,// ban to drag
        //TODO edit treeNodeText
        onClick: function (node) {
            oldText = node.text;
            $(this).tree('beginEdit', node.target);
        },
        //update db after edit node
        onAfterEdit: function (node) {
            var type = undefined;
            if (node.id.substr(0, 1) === "p") {
                type = "proj";
            }
            console.log("onBefore : " + oldText + " onAfterEdit : " + node.text);
            if (oldText != node.text) {
                reqdata = "{\"id\":\"" + node.id + "\",\"text\":\"" + node.text + "\",\"type\":\"" + type + "\"}";
                ajaxRequest("/treenode/rename", reqdata, false);
            }
        },
        //TODO 拖拽文件时触发执行
        onDrop: function (target, source) {
            var targetId = $('#tt').tree('getNode', target).id;
            console.log("targetId" + targetId + " -- sourceId : " + source.id)
            console.log("sourceText : " + source.text)
            reqdata = "{\"sid\":\"" + source.id + "\",\"tid\":\"" + targetId + "\",\"text\":\"" + source.text + "\"}";
            var obj = ajaxRequest("/treenode/move", reqdata, false);
            if (obj.code == 200) {
                return true;
            } else {
                return false;
            }
        },
        //TODO 当节点展开时触发
        /*onExpand: function (node) {
            console.log("------当节点展开时触发------" + node.id)
            importProjectNodeData(node)
        }*/

        //TODO 展示代码(当用户双击一个节点时触发)
        onDblClick: function (node) {
            var o = ajaxRequest("/treenode/adjust/isFile",
                "{\"nodeId\":\"" + node.id + "\"}",
                false)
            if (o.code == 200 && o.data) {
                var obj = ajaxRequest("/code/show", "{\"codeId\":\"" + node.id + "\"}",false);
                $('#runText').val(obj.data);
            }else {
                console.log("double click error !!!")
            }
        }
    });

    //TODO import projectList listener
    $('#importProj').mouseenter(function () {
        $('#myProjsList').empty();
        var userId = "1111";
        var obj = ajaxRequest("/treenode/import/list", "{\"userId\":\"" + userId + "\"}", false);
        for (var i = 0; i < obj.data.length; i++) {
            var nodeStr = obj.data[i].projectId + "," + obj.data[i].projectName;
            var innerEle = "<div class='menu-text' style='height: 30px;line-height: 30px;'>" + obj.data[i].projectName + "</div>";
            $('#myProjsList').append("<div onclick='importPorjectInit(\"" + nodeStr + "\")' class='menu-item' style='height: 32px;'>" + innerEle + "</div>")
        }
    })
    //TODO mycode btn listener
    $('#codebtn').click(function () {
        window.open("/myCode.html?userId=1111");
        // window.location.href = "/myCode.html"
    })

    //TODO save code listener
    $('#savebtn').click(function () {
        var node = $('#tt').tree('getSelected');
        var code = $('#runText').val();
        var userId = "1111";
        console.log("[--------save code listener---------node : "+node)
        if (node != undefined) {
            console.log("saveMyCode : " + node.text + "\t" + node.id);
            var o = ajaxRequest("/treenode/adjust/isFile",
                "{\"nodeId\":\"" + node.id + "\"}",
                false);
            if (o.code == 200 && o.data) {
                if (confirm("保存代码到文件" + node.text + "?")) {
                    var uri = "/code/save?codeId="+node.id+"&userId="+userId;
                    var obj = ajaxRequest(
                        uri,
                        // "{\"nodeId\":\"" + node.id + "\",\"code\":\"" + code + "\"}",
                        // 'nodeId=' + node.id + '&code=' + code,
                        code,
                        false,
                        "text/plain");
                        // "application/x-www-form-urlencoded;charset=utf-8");
                    if (obj.code == 200) {
                        console.log("save code success !!!");
                    } else {
                        alert(obj.code + "\t" + obj.message);
                    }
                }
            } else {
                alert("请选择正确的代码保存位置!!")
            }
        } else {
            alert("请选择代码保存位置!!")
        }
    })
});

var isAlreadyCreateProj = false;

function importPorjectInit(nodeStr) {
    console.log("importPorjectInit:" + nodeStr);
    var arr = nodeStr.split(",");
    var nodes = [{
        "id": arr[0],
        "text": arr[1],
        "state": "closed"
    }]
    $('#tt').tree({
        // data: nodes
        url: "/treenode/getNode?id=" + arr[0]
    })
}

function importProjectNodeData(node) {
    var obj = ajaxRequest("/treenode/getNode", "{\"nodeId\":\"" + node.id + "\"}", false);
    $('#tt').tree('append', {
        parent: node.target,
        data: JSON.parse(obj.data)
    });
}

function createDirFile(type) {
    var node = $('#tt').tree('getSelected');
    var reqdata = "{\"type\":\"" + type + "\",\"parentId\":\"" + node.id + "\"}";
    var obj = ajaxRequest("/treenode/create/dirfile", reqdata, false);
    if (node) {
        if (obj.code == 200) {
            var stat = undefined;
            if (obj.data.label == "0") {
                stat = "closed";
            } else {
                stat = "open"
            }
            var nodes = [{
                "id": obj.data.childId,
                "text": obj.data.nodeName,
                "state": stat
            }];

            $('#tt').tree('append', {
                parent: node.target,
                data: nodes
            });
        } else {
            alert(obj.message)
        }
    }
}

function createProject() {
    if (isAlreadyCreateProj) {
        return;
    }
    var obj = ajaxRequest("/treenode/create/proj", "{\"userId\":\"1111\"}", false);
    console.log("obj.data : " + obj.data)
    var nodes = [{
        "id": obj.data.projectId,
        "text": obj.data.projectName,
        "state": "closed"
    }];
    $('#tt').tree('append', {
        data: nodes
    });
    isAlreadyCreateProj = true;
}

//todo deleteTreeNode
function deleteNode() {
    var node = $('#tt').tree('getSelected');
    var obj = ajaxRequest("/treenode/delete", "{\"id\":\"" + node.id + "\"}", false)
    if (obj.code == 200) {
        $('#tt').tree('remove', node.target)
    } else {
        alert("delete failed !")
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
