package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 17:59
 * @Desc: 项目建设程序
 */
public class ProjectProcedureBean implements Serializable {

    private String processName;           // 名称
    private String timeSeven;            // 时间
    private List<AttachBean> fileSevenFile;         // 附件
    private String stateSeven;        // 是否合格


    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getTimeSeven() {
        return timeSeven;
    }

    public void setTimeSeven(String timeSeven) {
        this.timeSeven = timeSeven;
    }

    public List<AttachBean> getFileSevenFile() {
        return fileSevenFile;
    }

    public void setFileSevenFile(List<AttachBean> fileSevenFile) {
        this.fileSevenFile = fileSevenFile;
    }

    public String getStateSeven() {
        return stateSeven;
    }

    public void setStateSeven(String stateSeven) {
        this.stateSeven = stateSeven;
    }
}
