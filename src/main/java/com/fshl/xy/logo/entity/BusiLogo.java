package com.fshl.xy.logo.entity;

import com.yisi.stiku.common.bean.BaseEntity;
import com.yisi.stiku.common.utils.DateUtil;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class BusiLogo extends BaseEntity<Integer> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    @DateTimeFormat(pattern="yyyy-M-d")
    private Date createTime;

    private String company;

    private String customerAddr;
    
    private String userName;

    private String phone;

    private String logoName;

    private String logoTypes;

    private Integer num;

    private Integer logoFee;

    private Integer designFee;

    private Integer designProfit;

    private Integer bill;

    private Integer firstPayment;

    private Integer totalProfit;

    private Integer myFeeStatus;

    private Date myFeeTime;

    private Integer chenFeeStatus;

    private Date chenFeeTime;

    private String expressNum;

    private String wx;

    private Integer rapid;

    private Integer orderType;

    private Integer status;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreateTimeStr(){
    	return DateUtil.getDateStr(this.createTime);
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getCustomerAddr() {
		return customerAddr;
	}

	public void setCustomerAddr(String customerAddr) {
		this.customerAddr = customerAddr == null ? null : customerAddr.trim();
	}

	public String getLogoTypes() {
		return logoTypes;
	}

	public void setLogoTypes(String logoTypes) {
		this.logoTypes = logoTypes == null ? null : logoTypes.trim();
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName == null ? null : logoName.trim();
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getLogoFee() {
        return logoFee;
    }

    public void setLogoFee(Integer logoFee) {
        this.logoFee = logoFee;
    }

    public Integer getDesignFee() {
        return designFee;
    }

    public void setDesignFee(Integer designFee) {
        this.designFee = designFee;
    }

    public Integer getDesignProfit() {
        return designProfit;
    }

    public void setDesignProfit(Integer designProfit) {
        this.designProfit = designProfit;
    }

    public Integer getBill() {
        return bill;
    }

    public void setBill(Integer bill) {
        this.bill = bill;
    }

    public Integer getFirstPayment() {
        return firstPayment;
    }

    public void setFirstPayment(Integer firstPayment) {
        this.firstPayment = firstPayment;
    }

    public Integer getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Integer totalProfit) {
        this.totalProfit = totalProfit;
    }

    public Integer getMyFeeStatus() {
        return myFeeStatus;
    }

    public void setMyFeeStatus(Integer myFeeStatus) {
        this.myFeeStatus = myFeeStatus;
    }

    public Date getMyFeeTime() {
        return myFeeTime;
    }
    
    public String getMyFeeTimeStr(){
    	return myFeeTime == null ? null : DateUtil.getDateTimeStr(myFeeTime);
    }

    public void setMyFeeTime(Date myFeeTime) {
        this.myFeeTime = myFeeTime;
    }

    public Integer getChenFeeStatus() {
        return chenFeeStatus;
    }
    
    public void setChenFeeStatus(Integer chenFeeStatus) {
        this.chenFeeStatus = chenFeeStatus;
    }

    public Date getChenFeeTime() {
        return chenFeeTime;
    }
    
    public String getChenFeeTimeStr(){
    	return chenFeeTime == null ? null : DateUtil.getDateTimeStr(chenFeeTime);
    }

    public void setChenFeeTime(Date chenFeeTime) {
        this.chenFeeTime = chenFeeTime;
    }

    public String getExpressNum() {
        return expressNum;
    }

    public void setExpressNum(String expressNum) {
        this.expressNum = expressNum == null ? null : expressNum.trim();
    }

    public String getWx() {
        return wx;
    }

    public void setWx(String wx) {
        this.wx = wx == null ? null : wx.trim();
    }

    public Integer getRapid() {
        return rapid;
    }

    public void setRapid(Integer rapid) {
        this.rapid = rapid;
    }

    public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    @Override
    public Integer getPK() {
        return id;
    }
}