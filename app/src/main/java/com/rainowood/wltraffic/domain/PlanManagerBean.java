package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/23 13:19
 * @Desc: 计划管理
 */
public class PlanManagerBean implements Serializable {

    /*
    前期通知书
     */
    private String itemName;        // 项目名称
    private String startAdr;        // 开始位置
    private String endAdr;          // 结束位置
    private String constructionScale;       // 建设规模

    /*
    计划下达
     */
    private String planNo;                      // 计划下达title
    private List<AttachBean>  planWordList;        // 计划文档书
    private String constructionContent;     // 建设内容
    private String investmentScale;         // 投资规模

    /*
    资金来源 单位（万元）
     */
    private String totalInvestment;         // 总投资
    private String jianAnInvestment;        // 建安投资
    private String gapInvestment;           // 缺口投资
    private String striveInvesment;         // 已争取的投资
    private String simulationSriveInvesment;    // 拟争取投资


    public String getPlanNo() {
        return planNo;
    }

    public void setPlanNo(String planNo) {
        this.planNo = planNo;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getStartAdr() {
        return startAdr;
    }

    public void setStartAdr(String startAdr) {
        this.startAdr = startAdr;
    }

    public String getEndAdr() {
        return endAdr;
    }

    public void setEndAdr(String endAdr) {
        this.endAdr = endAdr;
    }

    public String getConstructionScale() {
        return constructionScale;
    }

    public void setConstructionScale(String constructionScale) {
        this.constructionScale = constructionScale;
    }

    public List<AttachBean> getPlanWordList() {
        return planWordList;
    }

    public void setPlanWordList(List<AttachBean> planWordList) {
        this.planWordList = planWordList;
    }

    public String getConstructionContent() {
        return constructionContent;
    }

    public void setConstructionContent(String constructionContent) {
        this.constructionContent = constructionContent;
    }

    public String getInvestmentScale() {
        return investmentScale;
    }

    public void setInvestmentScale(String investmentScale) {
        this.investmentScale = investmentScale;
    }

    public String getTotalInvestment() {
        return totalInvestment;
    }

    public void setTotalInvestment(String totalInvestment) {
        this.totalInvestment = totalInvestment;
    }

    public String getJianAnInvestment() {
        return jianAnInvestment;
    }

    public void setJianAnInvestment(String jianAnInvestment) {
        this.jianAnInvestment = jianAnInvestment;
    }

    public String getGapInvestment() {
        return gapInvestment;
    }

    public void setGapInvestment(String gapInvestment) {
        this.gapInvestment = gapInvestment;
    }

    public String getStriveInvesment() {
        return striveInvesment;
    }

    public void setStriveInvesment(String striveInvesment) {
        this.striveInvesment = striveInvesment;
    }

    public String getSimulationSriveInvesment() {
        return simulationSriveInvesment;
    }

    public void setSimulationSriveInvesment(String simulationSriveInvesment) {
        this.simulationSriveInvesment = simulationSriveInvesment;
    }
}
