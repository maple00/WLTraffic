package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/25 15:22
 * @Desc: 质量安全详情bean
 */
public class QualitySafeDetailBean implements Serializable {

    private String title;
    private String content;

    // 文档列表
    private List<AttachBean> mWordList;
    // 图片列表
    private List<String> mImgList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<AttachBean> getmWordList() {
        return mWordList;
    }

    public void setmWordList(List<AttachBean> mWordList) {
        this.mWordList = mWordList;
    }

    public List<String> getmImgList() {
        return mImgList;
    }

    public void setmImgList(List<String> mImgList) {
        this.mImgList = mImgList;
    }
}
