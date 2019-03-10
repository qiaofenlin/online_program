$(function () {
    $(".l-btn-left.l-btn-text").css('font-size','36px')
    //todo asyn load treeNodes
    $('#tt').tree({
        dnd: true,
        url: 'http://127.0.0.1:8082/asyn/tree',
        method: 'get'
    });
    //todo edit treeNodeText
    $('#tt').tree({
        onClick: function (node) {
            $(this).tree('beginEdit', node.target);
            console.log("-------edit treeNodeText-------")
        }
    });
    //todo 拖拽文件位置后触发执行
    $('#tt').tree({
        onDrop: function (targetNode, source, point) {
            var targetId = $('#tt').tree('getNode', targetNode).id;
            console.log("targetId : " + targetId)
            console.log("sourceId : " + source.id)
            console.log("point : " + point)
            /*$.ajax({
                url: '...',
                type: 'post',
                dataType: 'json',
                data: {
                    id: source.id,
                    targetId: targetId,
                    point: point
                }
            });*/
        }
    });

});
//todo import project
function importProj() {
    $('#tt').tree({
        dnd: true,
        url: 'http://127.0.0.1:8082/asyn/tree',
        method: 'get'
    });
}
var isAlreadyCreateProj = false;

function createDir() {
    var node = $('#tt').tree('getSelected');
    if (node && node.hasOwnProperty("state")) {
        if (node.state == "closed") {
            var nodes = [{
                "id": 1,
                "text": "Directory",
                "state": "closed"
            }];
            $('#tt').tree('append', {
                parent: node.target,
                data: nodes
            });
        } else {
            alert("创建位置不正确")
        }

    }
}

function createFile() {
    var node = $('#tt').tree('getSelected');
    // var child = $('#tt').tree('getChildren',node.target);
    // console.log("parentNode : "+node +" child : "+child);
    if (node && node.hasOwnProperty("state")) {
        /*console.log("node2 : "+node.state)
        console.log("node3 : "+node.hasOwnProperty("state"))*/
        if (node.state == "closed") {
            var nodes = [{
                "id": 1,
                "text": "file",
                "state": "open"
            }];
            $('#tt').tree('append', {
                parent: node.target,
                data: nodes
            });
        } else {
            alert("目录下才能创建文件")
        }

    }
}

function createProj() {
    if (isAlreadyCreateProj) {
        return;
    }
    var nodes = [{
        "id": 1,
        "text": "Proj",
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
    $('#tt').tree('remove', node.target)
    console.log("exe delete")
}
