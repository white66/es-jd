package com.ly.esjd.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

/**
 * @Author White Liu
 * @Description ElasticSearch的客户端，在官方文档中推荐使用RestHighLevelClient，在ES8.0之后将移除API
 * @Date 2020/8/25 15:45
 * @Version 1.0
 */
@Configuration
@EnableElasticsearchRepositories
public class RestClientConfig {
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("192.168.0.112",9200,"http")));
        return client;
    }
}
