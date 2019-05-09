function baseAjax(requestPath, requestData,requestType,succCallback, errorCallback, dataType){
    /*requestPath：请求路径
     requestData：请求参数，默认为空
     requestType：请求方式("POST" 或 "GET")， 默认为 "GET"
     succCallback：请求成功回调函数
     errorCallback：请求失败回调函数
     dataType：预期服务器返回的数据类型， 默认为 JSON */
    requestData = requestData || {}
    requestType = requestType || 'GET'
    dataType = dataType || 'JSON'
    $.ajax({
        url:requestPath,               //请求地址
        type:requestType,              //请求类型
        data:requestData,              //请求数据
        timeout:100000,                //请求超时时间(毫秒)
        beforeSend:function(){
            load.init()                //发送请求之前，插入加载提示信息“拼命加载中···”
        },
        success:function(res){         //请求成功
            if(res.message == 'OK'){   //res.message不是唯一，也有可能是res.code 需结合项目实际场景来写入判断条件
                if(succCallback){
                    succCallback(res)  //返回OK回调函数，将返回的数据res传入到该回调函数中
                }
            }else{
                if(errorCallback){
                    errorCallback(res) //返回不是OK时回调函数，将返回的数据res传入到该回调函数中
                }
            }
        },
        complete:function(res,status){
            load.remove()             //请求完成 移除加载提示“拼命加载中···”
        },
        error:function(){
            tip()                     //请求错误，弹出提示
        }
    })
}



function jPost(path,data,succCallback,errorCallback){
    //再次封装-有参数
    baseAjax(path,data,'POST',succCallback,errorCallback)
}
function noParameterJPost(path,succCallback,errorCallback){
    //再次封装-无参数
    baseAjax(path,{},'POST',succCallback,errorCallback)
}

function jGet(path,data,succCallback,errorCallback){
    //再次封装-有参数
    baseAjax(path,data,'GET',succCallback,errorCallback)
}
function noParameterJGet(path,succCallback,errorCallback){
    //再次封装-无参数
    baseAjax(path,{},'GET',succCallback,errorCallback)
}
//只写了这两种类型请求方法，其他方式依次类推

