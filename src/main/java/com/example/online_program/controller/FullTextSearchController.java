package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.service.EsOptService;
import com.example.online_program.service.SearchInstallPackage;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: wtt
 * @Date: 19-3-10
 * @Description:
 */
@RestController
@RequestMapping(value = "/search")
public class FullTextSearchController {

    /**
     * TODO fullTextSearchCode
     * kw 为搜索关键字,
     * page第几页,
     * num每页数据条数
     *
     * @param object
     * @return
     */
    @PostMapping(value = "/fulltext")
    @ResponseBody
    public Result fullTextSearchCode(@RequestBody JSONObject object) {
        String kw = null;
        int page = 1;
        int num = 0;
        if (object != null) {
            if (object.getString("keyword") != null) {
                kw = object.getString("keyword");
            }
            if (object.getString("num") != null) {
                num = Integer.valueOf(object.getString("num"));
            }
            if (object.getString("page") != null) {
                page = Integer.valueOf(object.getString("page"));
            }
            System.out.println("page : " + page + "\tkw : " + kw + "\tnum : " + num);
            Map data = EsOptService.queryDataFromEs(kw, page, num);
            return ResultGenerator.genSuccessResult(data);
        }
        return ResultGenerator.genFailResult("search full failed");

    }

    /**
     * TODO getSearchPackageMsg
     *
     * @param q(package name)
     * @return
     */
    @RequestMapping(value = "/spac")
    @ResponseBody
    public List getSearchPackageMsg(String q) {
        System.out.println("[ ---getSearchPackageMsg ---: " + q);
        List result = SearchInstallPackage.searchPackage(q);
        return result;
    }

    /**
     * TODO install Package of Python On Linux OS
     *
     * @param object(package name,pip version)
     * @return
     */
    @RequestMapping(value = "/ipac")
    @ResponseBody
    public Result installPyPackageOnLinux(@RequestBody JSONObject object) {
        System.out.println("-----installPyPackageOnLinux-----");
        if (object != null) {
            String pName = object.getString("pName");
            String pipV = object.getString("pipV");
            System.out.println("pName : " + pName + " pipV : " + pipV);
            int re = SearchInstallPackage.installPackage(pName, pipV);
            if (re == 0) {
                return ResultGenerator.genSuccessResult(0);
            }
            if (re == 1) {
                return ResultGenerator.genSuccessResult(1);
            }
        }
        return ResultGenerator.genFailResult("install package failed ..");
    }
}
