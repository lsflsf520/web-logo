package com.fshl.xy.logo.service.impl;

import com.fshl.xy.logo.dao.impl.TrackPiaoDaoImpl;
import com.fshl.xy.logo.entity.TrackPiao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class TrackPiaoServiceImpl extends BaseServiceImpl<String, TrackPiao> {
    @Resource
    private TrackPiaoDaoImpl trackPiaoDaoImpl;

    @Override
    protected BaseDaoImpl<String, TrackPiao> getBaseDaoImpl() {
        return trackPiaoDaoImpl;
    }
}