package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/26 17:24
 * @Desc: 扣分明细
 */
public class AssessDeductionBean implements Serializable {

    private String department;          // 课时部门
    private String pointMx;             // 扣分项
    private String point;               // 分数
    private String text;               // 备注
    private String updateTime;          // 更新时间
    private String time;                // 时间

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPointMx() {
        return pointMx;
    }

    public void setPointMx(String pointMx) {
        this.pointMx = pointMx;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
