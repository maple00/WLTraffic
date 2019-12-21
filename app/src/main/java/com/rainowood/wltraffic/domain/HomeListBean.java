package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/20 15:28
 * @Desc: 首页列表bean
 */
public class HomeListBean implements Serializable {

    private String title;       // 项目名称
    private String label;       // 项目标签

    /*
     页面详情页数据
     */
    private String time;        //时间
    private String companyA;    // 甲方公司
    private String agentCompany;    // 代建公司
    private String investment;      // 总投资
    private String preInvestemt;        // 批准概算投资
    private String tokeInvestemt;       // 建安投资
    private String managementFee;       // 业主管理费

    /*
    计划情况, 任务来源
     */
    private String pageTitle;       // 页面标题
    private String editTitle;       // 后台编辑标题
    private String wordTitle;       // 文档标题

    /*
    主要建设内容，年度建设目标，备注
     */
    private String titleLabel;      // 页面标题
    private String content;         // 内容


    @Override
    public String toString() {
        return "HomeListBean{" +
                "title='" + title + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
