package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.TrackPiaoDao;
import com.fshl.xy.logo.entity.TrackPiao;
import com.yisi.stiku.db.dao.BaseDao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class TrackPiaoDaoImpl extends BaseDaoImpl<String, TrackPiao> {
    @Resource
    private TrackPiaoDao trackPiaoDao;

    @Override
    protected BaseDao<String, TrackPiao> getProxyBaseDao() {
        return trackPiaoDao;
    }
}