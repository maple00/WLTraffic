package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/28 16:16
 * @Desc: 质疑答疑、补漏
 */
public class SubQuestionsAndBareBean implements Serializable {

    private String title;       // 标题
    private List<SubQuestionBean> one;             // 质疑答疑
    private List<SubQuestionBean> two;             // 补漏

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<SubQuestionBean> getOne() {
        return one;
    }

    public void setOne(List<SubQuestionBean> one) {
        this.one = one;
    }

    public List<SubQuestionBean> getTwo() {
        return two;
    }

    public void setTwo(List<SubQuestionBean> two) {
        this.two = two;
    }
}
