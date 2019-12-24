package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/24 9:19
 * @Desc: 进度管理进度
 */
public class SubProjectProgressBean implements Serializable {

    private String title;               // 进度标题
    private String time;                // 进度完成时间
    private boolean isFinished;         // 是否完成

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }
}
