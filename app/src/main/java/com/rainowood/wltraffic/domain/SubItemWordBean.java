package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: shearson
 * @Time: 2019/12/21 23:56
 * @Desc: 页面详情文档
 */
public class SubItemWordBean implements Serializable {

    private String backEditTitle;   // 标题
    private String wordTitle;       // 内容

    public String getBackEditTitle() {
        return backEditTitle;
    }

    public void setBackEditTitle(String backEditTitle) {
        this.backEditTitle = backEditTitle;
    }

    public String getWordTitle() {
        return wordTitle;
    }

    public void setWordTitle(String wordTitle) {
        this.wordTitle = wordTitle;
    }
}
