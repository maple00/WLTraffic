package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/24 9:19
 * @Desc: 进度管理进度
 */
public class SubProjectProgressBean implements Serializable {

    private String name;               // 进度标题
    private String statusTime;                // 进度完成时间
    private String imgState;         // 是否完成

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusTime() {
        return statusTime;
    }

    public void setStatusTime(String statusTime) {
        this.statusTime = statusTime;
    }

    public String getImgState() {
        return imgState;
    }

    public void setImgState(String imgState) {
        this.imgState = imgState;
    }
}
