package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/7 10:16
 * @Desc: 质量管理---外业检测&鉴定意见
 */
public class SubQualityTestBean implements Serializable {

    private String id;
    private String detectionTime;               // 申请时间
    private String detectionMatter;             // 外业检测内容
    private List<AttachBean> detectionFile;     // 外业检测附件
    private String qsOpinion;                   // 质量鉴定意见
    private List<AttachBean> qsOpinionFile;     // 鉴定附件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetectionTime() {
        return detectionTime;
    }

    public void setDetectionTime(String detectionTime) {
        this.detectionTime = detectionTime;
    }

    public String getDetectionMatter() {
        return detectionMatter;
    }

    public void setDetectionMatter(String detectionMatter) {
        this.detectionMatter = detectionMatter;
    }

    public List<AttachBean> getDetectionFile() {
        return detectionFile;
    }

    public void setDetectionFile(List<AttachBean> detectionFile) {
        this.detectionFile = detectionFile;
    }

    public String getQsOpinion() {
        return qsOpinion;
    }

    public void setQsOpinion(String qsOpinion) {
        this.qsOpinion = qsOpinion;
    }

    public List<AttachBean> getQsOpinionFile() {
        return qsOpinionFile;
    }

    public void setQsOpinionFile(List<AttachBean> qsOpinionFile) {
        this.qsOpinionFile = qsOpinionFile;
    }
}
