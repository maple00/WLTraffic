package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/24 17:37
 * @Desc: 图片展示
 */
public class ImageBean implements Serializable {

    private String path;        // 图片路径

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
