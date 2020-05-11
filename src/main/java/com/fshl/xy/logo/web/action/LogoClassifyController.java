package com.fshl.xy.logo.web.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("lc")
public class LogoClassifyController {
	
	@RequestMapping("index")
	public ModelAndView index() {
		
		return new ModelAndView("lc/index");
	}

}
