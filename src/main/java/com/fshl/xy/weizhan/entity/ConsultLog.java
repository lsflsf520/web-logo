package com.fshl.xy.weizhan.entity;

import java.util.Date;

import com.fshl.xy.weizhan.constant.Quality;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.bean.BaseEntity;

public class ConsultLog extends BaseEntity<Integer> {
    private Integer id;

    private Integer buid;

    private Integer siteId;

    private Integer wxUid;

    private String consuleInfo;

    private Quality quality;

    private String remark;

    private CommonStatus status;

    private Date createTime;

    private Date lastUptime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuid() {
        return buid;
    }

    public void setBuid(Integer buid) {
        this.buid = buid;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public Integer getWxUid() {
        return wxUid;
    }

    public void setWxUid(Integer wxUid) {
        this.wxUid = wxUid;
    }

    public String getConsuleInfo() {
        return consuleInfo;
    }

    public void setConsuleInfo(String consuleInfo) {
        this.consuleInfo = consuleInfo == null ? null : consuleInfo.trim();
    }

    public Quality getQuality() {
        return quality;
    }

    public void setQuality(Quality quality) {
        this.quality = quality;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public CommonStatus getStatus() {
        return status;
    }

    public void setStatus(CommonStatus status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUptime() {
        return lastUptime;
    }

    public void setLastUptime(Date lastUptime) {
        this.lastUptime = lastUptime;
    }

    @Override
    public Integer getPK() {
        return id;
    }
}