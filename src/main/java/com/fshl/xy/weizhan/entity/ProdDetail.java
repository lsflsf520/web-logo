package com.fshl.xy.weizhan.entity;

import com.xyz.tools.db.bean.BaseEntity;

public class ProdDetail extends BaseEntity<Integer> {
    private Integer id;

    private Integer buid;

    private Integer prodId;

    private String detail;

    private String servFlow;

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

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getServFlow() {
        return servFlow;
    }

    public void setServFlow(String servFlow) {
        this.servFlow = servFlow == null ? null : servFlow.trim();
    }

    @Override
    public Integer getPK() {
        return id;
    }
}