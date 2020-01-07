package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/7 10:44
 * @Desc: 整改内容Bean
 */
public class RectificationBean implements Serializable {

    private String caseDescriptio;          // 描述
    private List<AttachBean> caseDescriptioFile;      // 描述文件
    private String modifyHave;              // 已整改描述
    private List<AttachBean> haveFile;      // 已整改文件
    private String modifyIn;                // 整改中描述
    private List<AttachBean> inFile;        // 整改中文件
    private String modifyNot;               // 未整改描述
    private List<AttachBean> notFile;       // 未整改文件

    public String getCaseDescriptio() {
        return caseDescriptio;
    }

    public void setCaseDescriptio(String caseDescriptio) {
        this.caseDescriptio = caseDescriptio;
    }

    public List<AttachBean> getCaseDescriptioFile() {
        return caseDescriptioFile;
    }

    public void setCaseDescriptioFile(List<AttachBean> caseDescriptioFile) {
        this.caseDescriptioFile = caseDescriptioFile;
    }

    public String getModifyHave() {
        return modifyHave;
    }

    public void setModifyHave(String modifyHave) {
        this.modifyHave = modifyHave;
    }

    public List<AttachBean> getHaveFile() {
        return haveFile;
    }

    public void setHaveFile(List<AttachBean> haveFile) {
        this.haveFile = haveFile;
    }

    public String getModifyIn() {
        return modifyIn;
    }

    public void setModifyIn(String modifyIn) {
        this.modifyIn = modifyIn;
    }

    public List<AttachBean> getInFile() {
        return inFile;
    }

    public void setInFile(List<AttachBean> inFile) {
        this.inFile = inFile;
    }

    public String getModifyNot() {
        return modifyNot;
    }

    public void setModifyNot(String modifyNot) {
        this.modifyNot = modifyNot;
    }

    public List<AttachBean> getNotFile() {
        return notFile;
    }

    public void setNotFile(List<AttachBean> notFile) {
        this.notFile = notFile;
    }
}
