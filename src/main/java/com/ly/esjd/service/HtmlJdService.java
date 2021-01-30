package com.ly.esjd.service;

import com.alibaba.fastjson.JSON;
import com.ly.esjd.pojo.HtmlContent;
import com.ly.esjd.util.HtmlJdUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @Author White Liu
 * @Description 详情
 * @Date 2020/8/28 16:53
 * @Version 1.0
 */
@Service
public class HtmlJdService {
    @Autowired
    HtmlJdUtil htmlJdUtil;
    @Autowired
    RestHighLevelClient restHighLevelClient;

    public Boolean htmlJdParse(String keyWords) throws IOException {
        //爬取页面上的数据
        List<HtmlContent> contentList = htmlJdUtil.htmlJdParse(keyWords);
        //创建Es请求对象，将爬取的数据放入ES中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for(int i=0;i<contentList.size();i++){
            //for循环将每一个content对象添加到ES请求中
            bulkRequest.add(new IndexRequest("jd_goods").source(JSON.toJSONString(contentList.get(i)), XContentType.JSON));
        }
       return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT).hasFailures();
    }
}
