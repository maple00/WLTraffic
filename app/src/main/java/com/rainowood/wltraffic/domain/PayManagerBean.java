package com.rainowood.wltraffic.domain;

import java.io.Serializable;

/**
 * @Author: a797s
 * @Date: 2019/12/25 18:04
 * @Desc: 支付管理
 */
public class PayManagerBean implements Serializable {

    private String totalMoneyInt;      // 累计的总单位    --- 整数部分
    private String totalMoneyFloat;      // 累计的总单位  -- 小数部分

    @Override
    public String toString() {
        return "PayManagerBean{" +
                "totalMoneyInt='" + totalMoneyInt + '\'' +
                ", totalMoneyFloat='" + totalMoneyFloat + '\'' +
                '}';
    }

    public String getTotalMoneyInt() {
        return totalMoneyInt;
    }

    public void setTotalMoneyInt(String totalMoneyInt) {
        this.totalMoneyInt = totalMoneyInt;
    }

    public String getTotalMoneyFloat() {
        return totalMoneyFloat;
    }

    public void setTotalMoneyFloat(String totalMoneyFloat) {
        this.totalMoneyFloat = totalMoneyFloat;
    }

}
