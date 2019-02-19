package com.fshl.xy.weizhan.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.xyz.tools.common.constant.Bool;
import com.xyz.tools.common.utils.StringUtil;
import com.xyz.tools.db.bean.BaseEntity;

public class SiteInfo extends BaseEntity<Integer> {
    private Integer id;

    private Integer buid;

    private String name;

    private String shortName;
    
    private String logo;
    
    private String domain;

    private String contactName;

    private String contactPhone;

    private String contactAddr;
    
    private Bool noAd;

    private String wxQrcode;

    private String qq;

//    private String banner;
    private String[] banners;

    private Date createTime;

    private Date lastUptime;

    private String remark;

    private String title;

    private String keyword;

    private String description;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName == null ? null : shortName.trim();
    }
    
    public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr == null ? null : contactAddr.trim();
    }
    
    public Bool getNoAd() {
        return noAd;
    }

    public void setNoAd(Bool noAd) {
        this.noAd = noAd;
    }

    public String getWxQrcode() {
        return wxQrcode;
    }

    public void setWxQrcode(String wxQrcode) {
        this.wxQrcode = wxQrcode == null ? null : wxQrcode.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getBanner() {
//        return banner;
    	return StringUtils.join(this.banners, ",");
    }

    public void setBanner(String banner) {
//        this.banner = banner == null ? null : banner.trim();
    	if(StringUtils.isNotBlank(banner)) {
    		this.banners = StringUtil.toSet(banner).toArray(new String[0]);
    	}
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    @Override
    public Integer getPK() {
        return id;
    }
    
    public String[] getBanners() {
    	return this.banners;
    }
    
    public void setBanners(String[] banners) {
    	this.banners = banners;
    }
    
    public String getFirstBanner() {
    	return this.banners == null || this.banners.length <= 0 ? null : this.banners[0];
    }
    
    public String getLastBanner() {
    	return this.banners == null || this.banners.length <= 0 ? null : this.banners[this.banners.length - 1];
    }
}