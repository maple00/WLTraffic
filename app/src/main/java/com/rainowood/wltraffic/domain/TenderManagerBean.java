package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2020/1/6 14:47
 * @Desc: 招投标管理
 */
public class TenderManagerBean implements Serializable {

    private SubTenderA a;               // 代理公司
    private SubCompanyAudit b;               // 公司审核
    private SubCompanyAudit c;               // 专家审核
    private SubRecordsBean d;               // 备案
    private SubHangingNet e;               // 挂网
    private SubQuestionsAndBareBean f;     // 质疑答疑、补漏
    private SubOpenBidBean g;               // 开标
    private SubPublicityBean h;               // 公示
    private ContractBean i;               // 合同签订

    @Override
    public String toString() {
        return "TenderManagerBean{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                ", d=" + d +
                ", e=" + e +
                ", f=" + f +
                ", g=" + g +
                ", h=" + h +
                ", i=" + i +
                '}';
    }

    public SubTenderA getA() {
        return a;
    }

    public void setA(SubTenderA a) {
        this.a = a;
    }

    public SubCompanyAudit getB() {
        return b;
    }

    public void setB(SubCompanyAudit b) {
        this.b = b;
    }

    public SubCompanyAudit getC() {
        return c;
    }

    public void setC(SubCompanyAudit c) {
        this.c = c;
    }

    public SubRecordsBean getD() {
        return d;
    }

    public void setD(SubRecordsBean d) {
        this.d = d;
    }

    public SubHangingNet getE() {
        return e;
    }

    public void setE(SubHangingNet e) {
        this.e = e;
    }

    public SubQuestionsAndBareBean getF() {
        return f;
    }

    public void setF(SubQuestionsAndBareBean f) {
        this.f = f;
    }

    public SubOpenBidBean getG() {
        return g;
    }

    public void setG(SubOpenBidBean g) {
        this.g = g;
    }

    public SubPublicityBean getH() {
        return h;
    }

    public void setH(SubPublicityBean h) {
        this.h = h;
    }

    public ContractBean getI() {
        return i;
    }

    public void setI(ContractBean i) {
        this.i = i;
    }
}
