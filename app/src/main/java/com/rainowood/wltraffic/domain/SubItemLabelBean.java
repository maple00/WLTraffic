package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: shearson
 * @Time: 2019/12/21 22:12
 * @Desc: 项目的标题的标签部分
 */
public class SubItemLabelBean implements Serializable {

    private String title;       // 标题
    private String content;     // 内容

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
