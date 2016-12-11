package com.fshl.xy.logo.web.action;

import com.fshl.xy.logo.entity.*;
import com.fshl.xy.logo.service.impl.*;
import com.fshl.xy.logo.util.PiaoUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/gp")
public class GupiaoController {

	@Autowired
	private TXAPIService txAPIService;
	
	@Autowired
	private GupiaoServiceImpl gupiaoServiceImpl;
	
	@Autowired
	private ZijinServiceImpl zijinServiceImpl;

	@Autowired
	private TrackPiaoServiceImpl trackPiaoServiceImpl;

	@Autowired
	private Latest5dPriceServiceImpl latest5dPriceServiceImpl;
	
	@RequestMapping("/datachart")
	public String dataChart(HttpServletRequest request, String codeOrName){
		if(StringUtils.isBlank(codeOrName)){
			codeOrName = "600000";
		}
		if(!codeOrName.matches("\\d+") && !codeOrName.matches("\\w+")){
			request.setAttribute("errorMsg", "请输入正确的股票代码或者股票名称");
		}else{
			List<Zijin> zijins = zijinServiceImpl.findByCodeOrName(codeOrName);
			List<MainPureIn> mainPureIns = zijinServiceImpl.findLatestMainPureIn(codeOrName);
			request.setAttribute("zijins", zijins);
			request.setAttribute("mainPureIns", mainPureIns);
			if(zijins != null && !zijins.isEmpty()){
				Zijin zijin = zijins.get(0);
				request.setAttribute("name", zijin.getName());
				request.setAttribute("code", zijin.getCode());
			}
		}
		
		request.setAttribute("kw", codeOrName);
		return "gupiao/data_chart";
	}
	
	@RequestMapping("/gappiao")
	public String findGapPiaos(HttpServletRequest request, String day){
		if(StringUtils.isBlank(day)){
			day = PiaoUtil.getLatestWeekDay();
		}
		
		List<Gupiao> piaos = gupiaoServiceImpl.findGapPiaos(day);
		request.setAttribute("piaos", piaos);
		request.setAttribute("size", piaos == null ? 0 : piaos.size());
		
		return "gupiao/gap";
	}

	@RequestMapping("/downpiao")
	public String findDownByDays(HttpServletRequest request, Integer days){
		if(days == null){
			days = 3;
		}
		List<Latest5dPrice> priceList = latest5dPriceServiceImpl.findDownByDays(days);
		request.setAttribute("days", days);
		request.setAttribute("piaos", priceList);
		request.setAttribute("size", priceList == null ? 0 : priceList.size());

		return "gupiao/downpiao";
	}

	@RequestMapping("/addTrackPiao")
	public String addTrackPiao(String code){
		trackPiaoServiceImpl.addTrackPiao(code);

		return "redirect:/gp/listTrackPiao.do";
	}

	@RequestMapping("/listTrackPiao")
	public String listTrackPiao(HttpServletRequest request){
		List<TrackPiao> piaos = trackPiaoServiceImpl.findTrackPiao();

		request.setAttribute("piaos", piaos);
		request.setAttribute("size", piaos == null ? 0 : piaos.size());
		return "gupiao/track";
	}

	@RequestMapping("/deltrack")
	public String delTrackPiao(String code){
		trackPiaoServiceImpl.deleteById(code);

		return "redirect:/gp/listTrackPiao.do";
	}
	
	@RequestMapping("/crawl/zijin")
	@ResponseBody
	public String crawlZijin(){
		txAPIService.crawlZijin();
		
		return "OK";
	}
	
	@RequestMapping("/crawl/gupiao")
	@ResponseBody
	public String crawlGupiao(){
		txAPIService.crawlGupiao();
		
		return "OK";
	}

	@RequestMapping("/reset5day")
	@ResponseBody
	public String resetLatest5dayPiaos(){
		latest5dPriceServiceImpl.resetLatest5dayPiaos();

		return "OK";
	}



	@RequestMapping("/statmainpurein")
	@ResponseBody
	public String statmainpurein(String weekday){
		weekday = PiaoUtil.getLatestWeekDay(weekday);
		zijinServiceImpl.statLatestMainPureIn(weekday);
		
		return "OK";
	}
	
	
}
