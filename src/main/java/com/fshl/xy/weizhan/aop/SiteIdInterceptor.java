package com.fshl.xy.weizhan.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fshl.xy.weizhan.entity.SiteInfo;
import com.fshl.xy.weizhan.entity.WxUser;
import com.fshl.xy.weizhan.service.SiteInfoService;
import com.fshl.xy.weizhan.service.WxUserService;
import com.fshl.xy.weizhan.utils.WeiZhanUtil;
import com.xyz.tools.common.exception.BaseRuntimeException;
import com.xyz.tools.common.utils.ThreadUtil;
import com.xyz.tools.web.aop.AbstractInterceptor;

public class SiteIdInterceptor extends AbstractInterceptor {
	
	@Resource
	private SiteInfoService siteInfoService;
	@Resource
	private WxUserService wxUserService;

	@Override
	protected boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			String requestUri) throws Exception {
		String domain = ThreadUtil.getCurrDomain();
		SiteInfo dbData = siteInfoService.loadByDomain(domain);
		if(dbData == null) {
			throw new BaseRuntimeException("NOT_FOUND_SITE", "没有找到对应的站点'" + domain + "'");
		}
		
		if(dbData.getBuid() == null || dbData.getBuid() <= 0) {
			throw new BaseRuntimeException("NO_BUID_SITE", "当前站点'" + domain + "'数据异常");
		}
		
		WxUser wxUser = wxUserService.findById(dbData.getBuid());
		if(wxUser == null || !wxUser.isNormal()) {
			throw new BaseRuntimeException("OFFLINE_SITE", "当前站点'" + domain + "'已下线");
		}
		
		ThreadUtil.putIfAbsent(WeiZhanUtil.SITE_KEY, dbData);
		ThreadUtil.putIfAbsent(WeiZhanUtil.SITE_ID_KEY, dbData.getId());
		ThreadUtil.putIfAbsent(WeiZhanUtil.BUSER_KEY, wxUser);
		
		request.setAttribute(WeiZhanUtil.SITE_KEY, dbData);
		request.setAttribute(WeiZhanUtil.SITE_ID_KEY, dbData.getId());
		request.setAttribute(WeiZhanUtil.BUSER_KEY, wxUser);
		
		return true;
	}

}
