package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/8 15:43
 * @Desc: 农民工资----通报
 */
public class NotifyBean implements Serializable {

    private NotifyTopBean top;
    private List<NotifyContentBean> list;

    public NotifyTopBean getTop() {
        return top;
    }

    public void setTop(NotifyTopBean top) {
        this.top = top;
    }

    public List<NotifyContentBean> getList() {
        return list;
    }

    public void setList(List<NotifyContentBean> list) {
        this.list = list;
    }
}
