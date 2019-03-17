package com.fshl.xy.logo.web.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fshl.xy.logo.util.PassportUtil;
import com.xyz.tools.common.utils.LogUtils;
import com.xyz.tools.web.util.WebUtils;


@Controller
@RequestMapping("sys")
public class PassportController {
	
	
//	public final static String READONLY_VALUE = "reduwwwyyoouuu323ee";
	
	@RequestMapping("tologin")
	public ModelAndView tologin(String username){
		
		return new ModelAndView("logo/login", "username", username);
	}

	@RequestMapping("doLogon")
	public String login(HttpServletResponse response, String username, String password, RedirectAttributes redirectAttr){
		if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)
				){
			username = username.replaceAll("\\s+", "");
			password = password.replaceAll("\\s+", "");
			
			try{
				String token = PassportUtil.canLogin(username, password);
				if(StringUtils.isNotBlank(token)) {
					WebUtils.setCookieValue(response, "_tk_", token, -1);
					return "redirect:/logo/list.do";
				}
			} catch (Exception e) {
				LogUtils.warn("auth fail for userName %s with password %s", username, password);
			}
		}
		
		redirectAttr.addAttribute("username", username);
		return "redirect:/sys/tologin.do";
	}
	
	@RequestMapping("doLogout")
	public String doLogout(HttpServletRequest request, HttpServletResponse response) {
		WebUtils.deleteAllCookies(request, response);
		return "redirect:/sys/tologin.do";
	}
	
	/*if("siyufank".equals() && "335869".equals(password.trim())){
	WebUtils.setCookieValue(response, "_tk_", TK_VALUE, -1);
	return "redirect:/logo/list.do";
} else if("genghui".equals(username.replaceAll("\\s+", "")) && "gh2018".equals(password.trim())){
	WebUtils.setCookieValue(response, "_tk_", READONLY_VALUE, -1);
	return "redirect:/logo/list.do";
} */

	
}
