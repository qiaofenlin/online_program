package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.constants.OnlineIde;
import com.example.online_program.service.EsOptService;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

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
     * @param object
     * @return
     */
    @PostMapping(value = "/fulltext")
    @ResponseBody
    public Result fullTextSearchCode(@RequestBody JSONObject object){
        String kw = null;
        int page = 1;
        int num = 0;
        if (object!=null){
            if (object.getString("keyword")!=null){
                kw = object.getString("keyword");
            }
            if (object.getString("num")!=null){
                num = Integer.valueOf(object.getString("num"));
            }
            if (object.getString("page")!=null){
                page = Integer.valueOf(object.getString("page"));
            }
            System.out.println("page : "+page + "\tkw : "+kw+"\tnum : "+num);
            Map data = EsOptService.queryDataFromEs(kw,page,num);
            return ResultGenerator.genSuccessResult(data);
        }
        return ResultGenerator.genFailResult("search full failed");
    }
}
