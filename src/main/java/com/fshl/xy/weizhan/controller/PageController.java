package com.fshl.xy.weizhan.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fshl.xy.weizhan.entity.ImgText;
import com.fshl.xy.weizhan.entity.Prod;
import com.fshl.xy.weizhan.entity.ProdDetail;
import com.fshl.xy.weizhan.entity.ProdParam;
import com.fshl.xy.weizhan.service.ImgTextService;
import com.fshl.xy.weizhan.service.ProdDetailService;
import com.fshl.xy.weizhan.service.ProdParamService;
import com.fshl.xy.weizhan.service.ProdService;
import com.fshl.xy.weizhan.service.SiteInfoService;
import com.fshl.xy.weizhan.utils.WeiZhanUtil;
import com.xyz.tools.common.utils.ThreadUtil;
import com.xyz.tools.db.bean.PageData;

@Controller
@RequestMapping("wz")
public class PageController {
	
	@Resource
	private SiteInfoService siteInfoService;
	@Resource
	private ImgTextService imgTextService;
	@Resource
	private ProdService prodService;
	@Resource
	private ProdDetailService prodDetailService;
	@Resource
	private ProdParamService prodParamService;
	
	@RequestMapping("index")
	public ModelAndView toIndex() {
		return new ModelAndView("weizhan/index");
	}
	
	@RequestMapping("enviroment")
	public ModelAndView toEnviroment(int currPage) {
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		PageData<ImgText> dataPage = imgTextService.loadEnvBySiteId(siteId, currPage);
		return new ModelAndView("weizhan/environment", "dataPage", dataPage);
	}
	
	@RequestMapping("prodList")
	public ModelAndView prodList(int currPage) {
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		PageData<Prod> dataPage = prodService.loadByPage(siteId, currPage);
		
		ModelAndView mav = new ModelAndView("weizhan/prodList", "dataPage", dataPage);
		
		List<Integer> prodIds = new ArrayList<>();
		if(!CollectionUtils.isEmpty(dataPage.getDatas())) {
			for(Prod prod : dataPage.getDatas()) {
				prodIds.add(prod.getId());
			}
			
			Map<Integer, List<ProdParam>> paramMap = prodParamService.loadMainProdParamMap(prodIds.toArray(new Integer[0]));
			
			mav.addObject("paramMap", paramMap);
		}
		
		return mav;
	}
	
	@RequestMapping("dynamic")
	public ModelAndView dynamic(int currPage) {
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		PageData<ImgText> dataPage = imgTextService.loadDynamicBySiteId(siteId, currPage);
		return new ModelAndView("weizhan/dynamic", "dataPage", dataPage);
	}

	@RequestMapping("detail")
	public ModelAndView prodDetail(int prodId) {
		Prod prod = prodService.findById(prodId);
		ProdDetail detail = prodDetailService.findById(prodId);
		List<ProdParam> prodParams = prodParamService.loadByProdId(prodId);
		ModelAndView mav = new ModelAndView("weizhan/detail", "prod", prod);
		mav.addObject("detail", detail);
		mav.addObject("prodParams", prodParams);
		
		return mav;
	}
}
