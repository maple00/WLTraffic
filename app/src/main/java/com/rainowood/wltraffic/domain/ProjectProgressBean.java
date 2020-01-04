package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 19:29
 * @Desc: 进度管理
 */
public class ProjectProgressBean implements Serializable {

    private String name;           // 标题
    private int hasSelected;

    private List<SubProjectProgressBean> child;     // 子项

    @Override
    public String toString() {
        return "ProjectProgressBean{" +
                "name='" + name + '\'' +
                ", hasSelected=" + hasSelected +
                ", child=" + child +
                '}';
    }

    public List<SubProjectProgressBean> getChild() {
        return child;
    }

    public void setChild(List<SubProjectProgressBean> child) {
        this.child = child;
    }

    public int getHasSelected() {
        return hasSelected;
    }

    public void setHasSelected(int hasSelected) {
        this.hasSelected = hasSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
