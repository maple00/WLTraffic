package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: shearson
 * @Time: 2019/12/21 23:26
 * @Desc: 项目详情中的列表_文档列表
 */
public class SubItemListContentBean implements Serializable {

    private String id;     // 序号
    private String title;   // 标题
    private String wordTitle;   // 文档标题
    private List<AttachBean> mList;    // 文档

    public String getWordTitle() {
        return wordTitle;
    }

    public void setWordTitle(String wordTitle) {
        this.wordTitle = wordTitle;
    }

    public List<AttachBean> getmList() {
        return mList;
    }

    public void setmList(List<AttachBean> mList) {
        this.mList = mList;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
