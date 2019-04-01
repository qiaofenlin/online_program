package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.CodeInfo;
import com.example.online_program.impl.CodeStorage;
import com.example.online_program.repository.CodeStorageImpl;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: wtt
 * @Date: 19-3-10
 * @Description: 关于代码数据的存储
 */
@RestController
@RequestMapping("/code")
public class CodeStorageController {

    @PostMapping(value = "/save")
    @ResponseBody
    public Result saveCodeToDB(String nodeId, String code) {
        System.out.println("[---------saveCodeToDB--------]");
        System.out.println("nodeId : " + nodeId + "\n" + "code : " + code);
        if (nodeId != null && !nodeId.trim().equals("")) {
            CodeStorage codeStorage = new CodeStorageImpl();
            CodeInfo codeInfo = new CodeInfo();
            codeInfo.setCodeId(nodeId);
            codeInfo.setCodeText(code);
            codeInfo.setUserId("1111");
            if (codeStorage.save(codeInfo)) {
                return ResultGenerator.genSuccessResult();
            }
        }
        return ResultGenerator.genFailResult("save code failed !!");
    }

    @PostMapping(value = "/show")
    @ResponseBody
    public Result showCode(String codeId) {
        System.out.println("[---------showCode-------codeId : ]" + codeId);
        if (codeId != null && !codeId.trim().equals("")) {
            CodeStorage codeStorage = new CodeStorageImpl();
            String result = codeStorage.show(codeId);
            return ResultGenerator.genSuccessResult(result);
        }
        return ResultGenerator.genFailResult("show code failed");
    }
}
