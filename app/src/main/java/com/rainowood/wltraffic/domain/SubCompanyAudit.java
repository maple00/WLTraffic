package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:06
 * @Desc: 公司审核，专家审核
 */
public class SubCompanyAudit implements Serializable {

    private String id;
    private String peopleSix;       // 审核人
    private String timeOneSix;      // 审核时间
    private String ideaSix;         // 审核内容
    private List<AttachBean> ideaSixFile;       // 附件

    @Override
    public String toString() {
        return "SubCompanyAudit{" +
                "id='" + id + '\'' +
                ", peopleSix='" + peopleSix + '\'' +
                ", timeOneSix='" + timeOneSix + '\'' +
                ", ideaSix='" + ideaSix + '\'' +
                ", ideaSixFile=" + ideaSixFile +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPeopleSix() {
        return peopleSix;
    }

    public void setPeopleSix(String peopleSix) {
        this.peopleSix = peopleSix;
    }

    public String getTimeOneSix() {
        return timeOneSix;
    }

    public void setTimeOneSix(String timeOneSix) {
        this.timeOneSix = timeOneSix;
    }

    public String getIdeaSix() {
        return ideaSix;
    }

    public void setIdeaSix(String ideaSix) {
        this.ideaSix = ideaSix;
    }

    public List<AttachBean> getIdeaSixFile() {
        return ideaSixFile;
    }

    public void setIdeaSixFile(List<AttachBean> ideaSixFile) {
        this.ideaSixFile = ideaSixFile;
    }
}
