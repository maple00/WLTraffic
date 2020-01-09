package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/8 11:31
 * @Desc: 银行代发制度头部信息
 */
public class SubBankTop implements Serializable {

    private String id;
    private String state;           // 状态
    private String text;            // 备注
    private List<AttachBean> file;      // 附件

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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AttachBean> getFile() {
        return file;
    }

    public void setFile(List<AttachBean> file) {
        this.file = file;
    }
}
