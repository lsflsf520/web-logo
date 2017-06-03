package com.fshl.xy.logo.entity;

import com.ujigu.secure.common.bean.BaseEntity;

public class Latest5dPrice extends BaseEntity<String> {
    private String code;

    private String name;

    private Integer openPrice;

    private Integer price;

    private Integer price1;

    private Integer price2;

    private Integer price3;

    private Integer price4;

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

    public Integer getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(Integer openPrice) {
        this.openPrice = openPrice;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPrice1() {
        return price1;
    }

    public void setPrice1(Integer price1) {
        this.price1 = price1;
    }

    public Integer getPrice2() {
        return price2;
    }

    public void setPrice2(Integer price2) {
        this.price2 = price2;
    }

    public Integer getPrice3() {
        return price3;
    }

    public void setPrice3(Integer price3) {
        this.price3 = price3;
    }

    public Integer getPrice4() {
        return price4;
    }

    public void setPrice4(Integer price4) {
        this.price4 = price4;
    }

    @Override
    public String getPK() {
        return code;
    }
}