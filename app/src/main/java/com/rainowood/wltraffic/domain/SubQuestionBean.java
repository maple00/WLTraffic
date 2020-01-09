package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/6 15:39
 * @Desc: 质疑答疑Bean
 */
public class SubQuestionBean implements Serializable {

    private String id;
    private String problem;         // 问题
    private String answer;          // 回复
    private String text;            // 质疑答疑备注
    private List<AttachBean> problemFile;       //质疑附件
    private List<AttachBean> answerFile;        // 答复附件

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AttachBean> getProblemFile() {
        return problemFile;
    }

    public void setProblemFile(List<AttachBean> problemFile) {
        this.problemFile = problemFile;
    }

    public List<AttachBean> getAnswerFile() {
        return answerFile;
    }

    public void setAnswerFile(List<AttachBean> answerFile) {
        this.answerFile = answerFile;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
