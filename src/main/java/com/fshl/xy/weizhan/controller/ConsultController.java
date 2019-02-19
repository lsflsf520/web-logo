package com.fshl.xy.weizhan.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fshl.xy.weizhan.entity.ConsultLog;
import com.fshl.xy.weizhan.service.ConsultLogService;
import com.fshl.xy.weizhan.utils.WeiZhanUtil;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.utils.ThreadUtil;

@Controller
@RequestMapping("wz")
public class ConsultController {
	
	@Resource
	private ConsultLogService consultLogService;

	@RequestMapping("doConsult")
	public ResultModel doConsult(ConsultLog updata) {
		int buid = ThreadUtil.get(WeiZhanUtil.BUSER_ID_KEY);
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		updata.setBuid(buid);
		updata.setSiteId(siteId);
		updata.setUid(ThreadUtil.getUidInt());
		
		consultLogService.doSave(updata);
		
		return new ResultModel(true);
	}
	
}
