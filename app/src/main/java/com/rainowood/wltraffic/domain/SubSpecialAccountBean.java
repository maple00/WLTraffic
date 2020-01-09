package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/7 19:48
 * @Desc: 农民工工资---专户制度
 */
public class SubSpecialAccountBean implements Serializable {

    private String id;
    private String type;                //类型
    private String state;               // 状态
    private String text;                // 备注
    private String updateTime;          // 更新时间
    private List<AttachBean> file;      // 附件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public List<AttachBean> getFile() {
        return file;
    }

    public void setFile(List<AttachBean> file) {
        this.file = file;
    }
}
