package com.fshl.xy.logo.entity;


import java.util.Date;

import com.ujigu.secure.common.bean.BaseEntity;
import com.ujigu.secure.common.utils.DateUtil;

public class Zijin extends BaseEntity<Integer> {
    private Integer id;

    private String name;

    private String code;

    private String type;

    private Integer mainIn;

    private Integer mainOut;

    private Integer mainPureIn;

    private Integer personIn;

    private Integer personOut;

    private Integer personPureIn;

    private Integer buyBig;

    private Integer buySmall;

    private Integer sellBig;

    private Integer sellSmall;

    private Date day;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getMainIn() {
        return mainIn;
    }

    public void setMainIn(Integer mainIn) {
        this.mainIn = mainIn;
    }

    public Integer getMainOut() {
        return mainOut;
    }

    public void setMainOut(Integer mainOut) {
        this.mainOut = mainOut;
    }

    public Integer getMainPureIn() {
        return mainPureIn;
    }

    public void setMainPureIn(Integer mainPureIn) {
        this.mainPureIn = mainPureIn;
    }

    public Integer getPersonIn() {
        return personIn;
    }

    public void setPersonIn(Integer personIn) {
        this.personIn = personIn;
    }

    public Integer getPersonOut() {
        return personOut;
    }

    public void setPersonOut(Integer personOut) {
        this.personOut = personOut;
    }

    public Integer getPersonPureIn() {
        return personPureIn;
    }

    public void setPersonPureIn(Integer personPureIn) {
        this.personPureIn = personPureIn;
    }

    public Integer getBuyBig() {
        return buyBig;
    }

    public void setBuyBig(Integer buyBig) {
        this.buyBig = buyBig;
    }

    public Integer getBuySmall() {
        return buySmall;
    }

    public void setBuySmall(Integer buySmall) {
        this.buySmall = buySmall;
    }

    public Integer getSellBig() {
        return sellBig;
    }

    public void setSellBig(Integer sellBig) {
        this.sellBig = sellBig;
    }

    public Integer getSellSmall() {
        return sellSmall;
    }

    public void setSellSmall(Integer sellSmall) {
        this.sellSmall = sellSmall;
    }

    public Date getDay() {
        return day;
    }
    
    public String getDayStr(){
    	return DateUtil.getDateStr(day);
    }

    public void setDay(Date day) {
        this.day = day;
    }

    @Override
    public Integer getPK() {
        return id;
    }
}