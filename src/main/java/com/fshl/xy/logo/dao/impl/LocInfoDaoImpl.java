package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.LocInfoDao;
import com.fshl.xy.logo.entity.LocInfo;
import com.yisi.stiku.db.dao.BaseDao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class LocInfoDaoImpl extends BaseDaoImpl<Integer, LocInfo> {
    @Resource
    private LocInfoDao locInfoDao;

    @Override
    protected BaseDao<Integer, LocInfo> getProxyBaseDao() {
        return locInfoDao;
    }
}