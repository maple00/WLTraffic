package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/8 11:36
 * @Desc: 银行代发制度----年份信息
 */
public class SubBankYearInfo implements Serializable {

    private List<SubBankInfoYear> year;                 // 某年
    private List<SubBankInfoYearInfo> content;          //  某年的信息

    public List<SubBankInfoYear> getYear() {
        return year;
    }

    public void setYear(List<SubBankInfoYear> year) {
        this.year = year;
    }

    public List<SubBankInfoYearInfo> getContent() {
        return content;
    }

    public void setContent(List<SubBankInfoYearInfo> content) {
        this.content = content;
    }
}
