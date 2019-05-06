package com.example.online_program.service;

import com.example.online_program.constants.OnlineIde;
import com.example.online_program.utils.Utils;
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

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wtt
 * @Date: 19-4-5
 * @Description:
 */
public class EsOptService {
    public static TransportClient client;
    static {
        client = getEsCli();
    }
    public static TransportClient getEsCli() {
        TransportClient client = null;
        try {
            while (true) {
                client = new PreBuiltTransportClient(Settings.EMPTY)
                        .addTransportAddress(new TransportAddress(InetAddress
                                .getByName(OnlineIde.ES_IP), OnlineIde.ES_PORT));
                if (client!=null){
                    break;
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static void closeEsCli(TransportClient client) {
        if (client != null) {
            client.close();
        }
    }

    public static boolean saveDataToEs(String codeId,
                                       String codeText, String userId) {
        try {
            if (client == null) {
                client=getEsCli();
            }
            client.prepareIndex(OnlineIde.ES_INDEX, OnlineIde.ES_TABLE)
                    .setSource(jsonBuilder()
                            .startObject()
                            .field("codeId", codeId)
                            .field("codeText", codeText)
                            .endObject())
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Map queryDataFromEs(String keyWord, int page, int num) {
        Map result = new HashMap();
        QueryBuilder queryBuilder = QueryBuilders.matchQuery("codeText", keyWord);
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        highlightBuilder.field("codeText");
        // 搜索数据
        if (client != null) {
            client=getEsCli();
        }
        SearchResponse response = client.prepareSearch(OnlineIde.ES_INDEX)
                .setQuery(queryBuilder)
                .setFrom(Utils.getLimitStartPoint(page, num))
                .setSize(num)
                .highlighter(highlightBuilder)
                .execute().actionGet();
        //获取查询结果集
        SearchHits searchHits = response.getHits();
        System.out.println("共搜到:" + searchHits.getTotalHits() + "条结果!");
        result.put("count", searchHits.getTotalHits());
        result.put("pages", Utils.countPages(searchHits.getTotalHits(), num));
        //遍历结果
        List list = new ArrayList();
        for (SearchHit hit : searchHits) {
            System.out.println("score : "+hit.getScore());
            Map map = new HashMap();
            String codeId = hit.getSourceAsMap().get("codeId").toString();
            Text[] text = hit.getHighlightFields().get("codeText").getFragments();
            StringBuilder builder = new StringBuilder();
            for (Text str : text) {
                builder.append(str.string());
                builder.append("...");
                System.out.println(str.string());
            }
            map.put("codeId", codeId);
            map.put("codeText", builder.toString());
            list.add(map);
        }
        result.put("list", list);
        return result;
    }

    public static void main(String[] args) {
        queryDataFromEs("CountDownLatch RandomAccessFile",1,15);
    }
}
