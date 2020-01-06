package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:35
 * @Desc: 招投标-挂网
 */
public class SubHangingNet implements Serializable {

    private String timeOneSix;              // 挂网时间
    private String timeTwoSix;              // 预计开标时间

    @Override
    public String toString() {
        return "SubHangingNet{" +
                "timeOneSix='" + timeOneSix + '\'' +
                ", timeTwoSix='" + timeTwoSix + '\'' +
                '}';
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
}
