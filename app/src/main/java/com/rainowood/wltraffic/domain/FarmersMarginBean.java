package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/7 16:51
 * @Desc: 农民工工资保证金
 */
public class FarmersMarginBean implements Serializable {

    private SubMarginData content;
    private List<AttachBean> file;          // 附件列表


    public SubMarginData getContent() {
        return content;
    }

    public void setContent(SubMarginData content) {
        this.content = content;
    }

    public List<AttachBean> getFile() {
        return file;
    }

    public void setFile(List<AttachBean> file) {
        this.file = file;
    }
}
