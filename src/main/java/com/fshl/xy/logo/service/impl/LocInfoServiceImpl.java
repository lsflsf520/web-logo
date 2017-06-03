package com.fshl.xy.logo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.logo.dao.impl.LocInfoDaoImpl;
import com.fshl.xy.logo.entity.LocInfo;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;
import com.ujigu.secure.db.service.impl.BaseServiceImpl;

@Service
public class LocInfoServiceImpl extends BaseServiceImpl<Integer, LocInfo> {
    @Resource
    private LocInfoDaoImpl locInfoDaoImpl;

    @Override
    protected BaseDaoImpl<Integer, LocInfo> getBaseDaoImpl() {
        return locInfoDaoImpl;
    }
}