package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: shearson
 * @Time: 2019/12/21 23:26
 * @Desc: 项目详情中的列表
 */
public class SubItemListContentBean implements Serializable {

    private int id;     // 序号
    private String title;   // 标题
    private String content;     // 内容
    private List<SubItemWordBean> mList;    // 文档

    public List<SubItemWordBean> getmList() {
        return mList;
    }

    public void setmList(List<SubItemWordBean> mList) {
        this.mList = mList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
