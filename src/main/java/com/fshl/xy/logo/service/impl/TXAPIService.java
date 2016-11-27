package com.fshl.xy.logo.service.impl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fshl.xy.logo.entity.Gupiao;
import com.fshl.xy.logo.entity.Zijin;
import com.fshl.xy.logo.util.PiaoUtil;
import com.yisi.stiku.common.utils.DateUtil;
import com.yisi.stiku.common.utils.HttpClientUtil;

public class TXAPIService {
	
	private final static Logger LOG = LoggerFactory.getLogger(TXAPIService.class);

	private final static Map<Integer/*indexNo*/, String/*data name*/> indexNameMap = new HashMap<>();
	private final static Map<Integer/*indexNo*/, String/*data name*/> liangNameMap = new HashMap<>();
	
	static{
		indexNameMap.put(0, "序号 "); 
		indexNameMap.put(1, "名字 ");
		indexNameMap.put(2, "代码");  
		indexNameMap.put(3, "当前价格 ");
		indexNameMap.put(4, "昨收 ");
		indexNameMap.put(5, "今开 ");
		indexNameMap.put(6, "成交量（手） ");
		indexNameMap.put(7, "外盘 ");
		indexNameMap.put(8, "内盘 ");
		indexNameMap.put(9, "买一 ");
		indexNameMap.put(10, "买一量（手） ");
		indexNameMap.put(11, "买二");
		indexNameMap.put(12, "买二量（手）");
		indexNameMap.put(13, "买三");
		indexNameMap.put(14, "买三量（手）");
		indexNameMap.put(15, "买四");
		indexNameMap.put(16, "买四量（手）");
		indexNameMap.put(17, "买五");
		indexNameMap.put(18, "买五量（手）");
	    indexNameMap.put(19, "卖一 ");
		indexNameMap.put(20, "卖一量（手） ");  
		indexNameMap.put(21, "卖二 ");  
		indexNameMap.put(22, "卖二量（手）"); 
		indexNameMap.put(23, "卖三"); 
		indexNameMap.put(24, "卖三量（手）"); 
		indexNameMap.put(25, "卖四");  
		indexNameMap.put(26, "卖四量（手）"); 
		indexNameMap.put(27, "卖五"); 
		indexNameMap.put(28, "卖五量（手）");
		indexNameMap.put(29, "最近逐笔成交");  
		indexNameMap.put(30, "时间");  
		indexNameMap.put(31, "涨跌");  
        indexNameMap.put(32, "涨跌%");  
		indexNameMap.put(33, "最高");  
		indexNameMap.put(34, "最低"); 
		indexNameMap.put(35, "价格/成交量（手）/成交额 ");
		indexNameMap.put(36, "成交量（手） ");
		indexNameMap.put(37, "成交额（万）");
		indexNameMap.put(38, "换手率");
		indexNameMap.put(39, "市盈率");
		indexNameMap.put(40, "未知");
		indexNameMap.put(41, "最高");
		indexNameMap.put(42, "最低");
		indexNameMap.put(43, "振幅");
		indexNameMap.put(44, "流通市值");
		indexNameMap.put(45, "总市值");
		indexNameMap.put(46, "市净率");
		indexNameMap.put(47, "涨停价");
		indexNameMap.put(48, "跌停价");
		indexNameMap.put(49, "量比");
		
		liangNameMap.put(0, "代码");
		liangNameMap.put(1, "主力流入");
		liangNameMap.put(2, "主力流出");
		liangNameMap.put(3, "主力净流入");
		liangNameMap.put(4, "主力净流入/资金流入流出总和");
		liangNameMap.put(5, "散户流入");
		liangNameMap.put(6, "散户流出");
		liangNameMap.put(7, "散户净流入");
		liangNameMap.put(8, "散户净流入/资金流入流出总和");
		liangNameMap.put(9, "资金流入流出总和1+2+5+6");
		liangNameMap.put(10, "未知");
		liangNameMap.put(11, "未知");
		liangNameMap.put(12, "名字");
		liangNameMap.put(13, "日期");
	}
	
	@Autowired
	private ZijinServiceImpl zijinServiceImpl;
	
	@Autowired
	private GupiaoServiceImpl gupiaoServiceImpl;
	
	
	public Gupiao getGupiao(String code, String latestWeekDay){
		try {
			String str = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=" + code);
			if(str.contains("pv_none_match")){
				LOG.warn("code '" + code + "' not found for gupiao.");
				return null;
			}
			Gupiao gupiao = new Gupiao();
			String[] vals = str.split("=")[1].replace("\"", "").replace(";", "").split("~");
			
			Date dataDate = DateUtil.parseToDate(vals[30].substring(0, "yyyyMMdd".length()), "yyyyMMdd");
			if(!isLatestWeekDay(dataDate, latestWeekDay)){
				LOG.warn("code '" + code + "' is stopped");
				return null;
			}
			String dataDayStr = DateUtil.getDateStr(dataDate);
			if(!latestWeekDay.equals(dataDayStr)){
				LOG.warn("the data day '"+dataDayStr+"' is not equal to the latest week day '" + latestWeekDay + "', then abort it");
				return null;
			}
			gupiao.setDay(dataDate);
			gupiao.setCode(code.substring(2));
			gupiao.setName(vals[1]);
			gupiao.setType(code.substring(0, 2));
			gupiao.setCurrPrice(formatPrice(vals[3]));
			gupiao.setYesterPrice(formatPrice(vals[4]));
			gupiao.setOpenPrice(formatPrice(vals[5]));
			gupiao.setVolume(Integer.valueOf(vals[6]));
			gupiao.setOuterVolume(Integer.valueOf(vals[7]));
			gupiao.setInnerVolume(Integer.valueOf(vals[8]));
			gupiao.setBuyOnePrice(formatPrice(vals[9]));
			gupiao.setBuyOneVolume(formatPrice(vals[10]));
			gupiao.setBuyTwoPrice(formatPrice(vals[11]));
			gupiao.setBuyTwoVolume(formatPrice(vals[12]));
			gupiao.setBuyThreePrice(formatPrice(vals[13]));
			gupiao.setBuyThreeVolume(formatPrice(vals[14]));
			gupiao.setBuyFourPrice(formatPrice(vals[15]));
			gupiao.setBuyFourVolume(formatPrice(vals[16]));
			gupiao.setBuyFivePrice(formatPrice(vals[17]));
			gupiao.setBuyFiveVolume(formatPrice(vals[18]));
			gupiao.setSellOnePrice(formatPrice(vals[19]));
			gupiao.setSellOneVolume(formatPrice(vals[20]));
			gupiao.setSellTwoPrice(formatPrice(vals[21]));
			gupiao.setSellTwoVolume(formatPrice(vals[22]));
			gupiao.setSellThreePrice(formatPrice(vals[23]));
			gupiao.setSellThreeVolume(formatPrice(vals[24]));
			gupiao.setSellFourPrice(formatPrice(vals[25]));
			gupiao.setSellFourVolume(formatPrice(vals[26]));
			gupiao.setSellFivePrice(formatPrice(vals[27]));
			gupiao.setSellFiveVolume(formatPrice(vals[28]));
			gupiao.setLatestDeal(vals[29]);
			gupiao.setIncAmount(formatPrice(vals[31]));
			gupiao.setIncPercent(toInt(vals[32]));
			gupiao.setMaxPrice(formatPrice(vals[33]));
			gupiao.setMinPrice(formatPrice(vals[34]));
			gupiao.setPriceVolume(vals[35]);
			gupiao.setMoney(toInt(vals[37]));
			gupiao.setChangePercent(toInt(vals[38]));
			gupiao.setPeRatio(formatPrice(vals[39]));
			gupiao.setSwing(formatPrice(vals[43]));
			gupiao.setMarketValue(toInt(vals[44]));
			gupiao.setTotalValue(toInt(vals[45]));
			gupiao.setMarketPercent(formatPrice(vals[46]));
			gupiao.setVolumeRatio(formatPrice(vals[49]));
			
			return gupiao;
		} catch (Exception e) {
			LOG.warn("股票("+code+")基本数据抓取失败,msg:" + e.getMessage());
		} 
		
		return null;
	}
	
	private int toInt(String data){
		if(StringUtils.isBlank(data)){
			return 0;
		}
		
		return Double.valueOf(data).intValue();
	}
	
	private int formatPrice(String price){
		if(StringUtils.isBlank(price)){
			return 0;
		}
		double p = Double.valueOf(price)*100;
		return (int)p;
	}
	
	public void crawlAll(){
		crawlGupiao();
		
		crawlZijin();
	}
	
	public void crawlGupiao(){
		String latestWeekDay = PiaoUtil.getLatestWeekDay();
		List<Gupiao> gupiaos = new ArrayList<>();
		List<String> codes = getGupiaoCodes();
		for(String code : codes){
			Gupiao gupiao = getGupiao(code, latestWeekDay);
			
			if(gupiao != null){
				gupiaos.add(gupiao);
			}
			
			/*if(gupiaos.size() > 100){
			   break;
		    }*/
		}
		
		if(gupiaos.size() > 100){
			gupiaoServiceImpl.deleteByDay(latestWeekDay);
			gupiaoServiceImpl.insertBatch(gupiaos);
		}
	}
	
	public void crawlZijin(){
		String latestWeekDay = PiaoUtil.getLatestWeekDay();
		List<Zijin> zijins = new ArrayList<>();
		List<String> codes = getGupiaoCodes();
		for(String code : codes){
			Zijin zijin = getZijin(code, latestWeekDay);
			
			if(zijin != null){
				zijins.add(zijin);
			}
			
			/*if(zijins.size() > 100){
				break;
			}*/
		}
		
		if(zijins.size() > 100){
			zijinServiceImpl.deleteByDay(latestWeekDay);
			zijinServiceImpl.insertBatch(zijins);
			
			zijinServiceImpl.statLatestMainPureIn(latestWeekDay);
		}
	}
	
	public Zijin getZijin(String code, String latestWeekDay){
		try {
			String liang = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=ff_"+code);
			if(liang.contains("pv_none_match")){
				LOG.warn("code '" + code + "' not found for zijin.");
				return null;
			}
			
			String[] liangVals = liang.split("=")[1].replace("\"", "").replace(";", "").split("~");
			Zijin zijin = new Zijin();
			Date dataDate = DateUtil.parseToDate(liangVals[13], "yyyyMMdd");
			if(!isLatestWeekDay(dataDate, latestWeekDay)){
				LOG.warn("code '" + code + "' is stopped");
				return null;
			}
			zijin.setDay(dataDate);
			zijin.setCode(liangVals[0].substring(2));
			zijin.setName(liangVals[12]);
			zijin.setType(liangVals[0].substring(0, 2));
			zijin.setMainIn(toInt(liangVals[1]));
			zijin.setMainOut(toInt(liangVals[2]));
			zijin.setMainPureIn(toInt(liangVals[3]));
			zijin.setPersonIn(toInt(liangVals[5]));
			zijin.setPersonOut(toInt(liangVals[6]));
			zijin.setPersonPureIn(toInt(liangVals[7]));
			
			String pankou = HttpClientUtil.getInstance().doGet("http://qt.gtimg.cn/q=s_pk"+code);
			if(StringUtils.isNotBlank(pankou)){
				String[] pankous = pankou.split("=")[1].replace("\"", "").replace(";", "").split("~");
				zijin.setBuyBig(getPankouVal(pankous[0]));
				zijin.setBuySmall(getPankouVal(pankous[1]));
				zijin.setSellBig(getPankouVal(pankous[2]));
				zijin.setSellSmall(getPankouVal(pankous[3]));
			}
			
			return zijin;
		}catch (Exception e) {
			LOG.warn("股票("+code+")资金数据抓取失败,msg:" + e.getMessage());
		} 
		
		return null;
	}
	
	
	/**
	 * 判断date是否为最近一个工作日
	 * @param date
	 * @return
	 */
	private boolean isLatestWeekDay(Date date, String latestWeekDay){
		return latestWeekDay.equals(DateUtil.getDateStr(date));
	}
	
	/**
	 * 
	 * @param money
	 * @return
	 */
	private int getPankouVal(String money){
		double p = Double.valueOf(money)*10000;
		return (int)p;
	}
	
	public List<String> getGupiaoCodes(){
		List<String> codes = new ArrayList<>();
		try {
			Document doc = Jsoup.parse(new URL("http://quote.eastmoney.com/stocklist.html"), 5000);
			Element elem = doc.getElementById("quotesearch");
			Elements elems = elem.getElementsByTag("a");
			for(Element li : elems){
				String href = li.attr("href");
				String code = null;
				if(StringUtils.isNotBlank(href)){
					code = href.substring(href.lastIndexOf("/") + 1);
					code = code.replace(".html", "");
					if(code.startsWith("sz0") || code.startsWith("sz3") || code.startsWith("sh6") || code.startsWith("sh3")){
						codes.add(code);
					}
				}
			}
		} catch (IOException e) {
			LOG.warn("股票列表抓取失败");
		}
		
		return codes;
	}
	
	public static void main(String[] args) {
		//getData("000953");//"http://quote.eastmoney.com/stocklist.html"
		
		/*List<String> codes = getGupiaoCodes();
		for(String code : codes){
			
		}
		System.out.println(codes.size());*/
	}
}
