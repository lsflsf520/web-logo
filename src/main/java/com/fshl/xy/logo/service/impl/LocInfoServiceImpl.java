package com.fshl.xy.logo.service.impl;

import com.fshl.xy.logo.dao.impl.LocInfoDaoImpl;
import com.fshl.xy.logo.entity.LocInfo;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class LocInfoServiceImpl extends BaseServiceImpl<Integer, LocInfo> {
    @Resource
    private LocInfoDaoImpl locInfoDaoImpl;

    @Override
    protected BaseDaoImpl<Integer, LocInfo> getBaseDaoImpl() {
        return locInfoDaoImpl;
    }
}