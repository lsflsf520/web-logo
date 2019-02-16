package com.fshl.xy.weizhan.service;

import com.fshl.xy.weizhan.dao.ConsultLogDao;
import com.fshl.xy.weizhan.entity.ConsultLog;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ConsultLogService extends AbstractBaseService<Integer, ConsultLog> {
    @Resource
    private ConsultLogDao consultLogDao;

    @Override
    protected IBaseDao<Integer, ConsultLog> getBaseDao() {
        return consultLogDao;
    }

    public Integer insertReturnPK(ConsultLog consultLog) {
        consultLogDao.insertReturnPK(consultLog);
        return consultLog.getPK();
    }

    public Integer doSave(ConsultLog consultLog) {
        if (consultLog.getPK() == null) {
            return this.insertReturnPK(consultLog);
        }
        this.update(consultLog);
        return consultLog.getPK();
    }
}