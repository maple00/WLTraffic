package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:47
 * @Desc: 公示
 */
public class SubPublicityBean implements Serializable {

    private String timeOneSix;          // 开始时间
    private String timeTwoSix;          // 结束时间
    private String complainSix;         // 是否有投诉

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

    public String getComplainSix() {
        return complainSix;
    }

    public void setComplainSix(String complainSix) {
        this.complainSix = complainSix;
    }
}
