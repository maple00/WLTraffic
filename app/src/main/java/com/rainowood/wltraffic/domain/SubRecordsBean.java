package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:33
 * @Desc: 招投标---备案
 */
public class SubRecordsBean implements Serializable {

    private String id;
    private String timeOneSix;          // 政务备案时间
    private String timeTwoSix;          // 交通局备案时间
    private String ideaSix;             // 备案内容
    private List<AttachBean> ideaSixFile;   // 备案附件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTimeOneSix() {
        return timeOneSix;
    }

    public void setTimeOneSix(String timeOneSix) {
        this.timeOneSix = timeOneSix;
    }

    public String getTimeTwoSix() {
        return timeTwoSix;
    }

    public void setTimeTwoSix(String timeTwoSix) {
        this.timeTwoSix = timeTwoSix;
    }

    public String getIdeaSix() {
        return ideaSix;
    }

    public void setIdeaSix(String ideaSix) {
        this.ideaSix = ideaSix;
    }

    public List<AttachBean> getIdeaSixFile() {
        return ideaSixFile;
    }

    public void setIdeaSixFile(List<AttachBean> ideaSixFile) {
        this.ideaSixFile = ideaSixFile;
    }
}
