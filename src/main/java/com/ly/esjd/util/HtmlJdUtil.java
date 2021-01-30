package com.ly.esjd.util;

import com.ly.esjd.pojo.HtmlContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author White Liu
 * @Description 爬取京东网页数据工具类
 * @Date 2020/8/28 10:16
 * @Version 1.0
 */
@Component
public class HtmlJdUtil {
    /**
     * 爬取京东网页数据
     */
    public List<HtmlContent> htmlJdParse(String keyWords) throws IOException {
        String url = "https://search.jd.com/Search?keyword="+keyWords;
        //解析网页
        Document document = Jsoup.parse(new URL(url), 30000);
        Element goodsList = document.getElementById("J_goodsList");
        System.out.println(goodsList.html());
        Elements elements = goodsList.getElementsByTag("li");
        List<HtmlContent> htmlContentList = new ArrayList<>();
        for(Element el : elements){
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            HtmlContent htmlContent = new HtmlContent();
            htmlContent.setImg(img);
            htmlContent.setPrice(price);
            htmlContent.setTitle(title);
            htmlContentList.add(htmlContent);
            System.out.println("===========================");
            System.out.println(img);
            System.out.println(price);
            System.out.println(title);
        }
        return htmlContentList;
    }
}
