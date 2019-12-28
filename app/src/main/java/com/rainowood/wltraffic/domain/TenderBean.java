package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/28 16:16
 * @Desc: 质疑答疑、补漏
 */
public class TenderBean implements Serializable {

    private String title;       // 标题
    private List<SubTenderBean> mList;      // 子项

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubTenderBean> getmList() {
        return mList;
    }

    public void setmList(List<SubTenderBean> mList) {
        this.mList = mList;
    }
}
