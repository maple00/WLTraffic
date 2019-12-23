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
    private List<SubItemWordBean>  planWordList;        // 计划文档书
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
    // 注释掉
    private String centralSubsidy;          // 中央补助
    private String municipalSubsidy;        // 市级补助
    private String bond;                    // 债券
    private String bankLoans;               // 银行贷款
    private String sectorInvestment;        // 部门投入
    private String raiseIndependently;      // 自筹
    private String others;                  // 其他


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

    public List<SubItemWordBean> getPlanWordList() {
        return planWordList;
    }

    public void setPlanWordList(List<SubItemWordBean> planWordList) {
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

    public String getCentralSubsidy() {
        return centralSubsidy;
    }

    public void setCentralSubsidy(String centralSubsidy) {
        this.centralSubsidy = centralSubsidy;
    }

    public String getMunicipalSubsidy() {
        return municipalSubsidy;
    }

    public void setMunicipalSubsidy(String municipalSubsidy) {
        this.municipalSubsidy = municipalSubsidy;
    }

    public String getBond() {
        return bond;
    }

    public void setBond(String bond) {
        this.bond = bond;
    }

    public String getBankLoans() {
        return bankLoans;
    }

    public void setBankLoans(String bankLoans) {
        this.bankLoans = bankLoans;
    }

    public String getSectorInvestment() {
        return sectorInvestment;
    }

    public void setSectorInvestment(String sectorInvestment) {
        this.sectorInvestment = sectorInvestment;
    }

    public String getRaiseIndependently() {
        return raiseIndependently;
    }

    public void setRaiseIndependently(String raiseIndependently) {
        this.raiseIndependently = raiseIndependently;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
