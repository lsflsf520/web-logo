package com.fshl.xy.weizhan.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.weizhan.constant.Quality;
import com.fshl.xy.weizhan.dao.ConsultLogDao;
import com.fshl.xy.weizhan.entity.ConsultLog;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class ConsultLogService extends AbstractBaseService<Integer, ConsultLog> {
    @Resource
    private ConsultLogDao consultLogDao;

    @Override
    protected IBaseDao<Integer, ConsultLog> getBaseDao() {
        return consultLogDao;
    }

    public Integer insertReturnPK(ConsultLog consultLog) {
    	consultLog.setQuality(Quality.B);
    	consultLog.setStatus(CommonStatus.Normal);
    	consultLog.setCreateTime(new Date());
    	consultLog.setLastUptime(new Date());
        consultLogDao.insertReturnPK(consultLog);
        return consultLog.getPK();
    }
    
    @Override
    public boolean update(ConsultLog t) {
    	t.setLastUptime(new Date());
    	return super.update(t);
    }

    public Integer doSave(ConsultLog consultLog) {
        if (consultLog.getPK() == null) {
            return this.insertReturnPK(consultLog);
        }
        this.update(consultLog);
        return consultLog.getPK();
    }
    
    public ConsultLog loadByWxUid(int siteId, int wxUid) {
    	if(wxUid > 0) {
    		ConsultLog query = new ConsultLog();
    		query.setWxUid(wxUid);
    		query.setSiteId(siteId);
    		
    		return this.findOne(query);
    	}
    	return null;
    }
    
}