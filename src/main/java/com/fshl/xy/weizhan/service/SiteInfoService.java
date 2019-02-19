package com.fshl.xy.weizhan.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.weizhan.dao.SiteInfoDao;
import com.fshl.xy.weizhan.entity.SiteInfo;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

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
    
    /**
     * 根据域名返回站点信息
     * @return
     */
    public SiteInfo loadByDomain(String domain) {
    	SiteInfo query = new SiteInfo();
    	query.setDomain(domain);
    	
    	return this.findOne(query);
    }
}