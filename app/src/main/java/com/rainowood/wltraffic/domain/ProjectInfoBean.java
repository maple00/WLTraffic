package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/20 15:28
 * @Desc: 首页列表bean
 */
public class ProjectInfoBean implements Serializable {

    private String title;       // 项目名称
    private String label;       // 项目标签

    private String time;        //时间

    @Override
    public String toString() {
        return "HomeListBean{" +
                "title='" + title + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
