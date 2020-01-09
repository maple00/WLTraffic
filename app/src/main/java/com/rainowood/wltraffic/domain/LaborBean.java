package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/27 17:50
 * @Desc: 劳资Bean
 */
public class LaborBean implements Serializable {

    private String id;
    private String monad;               // 单位
    private String name;                // 姓名
    private String duty;                // 职务
    private String tel;                 // 联系方式
    private String text;                // 备注
    private String updateTime;          // 更新时间
    private boolean hasHide;        // 是否收起,默认收起

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMonad() {
        return monad;
    }

    public void setMonad(String monad) {
        this.monad = monad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public boolean isHasHide() {
        return hasHide;
    }

    public void setHasHide(boolean hasHide) {
        this.hasHide = hasHide;
    }
}
