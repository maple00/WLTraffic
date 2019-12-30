package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/26 9:24
 * @Desc: 支付管理标签bean
 */
public class SubPayManagerBean implements Serializable {

    private String label;           // 标签
    private String lMoney;          // 标签的总金额,整数部分
    private boolean hasHide;         // 默认收起,
    private List<SubItemLabelBean> mList;        // 金额时间标签

    public boolean isHasHide() {
        return hasHide;
    }

    public void setHasHide(boolean hasHide) {
        this.hasHide = hasHide;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getlMoney() {
        return lMoney;
    }

    public void setlMoney(String lMoney) {
        this.lMoney = lMoney;
    }

    public List<SubItemLabelBean> getmList() {
        return mList;
    }

    public void setmList(List<SubItemLabelBean> mList) {
        this.mList = mList;
    }
}
