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
import com.fshl.xy.weizhan.vo.ProdListVO;
import com.xyz.tools.common.bean.ResultModel;
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
		return new ModelAndView("weizhan/index", "currPageName", "index");
	}
	
	@RequestMapping("environment")
	public ModelAndView toEnvironment(int currPage) {
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		PageData<ImgText> dataPage = imgTextService.loadEnvBySiteId(siteId, currPage);
		ModelAndView mav = new ModelAndView("weizhan/environment", "dataPage", dataPage);
		mav.addObject("currPageName", "environment");
		return mav;
	}
	
	@RequestMapping("prodList")
	public ModelAndView prodList() {
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		
		PageData<ProdListVO> dataPage = loadProdListVOByPage(siteId, 1);
		
		ModelAndView mav = new ModelAndView("weizhan/prodList", "dataPage", dataPage);
		mav.addObject("currPageName", "prodList");
		return mav;
	}
	
	@RequestMapping("prodListByPage")
	public ResultModel prodListByPage(int currPage) {
        int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		
		PageData<ProdListVO> dataPage = loadProdListVOByPage(siteId, currPage);
		
		return new ResultModel(dataPage);
	}
	
	/**
	 * 
	 * @param siteId 站点ID
	 * @param currPage 分页页码，从1开始
	 * @return
	 */
	private PageData<ProdListVO> loadProdListVOByPage(int siteId, int currPage) {
        PageData<ProdListVO> dataPage = prodService.loadProdListVOByPage(siteId, currPage);
		
		List<Integer> prodIds = new ArrayList<>();
		if(!CollectionUtils.isEmpty(dataPage.getDatas())) {
			for(ProdListVO prod : dataPage.getDatas()) {
				prodIds.add(prod.getId());
			}
			
			Map<Integer, List<ProdParam>> paramMap = prodParamService.loadMainProdParamMap(prodIds.toArray(new Integer[0]));
			
			for(ProdListVO prod : dataPage.getDatas() ){
				List<ProdParam> vals = paramMap.get(prod.getId());
				if(!CollectionUtils.isEmpty(vals)) {
					prod.setMainParams(vals);
				}
			}
		}
		
		return dataPage;
	}
	
	@RequestMapping("dynamic")
	public ModelAndView dynamic(int currPage) {
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		PageData<ImgText> dataPage = imgTextService.loadDynamicBySiteId(siteId, currPage);
		ModelAndView mav = new ModelAndView("weizhan/dynamic", "dataPage", dataPage);
		mav.addObject("currPageName", "dynamic");
		
		return mav;
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
