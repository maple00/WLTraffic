package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:49
 * @Desc: 招投标----签订合同
 */
public class ContractBean implements Serializable {

    private String moneySix;        // 合同金额
    private String timeOneSix;      // 合同时间
    private List<AttachBean> ideaSixFile;       // 合同附件

    @Override
    public String toString() {
        return "ContractBean{" +
                "moneySix='" + moneySix + '\'' +
                ", timeOneSix='" + timeOneSix + '\'' +
                ", ideaSixFile=" + ideaSixFile +
                '}';
    }

    public String getMoneySix() {
        return moneySix;
    }

    public void setMoneySix(String moneySix) {
        this.moneySix = moneySix;
    }

    public String getTimeOneSix() {
        return timeOneSix;
    }

    public void setTimeOneSix(String timeOneSix) {
        this.timeOneSix = timeOneSix;
    }

    public List<AttachBean> getIdeaSixFile() {
        return ideaSixFile;
    }

    public void setIdeaSixFile(List<AttachBean> ideaSixFile) {
        this.ideaSixFile = ideaSixFile;
    }
}
