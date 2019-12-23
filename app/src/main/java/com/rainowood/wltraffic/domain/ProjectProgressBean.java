package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/23 19:29
 * @Desc: 进度管理
 */
public class ProjectProgressBean implements Serializable {

    private String title;
    private boolean hasSelected;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isHasSelected() {
        return hasSelected;
    }

    public void setHasSelected(boolean hasSelected) {
        this.hasSelected = hasSelected;
    }
}
