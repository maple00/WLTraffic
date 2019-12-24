package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 19:29
 * @Desc: 进度管理
 */
public class ProjectProgressBean implements Serializable {

    private String title;
    private int hasSelected;

    private List<SubProjectProgressBean> mList;

    public List<SubProjectProgressBean> getmList() {
        return mList;
    }

    public void setmList(List<SubProjectProgressBean> mList) {
        this.mList = mList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHasSelected() {
        return hasSelected;
    }

    public void setHasSelected(int hasSelected) {
        this.hasSelected = hasSelected;
    }
}
