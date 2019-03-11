package com.fshl.xy.weizhan.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fshl.xy.weizhan.constant.Quality;
import com.fshl.xy.weizhan.entity.ConsultConfig;
import com.fshl.xy.weizhan.entity.ConsultLog;
import com.fshl.xy.weizhan.service.ConsultConfigService;
import com.fshl.xy.weizhan.service.ConsultLogService;
import com.fshl.xy.weizhan.utils.WeiZhanUtil;
import com.xyz.tools.common.bean.ResultModel;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.common.utils.ThreadUtil;

@RestController
@RequestMapping("wz")
public class ConsultController {
	
	@Resource
	private ConsultLogService consultLogService;
	@Resource
	private ConsultConfigService consultConfigService;

	@RequestMapping("doConsult")
	public ResultModel doConsult(ConsultLog updata) {
		int buid = ThreadUtil.get(WeiZhanUtil.BUSER_ID_KEY);
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		updata.setBuid(buid);
		updata.setSiteId(siteId);
		updata.setWxUid(ThreadUtil.getUidInt() == null ? 0 : ThreadUtil.getUidInt());
		
		ConsultLog dbData = consultLogService.loadByWxUid(siteId, updata.getWxUid());
		if(dbData != null) {
			updata.setStatus(CommonStatus.Normal);
			if(!Quality.A.equals(updata.getQuality())) {
				updata.setQuality(Quality.B);
			}
		}
		
		consultLogService.doSave(updata);
		
		return new ResultModel(true);
	}
	
	@RequestMapping("loadConsultConfig")
	public ResultModel loadConsultConfig() {
		int siteId = ThreadUtil.get(WeiZhanUtil.SITE_ID_KEY);
		
		List<ConsultConfig> dbDatas = consultConfigService.loadBySiteId(siteId);
		
		return new ResultModel(dbDatas);
	}
	
}
