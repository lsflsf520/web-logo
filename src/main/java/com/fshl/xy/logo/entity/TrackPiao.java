package com.fshl.xy.logo.entity;

import com.yisi.stiku.common.bean.BaseEntity;
import java.util.Date;

public class TrackPiao extends BaseEntity<String> {
    private String code;

    private String name;

    private Date createDay;

    private Date trackDay;

    private Integer trackNum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateDay() {
        return createDay;
    }

    public void setCreateDay(Date createDay) {
        this.createDay = createDay;
    }

    public Date getTrackDay() {
        return trackDay;
    }

    public void setTrackDay(Date trackDay) {
        this.trackDay = trackDay;
    }

    public Integer getTrackNum() {
        return trackNum;
    }

    public void setTrackNum(Integer trackNum) {
        this.trackNum = trackNum;
    }

    @Override
    public String getPK() {
        return code;
    }
}