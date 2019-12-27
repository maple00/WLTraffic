package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/27 17:50
 * @Desc: 劳资Bean
 */
public class LaborBean implements Serializable {

    private String name;
    private String company;
    private String tel;
    private String note;
    private String time;
    private boolean hasHide;        // 是否收起,默认收起

    public boolean isHasHide() {
        return hasHide;
    }

    public void setHasHide(boolean hasHide) {
        this.hasHide = hasHide;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
