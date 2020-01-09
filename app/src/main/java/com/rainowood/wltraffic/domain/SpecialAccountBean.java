package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2019/12/27 18:36
 * @Desc: 专户制
 */
public class SpecialAccountBean implements Serializable {

   private SubSpecialAccountBean top;
   private List<SubSpecialAccountListBean> list;

    public SubSpecialAccountBean getTop() {
        return top;
    }

    public void setTop(SubSpecialAccountBean top) {
        this.top = top;
    }

    public List<SubSpecialAccountListBean> getList() {
        return list;
    }

    public void setList(List<SubSpecialAccountListBean> list) {
        this.list = list;
    }
}
