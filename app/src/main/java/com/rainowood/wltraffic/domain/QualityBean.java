package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/6 13:20
 * @Desc: 质量整改Bean
 */
public class QualityBean implements Serializable {

    private String id;
    private String state;           // 整改状态
    private String caseDescriptio;  // 整改描述

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCaseDescriptio() {
        return caseDescriptio;
    }

    public void setCaseDescriptio(String caseDescriptio) {
        this.caseDescriptio = caseDescriptio;
    }
}
