package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/8 17:50
 * @Desc: 消息Bean
 */
public class MessageBean implements Serializable {

    private List<SubMsgBean> backlog;           // 待办事项
    private List<SubMsgBean> message;           // 通知公告

    public List<SubMsgBean> getBacklog() {
        return backlog;
    }

    public void setBacklog(List<SubMsgBean> backlog) {
        this.backlog = backlog;
    }

    public List<SubMsgBean> getMessage() {
        return message;
    }

    public void setMessage(List<SubMsgBean> message) {
        this.message = message;
    }
}
