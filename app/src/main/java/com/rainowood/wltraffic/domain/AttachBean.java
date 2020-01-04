package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/4 10:31
 * @Desc: 附件Bean
 */
public class AttachBean implements Serializable {

    private String name;           // 附件名称
    private String type;            // 附件的类型
    private String src;         // 附件的地址
    private String time;            // 附件的时间

    @Override
    public String toString() {
        return "AttachBean{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", src='" + src + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
