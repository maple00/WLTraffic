package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/21 16:34
 * @Desc: 用户个人信息
 */
public class UserInfoBean implements Serializable {

    private String adid;                // 用户ID
    private String type;                // 用户类型
    private String adName;              // 姓名
    private String account;             // 用户名
    private String password;            // 密码
    private String sex;                 // 性别
    private String departmentName;       // 职位
    private String address;             // 公司地址
    private String adtel;               // 电话
    private String touxiang;             // logo地址
    private String companyName;         // 公司

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "adid='" + adid + '\'' +
                ", type='" + type + '\'' +
                ", adName='" + adName + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", address='" + address + '\'' +
                ", adtel='" + adtel + '\'' +
                ", touxiang='" + touxiang + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdid() {
        return adid;
    }

    public void setAdid(String adid) {
        this.adid = adid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdtel() {
        return adtel;
    }

    public void setAdtel(String adtel) {
        this.adtel = adtel;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
