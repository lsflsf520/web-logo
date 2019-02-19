package com.fshl.xy.weizhan.service;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.weizhan.dao.ConsultLogDao;
import com.fshl.xy.weizhan.entity.ConsultLog;
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
    	consultLog.setCreateTime(new Date());
        consultLogDao.insertReturnPK(consultLog);
        return consultLog.getPK();
    }

    public Integer doSave(ConsultLog consultLog) {
    	consultLog.setLastUptime(new Date());
        if (consultLog.getPK() == null) {
            return this.insertReturnPK(consultLog);
        }
        this.update(consultLog);
        return consultLog.getPK();
    }
}