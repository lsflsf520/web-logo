package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.SiteInfo;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface SiteInfoDao extends IBaseDao<Integer, SiteInfo> {
    int updateByPrimaryKeyWithBLOBs(SiteInfo record);

    Integer insertReturnPK(SiteInfo siteInfo);
}