package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/31 15:57
 * @Desc: 农民工工资银行代发制度
 */
public class BankRestBean implements Serializable {

    private String money;
    private String time;
    private List<LabelBean> mList;          // 文档列表

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<LabelBean> getmList() {
        return mList;
    }

    public void setmList(List<LabelBean> mList) {
        this.mList = mList;
    }
}
