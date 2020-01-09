package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/8 11:29
 * @Desc: 农民工工资管理------银行代发制度Bean
 */
public class FarmersBankBean implements Serializable {

    private SubBankTop top;          //头部信息
    private List<SubSpecialAccount> list1;              // 转入专户资金
    private SubBankYearInfo list2;                      // 每月产值表
    private SubBankYearInfo list3;                      // 农民工工资表

    public SubBankTop getTop() {
        return top;
    }

    public void setTop(SubBankTop top) {
        this.top = top;
    }

    public List<SubSpecialAccount> getList1() {
        return list1;
    }

    public void setList1(List<SubSpecialAccount> list1) {
        this.list1 = list1;
    }

    public SubBankYearInfo getList2() {
        return list2;
    }

    public void setList2(SubBankYearInfo list2) {
        this.list2 = list2;
    }

    public SubBankYearInfo getList3() {
        return list3;
    }

    public void setList3(SubBankYearInfo list3) {
        this.list3 = list3;
    }
}
