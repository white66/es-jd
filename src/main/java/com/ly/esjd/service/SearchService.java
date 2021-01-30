package com.ly.esjd.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author White Liu
 * @Description 详情
 * @Date 2020/8/31 0:12
 * @Version 1.0
 */
@Service
public class SearchService {
    @Autowired
    RestHighLevelClient restHighLevelClient;

    public List<Map<String,Object>> searchKeyWord(String keyword,int pageNum, int pageSize) throws IOException {
        //条件搜索
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        //分页
        sourceBuilder.from(pageNum);
        sourceBuilder.size(pageSize);
        //精准匹配
        TermQueryBuilder termQueryBuilder = new TermQueryBuilder("title",keyword);
        sourceBuilder.query(termQueryBuilder);
        //执行搜索
        searchRequest.source(sourceBuilder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Map<String,Object>> resultList = new ArrayList<>();
        for(SearchHit documentFields:search.getHits().getHits()){
           resultList.add(documentFields.getSourceAsMap());
        }
        return resultList;
    }
}
