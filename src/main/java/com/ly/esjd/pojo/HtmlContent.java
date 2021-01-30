package com.ly.esjd.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author White Liu
 * @Description 网页数据封装实体类
 * @Date 2020/8/28 10:17
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HtmlContent {
    private String title;//标题
    private String price;//价格
    private String img;//图片
}
