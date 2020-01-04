package com.rainowood.wltraffic.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: a797s
 * @Date: 2020/1/4 17:30
 * @Desc: 变更内容Bean
 */
public class SubChangeBean implements Serializable {

    private String id;
    private String itemId;
    private String changeMatter;            // 变更内容
    private String changeMoney;             // 变更金额
    private String changeBasis;             // 变更依据
    private List<AttachBean> sceneFile;     // 附件--现场会议纪要
    private List<AttachBean> ownerFile;     // 附件--业务会议纪要
    private List<AttachBean> terraceFile;   // 附件--交通局会议纪要
    private String updateTime;              // 更新时间

    @Override
    public String toString() {
        return "SubChangeBean{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", changeMatter='" + changeMatter + '\'' +
                ", changeMoney='" + changeMoney + '\'' +
                ", changeBasis='" + changeBasis + '\'' +
                ", sceneFile=" + sceneFile +
                ", ownerFile=" + ownerFile +
                ", terraceFile=" + terraceFile +
                ", updateTime='" + updateTime + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getChangeMatter() {
        return changeMatter;
    }

    public void setChangeMatter(String changeMatter) {
        this.changeMatter = changeMatter;
    }

    public String getChangeMoney() {
        return changeMoney;
    }

    public void setChangeMoney(String changeMoney) {
        this.changeMoney = changeMoney;
    }

    public String getChangeBasis() {
        return changeBasis;
    }

    public void setChangeBasis(String changeBasis) {
        this.changeBasis = changeBasis;
    }

    public List<AttachBean> getSceneFile() {
        return sceneFile;
    }

    public void setSceneFile(List<AttachBean> sceneFile) {
        this.sceneFile = sceneFile;
    }

    public List<AttachBean> getOwnerFile() {
        return ownerFile;
    }

    public void setOwnerFile(List<AttachBean> ownerFile) {
        this.ownerFile = ownerFile;
    }

    public List<AttachBean> getTerraceFile() {
        return terraceFile;
    }

    public void setTerraceFile(List<AttachBean> terraceFile) {
        this.terraceFile = terraceFile;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
