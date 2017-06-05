package com.fshl.xy.logo.web.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ujigu.secure.web.util.WebUtils;

@Controller
@RequestMapping("sys")
public class PassportController {
	
	public final static String TK_VALUE = "xxyyuueee";
	
	@RequestMapping("tologin")
	public ModelAndView tologin(String username){
		
		return new ModelAndView("logo/login", "username", username);
	}

	@RequestMapping("doLogon")
	public String login(HttpServletResponse response, String username, String password, RedirectAttributes redirectAttr){
		
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)
				&& "siyufank".equals(username.trim()) && "335869".equals(password.trim())){
            WebUtils.setCookieValue(response, "_tk_", TK_VALUE, -1);
			
			return "redirect:/logo/list.do";
		}
		
		redirectAttr.addAttribute("username", username);
		return "redirect:/sys/tologin.do";
	}
	
}
