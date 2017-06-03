package com.fshl.xy.logo.dao.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fshl.xy.logo.dao.LocInfoDao;
import com.fshl.xy.logo.entity.LocInfo;
import com.ujigu.secure.db.dao.BaseDao;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;

@Repository
public class LocInfoDaoImpl extends BaseDaoImpl<Integer, LocInfo> {
    @Resource
    private LocInfoDao locInfoDao;

    @Override
    protected BaseDao<Integer, LocInfo> getProxyBaseDao() {
        return locInfoDao;
    }
}