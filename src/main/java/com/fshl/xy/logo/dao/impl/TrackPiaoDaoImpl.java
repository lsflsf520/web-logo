package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.TrackPiaoDao;
import com.fshl.xy.logo.entity.TrackPiao;
import com.yisi.stiku.common.utils.DateUtil;
import com.yisi.stiku.db.dao.BaseDao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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