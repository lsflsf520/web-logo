package com.fshl.xy.weizhan.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.weizhan.dao.ConsultConfigDao;
import com.fshl.xy.weizhan.entity.ConsultConfig;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class ConsultConfigService extends AbstractBaseService<Integer, ConsultConfig> {
    @Resource
    private ConsultConfigDao consultConfigDao;

    @Override
    protected IBaseDao<Integer, ConsultConfig> getBaseDao() {
        return consultConfigDao;
    }

    public Integer insertReturnPK(ConsultConfig consultConfig) {
        consultConfigDao.insertReturnPK(consultConfig);
        return consultConfig.getPK();
    }

    public Integer doSave(ConsultConfig consultConfig) {
        if (consultConfig.getPK() == null) {
            return this.insertReturnPK(consultConfig);
        }
        this.update(consultConfig);
        return consultConfig.getPK();
    }
    
    public List<ConsultConfig> loadBySiteId(int siteId) {
    	ConsultConfig query = new ConsultConfig();
    	query.setSiteId(siteId);
    	
    	return this.findByEntity(query, "priority.asc");
    }
}