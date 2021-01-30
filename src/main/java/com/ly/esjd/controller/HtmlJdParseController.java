package com.ly.esjd.controller;

import com.ly.esjd.bean.BaseResult;
import com.ly.esjd.service.HtmlJdService;
import com.ly.esjd.service.SearchService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author White Liu
 * @Description 爬取京东商品接口
 * @Date 2020/8/28 16:57
 * @Version 1.0
 */
@RestController
public class HtmlJdParseController {
    @Autowired
    HtmlJdService htmlJdService;
    @Autowired
    SearchService searchService;

    /**
     * 获取页面关键字数据
     * @param keywords
     * @return
     * @throws IOException
     */
    @GetMapping("/parse/{keyword}")
    public BaseResult htmlJdParse(@PathVariable("keyword")String keywords) throws IOException {
        Boolean isBoolean = htmlJdService.htmlJdParse(keywords);
        if(isBoolean){
            return BaseResult.error("爬取失败");
        }else{
            return BaseResult.ok("爬取成功");
        }
    }
    @GetMapping("/search/{keyword}/{pageNum}/{pageSize}")
    public BaseResult searchKeyWord(@PathVariable("keyword") String keyword,@PathVariable("pageNum") int pageNum,@PathVariable("pageSize") int pageSize) throws IOException {
        List<Map<String, Object>> list = searchService.searchKeyWord(keyword, pageNum, pageSize);
        return BaseResult.ok().put("data",list);

    }

}
