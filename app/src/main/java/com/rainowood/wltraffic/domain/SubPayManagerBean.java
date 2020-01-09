package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/26 9:24
 * @Desc: 支付管理标签bean
 */
public class SubPayManagerBean implements Serializable {

    private String teamName;           // 标签
    private String teamChildMoney;          // 标签的总金额,整数部分
    private boolean hasHide;         // 默认收起,
    private List<SubPayContentBean> teamChildArr;        // 金额时间标签

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamChildMoney() {
        return teamChildMoney;
    }

    public void setTeamChildMoney(String teamChildMoney) {
        this.teamChildMoney = teamChildMoney;
    }

    public boolean isHasHide() {
        return hasHide;
    }

    public void setHasHide(boolean hasHide) {
        this.hasHide = hasHide;
    }

    public List<SubPayContentBean> getTeamChildArr() {
        return teamChildArr;
    }

    public void setTeamChildArr(List<SubPayContentBean> teamChildArr) {
        this.teamChildArr = teamChildArr;
    }
}
