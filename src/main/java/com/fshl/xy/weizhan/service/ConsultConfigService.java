package com.fshl.xy.weizhan.service;

import com.fshl.xy.weizhan.dao.ConsultConfigDao;
import com.fshl.xy.weizhan.entity.ConsultConfig;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

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
}