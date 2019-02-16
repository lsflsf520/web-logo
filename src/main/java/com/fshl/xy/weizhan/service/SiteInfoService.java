package com.fshl.xy.weizhan.service;

import com.fshl.xy.weizhan.dao.SiteInfoDao;
import com.fshl.xy.weizhan.entity.SiteInfo;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SiteInfoService extends AbstractBaseService<Integer, SiteInfo> {
    @Resource
    private SiteInfoDao siteInfoDao;

    @Override
    protected IBaseDao<Integer, SiteInfo> getBaseDao() {
        return siteInfoDao;
    }

    public Integer insertReturnPK(SiteInfo siteInfo) {
        siteInfoDao.insertReturnPK(siteInfo);
        return siteInfo.getPK();
    }

    public Integer doSave(SiteInfo siteInfo) {
        if (siteInfo.getPK() == null) {
            return this.insertReturnPK(siteInfo);
        }
        this.update(siteInfo);
        return siteInfo.getPK();
    }
}