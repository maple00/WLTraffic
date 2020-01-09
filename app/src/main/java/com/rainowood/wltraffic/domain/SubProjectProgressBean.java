package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/24 9:19
 * @Desc: 进度管理进度
 */
public class SubProjectProgressBean implements Serializable {

    private String name;               // 进度标题
    private String time;                // 进度完成时间
    private boolean imgState;         // 是否完成

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isImgState() {
        return imgState;
    }

    public void setImgState(boolean imgState) {
        this.imgState = imgState;
    }
}
