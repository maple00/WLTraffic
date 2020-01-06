package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/6 11:11
 * @Desc: 支付管理数据Bean
 */
public class SubPayContentBean implements Serializable {

    private String id;
    private String payMoney;                    // 支付金额
    private String planMoney;                   // 计划金额
    private String plChangeMoney;               // 计划变更金额
    private String chsumMoney;                  // 变更后累计金额
    private String payTime;                     // 支付时间
    private String updateTime;                  // 更新时间
    // 如果有附件，则是业主的支付管理
    private List<AttachBean> fileTypeFile;      // 附件

    @Override
    public String toString() {
        return "SubPayContentBean{" +
                "id='" + id + '\'' +
                ", payMoney='" + payMoney + '\'' +
                ", planMoney='" + planMoney + '\'' +
                ", plChangeMoney='" + plChangeMoney + '\'' +
                ", chsumMoney='" + chsumMoney + '\'' +
                ", payTime='" + payTime + '\'' +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public List<AttachBean> getFileTypeFile() {
        return fileTypeFile;
    }

    public void setFileTypeFile(List<AttachBean> fileTypeFile) {
        this.fileTypeFile = fileTypeFile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getPlanMoney() {
        return planMoney;
    }

    public void setPlanMoney(String planMoney) {
        this.planMoney = planMoney;
    }

    public String getPlChangeMoney() {
        return plChangeMoney;
    }

    public void setPlChangeMoney(String plChangeMoney) {
        this.plChangeMoney = plChangeMoney;
    }

    public String getChsumMoney() {
        return chsumMoney;
    }

    public void setChsumMoney(String chsumMoney) {
        this.chsumMoney = chsumMoney;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
