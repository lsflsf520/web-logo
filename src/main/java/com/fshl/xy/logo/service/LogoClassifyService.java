package com.fshl.xy.logo.service;

import com.fshl.xy.logo.dao.LogoClassifyDao;
import com.fshl.xy.logo.entity.LogoClassify;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LogoClassifyService extends AbstractBaseService<Integer, LogoClassify> {
    @Resource
    private LogoClassifyDao logoClassifyDao;

    @Override
    protected IBaseDao<Integer, LogoClassify> getBaseDao() {
        return logoClassifyDao;
    }

    public Integer insertReturnPK(LogoClassify logoClassify) {
        logoClassifyDao.insertReturnPK(logoClassify);
        return logoClassify.getPK();
    }

    public Integer doSave(LogoClassify logoClassify) {
        if (logoClassify.getPK() == null) {
            return this.insertReturnPK(logoClassify);
        }
        this.update(logoClassify);
        return logoClassify.getPK();
    }
}