package com.fshl.xy.logo.entity;

//
//public class Gupiao extends BaseEntity<Integer> {
//    private Integer id;
//
//    private String name;
//
//    private String code;
//
//    private String type;
//
//    private Integer currPrice;
//
//    private Integer yesterPrice;
//
//    private Integer openPrice;
//
//    private Integer volume;
//
//    private Integer outerVolume;
//
//    private Integer innerVolume;
//
//    private Integer buyOnePrice;
//
//    private Integer buyOneVolume;
//
//    private Integer buyTwoPrice;
//
//    private Integer buyTwoVolume;
//
//    private Integer buyThreePrice;
//
//    private Integer buyThreeVolume;
//
//    private Integer buyFourPrice;
//
//    private Integer buyFourVolume;
//
//    private Integer buyFivePrice;
//
//    private Integer buyFiveVolume;
//
//    private Integer sellOnePrice;
//
//    private Integer sellOneVolume;
//
//    private Integer sellTwoPrice;
//
//    private Integer sellTwoVolume;
//
//    private Integer sellThreePrice;
//
//    private Integer sellThreeVolume;
//
//    private Integer sellFourPrice;
//
//    private Integer sellFourVolume;
//
//    private Integer sellFivePrice;
//
//    private Integer sellFiveVolume;
//
//    private String latestDeal;
//
//    private Integer incAmount;
//
//    private Integer incPercent;
//
//    private Integer maxPrice;
//
//    private Integer minPrice;
//
//    private String priceVolume;
//
//    private Integer money;
//
//    private Integer changePercent;
//
//    private Integer peRatio;
//
//    private String remark;
//
//    private Integer swing;
//
//    private Integer marketValue;
//
//    private Integer totalValue;
//
//    private Integer marketPercent;
//
//    private Integer volumeRatio;
//
//    private Date day;
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name == null ? null : name.trim();
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code == null ? null : code.trim();
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type == null ? null : type.trim();
//    }
//
//    public Integer getCurrPrice() {
//        return currPrice;
//    }
//
//    public void setCurrPrice(Integer currPrice) {
//        this.currPrice = currPrice;
//    }
//
//    public Integer getYesterPrice() {
//        return yesterPrice;
//    }
//
//    public void setYesterPrice(Integer yesterPrice) {
//        this.yesterPrice = yesterPrice;
//    }
//
//    public Integer getOpenPrice() {
//        return openPrice;
//    }
//
//    public void setOpenPrice(Integer openPrice) {
//        this.openPrice = openPrice;
//    }
//
//    public Integer getVolume() {
//        return volume;
//    }
//
//    public void setVolume(Integer volume) {
//        this.volume = volume;
//    }
//
//    public Integer getOuterVolume() {
//        return outerVolume;
//    }
//
//    public void setOuterVolume(Integer outerVolume) {
//        this.outerVolume = outerVolume;
//    }
//
//    public Integer getInnerVolume() {
//        return innerVolume;
//    }
//
//    public void setInnerVolume(Integer innerVolume) {
//        this.innerVolume = innerVolume;
//    }
//
//    public Integer getBuyOnePrice() {
//        return buyOnePrice;
//    }
//
//    public void setBuyOnePrice(Integer buyOnePrice) {
//        this.buyOnePrice = buyOnePrice;
//    }
//
//    public Integer getBuyOneVolume() {
//        return buyOneVolume;
//    }
//
//    public void setBuyOneVolume(Integer buyOneVolume) {
//        this.buyOneVolume = buyOneVolume;
//    }
//
//    public Integer getBuyTwoPrice() {
//        return buyTwoPrice;
//    }
//
//    public void setBuyTwoPrice(Integer buyTwoPrice) {
//        this.buyTwoPrice = buyTwoPrice;
//    }
//
//    public Integer getBuyTwoVolume() {
//        return buyTwoVolume;
//    }
//
//    public void setBuyTwoVolume(Integer buyTwoVolume) {
//        this.buyTwoVolume = buyTwoVolume;
//    }
//
//    public Integer getBuyThreePrice() {
//        return buyThreePrice;
//    }
//
//    public void setBuyThreePrice(Integer buyThreePrice) {
//        this.buyThreePrice = buyThreePrice;
//    }
//
//    public Integer getBuyThreeVolume() {
//        return buyThreeVolume;
//    }
//
//    public void setBuyThreeVolume(Integer buyThreeVolume) {
//        this.buyThreeVolume = buyThreeVolume;
//    }
//
//    public Integer getBuyFourPrice() {
//        return buyFourPrice;
//    }
//
//    public void setBuyFourPrice(Integer buyFourPrice) {
//        this.buyFourPrice = buyFourPrice;
//    }
//
//    public Integer getBuyFourVolume() {
//        return buyFourVolume;
//    }
//
//    public void setBuyFourVolume(Integer buyFourVolume) {
//        this.buyFourVolume = buyFourVolume;
//    }
//
//    public Integer getBuyFivePrice() {
//        return buyFivePrice;
//    }
//
//    public void setBuyFivePrice(Integer buyFivePrice) {
//        this.buyFivePrice = buyFivePrice;
//    }
//
//    public Integer getBuyFiveVolume() {
//        return buyFiveVolume;
//    }
//
//    public void setBuyFiveVolume(Integer buyFiveVolume) {
//        this.buyFiveVolume = buyFiveVolume;
//    }
//
//    public Integer getSellOnePrice() {
//        return sellOnePrice;
//    }
//
//    public void setSellOnePrice(Integer sellOnePrice) {
//        this.sellOnePrice = sellOnePrice;
//    }
//
//    public Integer getSellOneVolume() {
//        return sellOneVolume;
//    }
//
//    public void setSellOneVolume(Integer sellOneVolume) {
//        this.sellOneVolume = sellOneVolume;
//    }
//
//    public Integer getSellTwoPrice() {
//        return sellTwoPrice;
//    }
//
//    public void setSellTwoPrice(Integer sellTwoPrice) {
//        this.sellTwoPrice = sellTwoPrice;
//    }
//
//    public Integer getSellTwoVolume() {
//        return sellTwoVolume;
//    }
//
//    public void setSellTwoVolume(Integer sellTwoVolume) {
//        this.sellTwoVolume = sellTwoVolume;
//    }
//
//    public Integer getSellThreePrice() {
//        return sellThreePrice;
//    }
//
//    public void setSellThreePrice(Integer sellThreePrice) {
//        this.sellThreePrice = sellThreePrice;
//    }
//
//    public Integer getSellThreeVolume() {
//        return sellThreeVolume;
//    }
//
//    public void setSellThreeVolume(Integer sellThreeVolume) {
//        this.sellThreeVolume = sellThreeVolume;
//    }
//
//    public Integer getSellFourPrice() {
//        return sellFourPrice;
//    }
//
//    public void setSellFourPrice(Integer sellFourPrice) {
//        this.sellFourPrice = sellFourPrice;
//    }
//
//    public Integer getSellFourVolume() {
//        return sellFourVolume;
//    }
//
//    public void setSellFourVolume(Integer sellFourVolume) {
//        this.sellFourVolume = sellFourVolume;
//    }
//
//    public Integer getSellFivePrice() {
//        return sellFivePrice;
//    }
//
//    public void setSellFivePrice(Integer sellFivePrice) {
//        this.sellFivePrice = sellFivePrice;
//    }
//
//    public Integer getSellFiveVolume() {
//        return sellFiveVolume;
//    }
//
//    public void setSellFiveVolume(Integer sellFiveVolume) {
//        this.sellFiveVolume = sellFiveVolume;
//    }
//
//    public String getLatestDeal() {
//        return latestDeal;
//    }
//
//    public void setLatestDeal(String latestDeal) {
//        this.latestDeal = latestDeal == null ? null : latestDeal.trim();
//    }
//
//    public Integer getIncAmount() {
//        return incAmount;
//    }
//
//    public void setIncAmount(Integer incAmount) {
//        this.incAmount = incAmount;
//    }
//
//    public Integer getIncPercent() {
//        return incPercent;
//    }
//
//    public void setIncPercent(Integer incPercent) {
//        this.incPercent = incPercent;
//    }
//
//    public Integer getMaxPrice() {
//        return maxPrice;
//    }
//
//    public void setMaxPrice(Integer maxPrice) {
//        this.maxPrice = maxPrice;
//    }
//
//    public Integer getMinPrice() {
//        return minPrice;
//    }
//
//    public void setMinPrice(Integer minPrice) {
//        this.minPrice = minPrice;
//    }
//
//    public String getPriceVolume() {
//        return priceVolume;
//    }
//
//    public void setPriceVolume(String priceVolume) {
//        this.priceVolume = priceVolume == null ? null : priceVolume.trim();
//    }
//
//    public Integer getMoney() {
//        return money;
//    }
//
//    public void setMoney(Integer money) {
//        this.money = money;
//    }
//
//    public Integer getChangePercent() {
//        return changePercent;
//    }
//
//    public void setChangePercent(Integer changePercent) {
//        this.changePercent = changePercent;
//    }
//
//    public Integer getPeRatio() {
//        return peRatio;
//    }
//
//    public void setPeRatio(Integer peRatio) {
//        this.peRatio = peRatio;
//    }
//
//    public String getRemark() {
//        return remark;
//    }
//
//    public void setRemark(String remark) {
//        this.remark = remark == null ? null : remark.trim();
//    }
//
//    public Integer getSwing() {
//        return swing;
//    }
//
//    public void setSwing(Integer swing) {
//        this.swing = swing;
//    }
//
//    public Integer getMarketValue() {
//        return marketValue;
//    }
//
//    public void setMarketValue(Integer marketValue) {
//        this.marketValue = marketValue;
//    }
//
//    public Integer getTotalValue() {
//        return totalValue;
//    }
//
//    public void setTotalValue(Integer totalValue) {
//        this.totalValue = totalValue;
//    }
//
//    public Integer getMarketPercent() {
//        return marketPercent;
//    }
//
//    public void setMarketPercent(Integer marketPercent) {
//        this.marketPercent = marketPercent;
//    }
//
//    public Integer getVolumeRatio() {
//        return volumeRatio;
//    }
//
//    public void setVolumeRatio(Integer volumeRatio) {
//        this.volumeRatio = volumeRatio;
//    }
//
//    public Date getDay() {
//        return day;
//    }
//
//    public void setDay(Date day) {
//        this.day = day;
//    }
//    
//    public String getDayStr(){
//    	return DateUtil.getDateStr(day);
//    }
//
//    @Override
//    public Integer getPK() {
//        return id;
//    }
//}