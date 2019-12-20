package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/20 15:28
 * @Desc: 首页列表bean
 */
public class HomeListBean implements Serializable {

    private String title;
    private String label;

    @Override
    public String toString() {
        return "HomeListBean{" +
                "title='" + title + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
