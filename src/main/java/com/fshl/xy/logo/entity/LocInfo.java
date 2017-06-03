package com.fshl.xy.logo.entity;

import com.ujigu.secure.common.bean.BaseEntity;

public class LocInfo extends BaseEntity<Integer> {
    private Integer id;

    private String city;
    
    private String name;

    private Integer lng;

    private Integer lat;

    private Integer baseType;

    private String defImg;

    private String detailUri;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLng() {
        return lng;
    }

    public void setLng(Integer lng) {
        this.lng = lng;
    }

    public Integer getLat() {
        return lat;
    }

    public void setLat(Integer lat) {
        this.lat = lat;
    }

    public Integer getBaseType() {
        return baseType;
    }

    public void setBaseType(Integer baseType) {
        this.baseType = baseType;
    }

    public String getDefImg() {
        return defImg;
    }

    public void setDefImg(String defImg) {
        this.defImg = defImg == null ? null : defImg.trim();
    }

    public String getDetailUri() {
        return detailUri;
    }

    public void setDetailUri(String detailUri) {
        this.detailUri = detailUri == null ? null : detailUri.trim();
    }

    @Override
    public Integer getPK() {
        return id;
    }
}