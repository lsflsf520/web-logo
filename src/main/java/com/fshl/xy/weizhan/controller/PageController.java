package com.fshl.xy.weizhan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fshl.xy.weizhan.service.ImgTextService;
import com.fshl.xy.weizhan.service.SiteInfoService;

@Controller
@RequestMapping("wz")
public class PageController {
	
	@Resource
	private SiteInfoService siteInfoService;
	@Resource
	private ImgTextService imgTextService;
	
	@RequestMapping("index")
	public ModelAndView toIndex() {
		return new ModelAndView("weizhan/index");
	}
	
	@RequestMapping("enviroment")
	public ModelAndView toEnviroment() {
		
		return new ModelAndView("weizhan/environment");
	}
	
	@RequestMapping("prodList")
	public ModelAndView prodList() {
		
		return new ModelAndView("weizhan/prodList");
	}
	
	@RequestMapping("dynamic")
	public ModelAndView dynamic() {
		
		return new ModelAndView("weizhan/dynamic");
	}

	@RequestMapping("detail")
	public ModelAndView prodDetail() {
		
		return new ModelAndView("weizhan/detail");
	}
}
