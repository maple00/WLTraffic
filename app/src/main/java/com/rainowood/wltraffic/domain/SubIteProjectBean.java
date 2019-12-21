package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: shearson
 * @Time: 2019/12/21 22:47
 * @Desc: 项目中的子项目
 */
public class SubIteProjectBean implements Serializable {

    private int icon;           // 图标的位置
    private String label;       // 图标的名字

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
