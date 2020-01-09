package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/8 15:46
 * @Desc: 通报------content
 */
public class NotifyContentBean implements Serializable {

    private String id;
    private String matter;          // 主要内容
    private String changeTime;          // 整改时限
    private String changeState;         // 状态
    private String tel;                 // 电话
    private String text;                // 备注
    private String changeHave;          // 已整改的内容
    private String changeIng;           // 整改中的内容
    private String changeNot;           // 未整改的内容
    private String updateTime;          // 更新时间

    // 附件
    private List<AttachBean> tongFile;          // 交通局附件
    private List<AttachBean> changeHaveFile;       // 已整改的附件
    private List<AttachBean> changeIngFile;         // 整改中的附件
    private List<AttachBean> changeNotFile;         // 未整改的附件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatter() {
        return matter;
    }

    public void setMatter(String matter) {
        this.matter = matter;
    }

    public String getChangeTime() {
        return changeTime;
    }

    public void setChangeTime(String changeTime) {
        this.changeTime = changeTime;
    }

    public String getChangeState() {
        return changeState;
    }

    public void setChangeState(String changeState) {
        this.changeState = changeState;
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

    public String getChangeHave() {
        return changeHave;
    }

    public void setChangeHave(String changeHave) {
        this.changeHave = changeHave;
    }

    public String getChangeIng() {
        return changeIng;
    }

    public void setChangeIng(String changeIng) {
        this.changeIng = changeIng;
    }

    public String getChangeNot() {
        return changeNot;
    }

    public void setChangeNot(String changeNot) {
        this.changeNot = changeNot;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<AttachBean> getTongFile() {
        return tongFile;
    }

    public void setTongFile(List<AttachBean> tongFile) {
        this.tongFile = tongFile;
    }

    public List<AttachBean> getChangeHaveFile() {
        return changeHaveFile;
    }

    public void setChangeHaveFile(List<AttachBean> changeHaveFile) {
        this.changeHaveFile = changeHaveFile;
    }

    public List<AttachBean> getChangeIngFile() {
        return changeIngFile;
    }

    public void setChangeIngFile(List<AttachBean> changeIngFile) {
        this.changeIngFile = changeIngFile;
    }

    public List<AttachBean> getChangeNotFile() {
        return changeNotFile;
    }

    public void setChangeNotFile(List<AttachBean> changeNotFile) {
        this.changeNotFile = changeNotFile;
    }
}
