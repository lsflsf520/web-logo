package com.fshl.xy.logo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fshl.xy.logo.dao.TrackPiaoDao;
import com.fshl.xy.logo.entity.TrackPiao;
import com.ujigu.secure.common.utils.DateUtil;
import com.ujigu.secure.db.dao.BaseDao;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;

@Repository
public class TrackPiaoDaoImpl extends BaseDaoImpl<String, TrackPiao> {
    @Resource
    private TrackPiaoDao trackPiaoDao;

    @Override
    protected BaseDao<String, TrackPiao> getProxyBaseDao() {
        return trackPiaoDao;
    }

    public void addTrackPiao(String code, String name){
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("code", code);
        paramMap.put("name", name);
        paramMap.put("currDay", DateUtil.getCurrentDateStr());
        this.getSqlSessionTemplate().update(this.getMapperNamespace() + ".addTrackPiao", paramMap);
    }

    public List<TrackPiao> findTrackPiao(){
        return this.getSqlSessionTemplate().selectList(this.getMapperNamespace() + ".findTrackPiao");
    }
}