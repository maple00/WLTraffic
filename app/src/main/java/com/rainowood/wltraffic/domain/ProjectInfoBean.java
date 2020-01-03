package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/20 15:28
 * @Desc: 首页列表bean
 */
public class ProjectInfoBean implements Serializable {

    private String id;            // id
    private String itemName;        // 项目名称
    private String proportion;      // 项目进度
    private String stage;           // 项目阶段

    @Override
    public String toString() {
        return "ProjectInfoBean{" +
                "id='" + id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", proportion='" + proportion + '\'' +
                ", stage='" + stage + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getProportion() {
        return proportion;
    }

    public void setProportion(String proportion) {
        this.proportion = proportion;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }
}
