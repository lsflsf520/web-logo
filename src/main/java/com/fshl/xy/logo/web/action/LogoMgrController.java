package com.fshl.xy.logo.web.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fshl.xy.logo.entity.BusiLogo;
import com.fshl.xy.logo.service.impl.BackupService;
import com.fshl.xy.logo.service.impl.BusiLogoService;
import com.fshl.xy.logo.util.DocUtil;
import com.google.gson.Gson;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.utils.BaseConfig;
import com.xyz.tools.common.utils.DateUtil;
import com.xyz.tools.common.utils.RegexUtil;
import com.xyz.tools.web.util.WebUtils;

@Controller
@RequestMapping("/logo")
public class LogoMgrController {
	
	private final static Logger LOG = LoggerFactory.getLogger(LogoMgrController.class);
	
	@Resource
	private BusiLogoService busiLogoServiceImpl;
	
	@Resource
	private BackupService backupService;
	
//	private final static int COST_PRICE = Integer.valueOf(BaseConfig.getValue("logo.cost.price"));
	private final static int BILL_PRICE = Integer.valueOf(BaseConfig.getValue("logo.bill.price"));
	
	private final static Map<Integer, String> statusMap = new HashMap<Integer, String>();
	private final static Map<Integer, String> typeMap = new HashMap<Integer, String>();
	private final static Map<Integer, Integer> typeCostMap = new HashMap<Integer, Integer>();
	
	static {
		statusMap.put(0, "待结款");
		statusMap.put(1, "待提局");
		statusMap.put(2, "待受理");
		statusMap.put(3, "待拿证");
		statusMap.put(4, "不受理");
		statusMap.put(5, "已拿证");
		statusMap.put(6, "已拒证");

		typeMap.put(0, "商标注册");
		typeMap.put(1, "商标异议");
		typeMap.put(2, "著作权");
		typeMap.put(3, "专利");
		typeMap.put(4, "商标购买");
		typeMap.put(5, "商标变更");
		typeMap.put(6, "商标转让");
		typeMap.put(7, "续费");
		typeMap.put(8, "设计");

		for(Integer type : typeMap.keySet()){
			String valStr = BaseConfig.getValue("logo."+type+".cost.price");
			if(RegexUtil.isInt(valStr)){
				typeCostMap.put(type, Integer.valueOf(valStr));
			}
		}
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, String yearMonth, Integer status, 
			          Integer timeType, String startDate, String endDate, String partner){
        String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token) && !PassportController.READONLY_VALUE.equals(token)){
        	return "redirect:/sys/tologin.do";
        }
		String keyword = request.getParameter("keyword");
		List<String> timeList = getLast3YearMonth();
		if(StringUtils.isBlank(yearMonth)){
			yearMonth =  timeList.get(0);
		}else if ("-1".equals(yearMonth)) {
		      yearMonth = null;
	    }
		Date sDate = null;
		Date eDate = null;
		if(StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)){
			startDate = DateUtil.getMonthStr(new Date()) + "-01";
			sDate = DateUtil.parseDate(startDate);
			eDate = DateUtil.timeAddByMonth(sDate, 1);
			endDate = DateUtil.getDateStr(eDate);
		}else{
			sDate = DateUtil.parseDate(startDate);
			eDate = DateUtil.parseDate(endDate);
		}
		List<BusiLogo> logoList = null;
		timeType = (timeType == null ? 0 : timeType);
		if(timeType == 0){
			logoList = busiLogoServiceImpl.queryBusiLogo(yearMonth, keyword, partner, status);
		}else{
			logoList = busiLogoServiceImpl.queryBusiLogo(sDate, eDate, keyword, partner, status);
		}
		String[] partners = BaseConfig.getValueArr("up.partners");
		request.setAttribute("logoList", logoList);
//		request.setAttribute("costPrice", COST_PRICE);
		request.setAttribute("typeCostMap", new Gson().toJson(typeCostMap));
		request.setAttribute("billPrice", BILL_PRICE);
		request.setAttribute("yearMonth", StringUtils.isBlank(yearMonth) ? null : yearMonth);
		request.setAttribute("startDate", startDate);
		request.setAttribute("endDate", endDate);
		request.setAttribute("timeType", timeType);
		request.setAttribute("keyword", keyword);
		request.setAttribute("status", status);
		request.setAttribute("partner", partner);
		request.setAttribute("partners", Arrays.asList(partners));
		request.setAttribute("statusMap", statusMap);
		request.setAttribute("typeMap", typeMap);
		
		request.setAttribute("timeList", timeList);
		
		
		int totalMoney = 0, totalProfit = 0, totalDesignFee = 0, totalDesignProfit = 0, hasGetProfit = 0, hasGetMoney = 0, logoNum = 0;
		if(logoList != null && !logoList.isEmpty()){
			for(BusiLogo logo : logoList){
				int currTotalMoney = logo.getLogoFee() + logo.getDesignFee();
				totalMoney += currTotalMoney;
				totalProfit += logo.getTotalProfit();
				totalDesignFee += logo.getDesignFee();
				totalDesignProfit += logo.getDesignProfit();
				logoNum += logo.getNum();
				
				if(logo.getStatus() > 0){
					hasGetMoney += currTotalMoney;
					
					if(logo.getMyFeeStatus() == 1){
						hasGetProfit += logo.getTotalProfit();
					}
				}
			}
		}
		
		request.setAttribute("totalMoney", totalMoney); //总流水
		request.setAttribute("totalProfit", totalProfit); //总利润
		request.setAttribute("totalDesignFee", totalDesignFee); //总的设计费
		request.setAttribute("totalDesignProfit", totalDesignProfit); //总的设计利润
		request.setAttribute("hasGetProfit", hasGetProfit); //已经收到的总利润，即实际到账的钱
		request.setAttribute("hasGetMoney", hasGetMoney); //已经收到的总金额，即已经结清的订单的总金额
		request.setAttribute("logoNum", logoNum); //商标总数
		
		if(PassportController.TK_VALUE.equals(token)){
			return "logo/list";
		}
		
		return "logo/readonly";
	}
	
	@RequestMapping("/saveLogo")
	public void saveLogo(HttpServletRequest request, HttpServletResponse response, BusiLogo logo){
		String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token)){
        	WebUtils.writeJson(new ResultModel("请先登录"), request, response);
        	return;
        }
		if(logo.getNum() == null || logo.getLogoFee() == null){
			WebUtils.writeJson(new ResultModel("ILLEGAL_PARAM", "数量和商标费不能为空！"), request, response);
			return ;
		}
		
		BusiLogo dbData = null;
		if(logo.getId() != null){
			dbData = busiLogoServiceImpl.findById(logo.getId());
		}
		if(dbData == null){
			logo.setCreateTime(new Date());
		}
		
		//如果是全款缴清，则将订单状态直接置为 1，即“待提局”
		if(dbData == null || dbData.getStatus() == null || dbData.getStatus() == 0){
			if(logo.getLogoFee().equals(logo.getFirstPayment())){
				logo.setStatus(1);
			}else if(logo.getMyFeeStatus() == 1){
				logo.setMyFeeTime(logo.getCreateTime());
				logo.setStatus(1);
			}if(logo.getChenFeeStatus() == 1){
				logo.setChenFeeTime(logo.getCreateTime());
				logo.setStatus(1);
			}else{
				logo.setStatus(0);
			}
		}else if(logo.getMyFeeStatus() == 0 && logo.getChenFeeStatus() == 0){
			logo.setStatus(0);
		}
		
		if(logo.getTotalProfit() == null && typeCostMap.get(logo.getOrderType()) != null){
			logo.setTotalProfit(logo.getLogoFee() - logo.getNum() * ( typeCostMap.get(logo.getOrderType()) + logo.getBill() * BILL_PRICE)  + (logo.getDesignProfit() == null ? 0 : logo.getDesignProfit()));
		}
		
		if(logo.getId() == null){
			busiLogoServiceImpl.insert(logo);
		}else{
			busiLogoServiceImpl.update(logo);
		}
		
		WebUtils.writeJson(new ResultModel("保存成功！"), request, response);
	}
	
	@RequestMapping("/delOrder")
	public void delOrder(HttpServletRequest request, HttpServletResponse response, int orderId){
		String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token)){
        	WebUtils.writeJson(new ResultModel("请先登录"), request, response);
        	return;
        }
		BusiLogo logo = busiLogoServiceImpl.findById(orderId);
		if(logo == null){
			WebUtils.writeJson(new ResultModel("NOT_EXIST", "订单不存在！"), request, response);
			return ;
		}
		
		logo.setStatus(-1);
		busiLogoServiceImpl.update(logo);
		
		WebUtils.writeJson(new ResultModel("删除成功！"), request, response);
	}
	
	@RequestMapping("/payRemainFee")
	public void payRemainFee(HttpServletRequest request, HttpServletResponse response, int orderId, int who){
		String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token)){
        	WebUtils.writeJson(new ResultModel("请先登录"), request, response);
        	return;
        }
		BusiLogo logo = busiLogoServiceImpl.findById(orderId);
		if(logo == null){
			WebUtils.writeJson(new ResultModel("NOT_EXIST", "订单不存在！"), request, response);
			return ;
		}
		
		if(who == 0){
			logo.setMyFeeStatus(1);
			logo.setMyFeeTime(new Date());
		}else{
			logo.setChenFeeStatus(1);
			logo.setChenFeeTime(new Date());
		}
		
		if(logo.getStatus() == 0){
			logo.setStatus(1);
		}
		
		busiLogoServiceImpl.update(logo);
		
		WebUtils.writeJson(new ResultModel("操作成功！"), request, response);
	}
	
	@RequestMapping("/updateExpress")
	public void updateExpress(HttpServletRequest request, HttpServletResponse response, int orderId){
		String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token)){
        	WebUtils.writeJson(new ResultModel("请先登录"), request, response);
        	return;
        }
		BusiLogo logo = busiLogoServiceImpl.findById(orderId);
		if(logo == null){
			WebUtils.writeJson(new ResultModel("NOT_EXIST", "订单不存在！"), request, response);
			return ;
		}
		
		logo.setExpressNum("Y");
		
		busiLogoServiceImpl.update(logo);
		
		WebUtils.writeJson(new ResultModel("操作成功！"), request, response);
	}
	
	@RequestMapping("/downloadDoc")
	public void downloadDoc(HttpServletRequest request, HttpServletResponse response, int orderId, String type){
		String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token)){
        	WebUtils.writeJson(new ResultModel("请先登录"), request, response);
        	return;
        }
		BusiLogo logo = busiLogoServiceImpl.findById(orderId);
		if(logo == null){
			WebUtils.writeJson(new ResultModel("NOT_EXIST", "订单不存在！"), request, response);
			return ;
		}
		
		if("delegate".equals(type)){
			//下载委托书
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("company", logo.getCompany());
			dataMap.put("logoName", logo.getLogoName());
			dataMap.put("customerAddr", logo.getCustomerAddr());
			dataMap.put("date", DateUtil.formatDate(logo.getCreateTime(), "yyyy 年  MM 月 dd 日"));
			
			try{
				DocUtil.writeHttpResponse(response, "delegate", dataMap, logo.getLogoName() + "商标代理委托书");
			}catch(Exception e){
				WebUtils.writeJson(new ResultModel("DOWNLOAD_ERROR", logo.getLogoName() + "商标代理委托书 下载失败！"), request, response);
				LOG.error(logo.getLogoName() + "商标代理委托书 下载失败！ orderId:" + orderId);
			}
		}else if("agent".equals(type)){
			//下载协议书
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("company", logo.getCompany());
			dataMap.put("logoName", logo.getLogoName());
			dataMap.put("customerAddr", logo.getCustomerAddr());
			dataMap.put("phone", logo.getPhone());
			dataMap.put("userName", logo.getUserName());
			dataMap.put("num", logo.getNum());
			int totalPrice = logo.getLogoFee();
			dataMap.put("totalPrice", totalPrice + "");
			dataMap.put("bigTotalPrice", DocUtil.digitUppercase(totalPrice));
			dataMap.put("logoTypes", logo.getLogoTypes());
			try{
				DocUtil.writeHttpResponse(response, "agent", dataMap, logo.getLogoName() + "商标协议书");
			}catch(Exception e){
				WebUtils.writeJson(new ResultModel("DOWNLOAD_ERROR", logo.getLogoName() + "商标协议书 下载失败！"), request, response);
				LOG.error(logo.getLogoName() + "商标协议书 下载失败！ orderId:" + orderId);
			}
		}else{
			Map<String, Object> dataMap = new HashMap<String, Object>();
			//dataMap.put("logoImg", DocUtil.getImageStr("d:/tieyuanmuge.png"));
			dataMap.put("company", logo.getCompany());
			dataMap.put("logoName", logo.getLogoName());
			dataMap.put("customerAddr", logo.getCustomerAddr());
			dataMap.put("logoTypes", logo.getLogoTypes());
			try{
				DocUtil.writeHttpResponse(response, "apply", dataMap, logo.getLogoName() + "商标申请书");
			}catch(Exception e){
				WebUtils.writeJson(new ResultModel("DOWNLOAD_ERROR", logo.getLogoName() + "商标申请书 下载失败！"), request, response);
				LOG.error(logo.getLogoName() + "商标申请书 下载失败！ orderId:" + orderId);
			}
		}
	}
	
	@RequestMapping("/updateStatus")
	public void updateStatus(HttpServletRequest request, HttpServletResponse response, int orderId, int status){
		String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token) && !PassportController.READONLY_VALUE.equals(token)){
        	WebUtils.writeJson(new ResultModel("请先登录"), request, response);
        	return;
        }
		BusiLogo logo = busiLogoServiceImpl.findById(orderId);
		if(logo == null){
			WebUtils.writeJson(new ResultModel("NOT_EXIST", "订单不存在！"), request, response);
			return ;
		}
		
		if((logo.getStatus() >= status) || status > 6 || status < 0){
			WebUtils.writeJson(new ResultModel("NOT_SUPPORT", "不支持的操作"), request, response);
			return ;
		}
		
		logo.setStatus(status);
		switch(status){
		case 2:logo.setTijuTime(new Date());break;
		case -1:
		case 3:logo.setAcceptTime(new Date());break;
		case -2:
		case 4:logo.setCertTime(new Date());break;
		}
		
        busiLogoServiceImpl.update(logo);
		
		WebUtils.writeJson(new ResultModel("操作成功！"), request, response);
	}
	
	@RequestMapping("/backupData")
	public void backupData(HttpServletRequest request, HttpServletResponse response){
		String token = WebUtils.getCookieValue(request, "_tk_");
        if(!PassportController.TK_VALUE.equals(token)){
        	WebUtils.writeJson(new ResultModel("请先登录"), request, response);
        	return;
        }
		try{
			
			backupService.backupData();
			WebUtils.writeJson(new ResultModel("备份成功"), request, response);
		}catch(Exception e){
			LOG.error("数据备份失败", e);
			
			WebUtils.writeJson(new ResultModel("BACKUP_ERROR", "备份失败"), request, response);
		}
		
	}
	
	private List<String> getLast3YearMonth(){
		Date currDate = new Date();
		int year = Integer.valueOf(DateUtil.formatDate(currDate, "yyyy"));
		int month = Integer.valueOf(DateUtil.formatDate(currDate, "M"));
		
		List<String> timeList = new ArrayList<String>();
		for(int m = month; m > 0; m--){
			timeList.add(year + "-" + m);
		}
		
		for(int y = year - 1; y > year - 4; y--){
			for(int m = 12; m > 0; m--){
				timeList.add(y + "-" + m);
			}
		}
		
		return timeList;
	}
}
