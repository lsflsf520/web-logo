package com.fshl.xy.logo.service.impl;

import com.fshl.xy.logo.dao.impl.GupiaoDaoImpl;
import com.fshl.xy.logo.dao.impl.TrackPiaoDaoImpl;
import com.fshl.xy.logo.entity.Gupiao;
import com.fshl.xy.logo.entity.TrackPiao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TrackPiaoServiceImpl extends BaseServiceImpl<String, TrackPiao> {
    @Resource
    private TrackPiaoDaoImpl trackPiaoDaoImpl;

    @Resource
    private GupiaoDaoImpl gupiaoDaoImpl;

    @Override
    protected BaseDaoImpl<String, TrackPiao> getBaseDaoImpl() {
        return trackPiaoDaoImpl;
    }

    public void addTrackPiao(String code){
        Gupiao query = new Gupiao();
        query.setCode(code);
        Gupiao gupiao = gupiaoDaoImpl.findOneByEntity(query);
        if(gupiao != null){
            trackPiaoDaoImpl.addTrackPiao(code, gupiao.getName());
        }
    }

    public List<TrackPiao> findTrackPiao(){
        return trackPiaoDaoImpl.findTrackPiao();
    }
}