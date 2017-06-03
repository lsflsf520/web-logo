package com.fshl.xy.logo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.logo.dao.impl.GupiaoDaoImpl;
import com.fshl.xy.logo.dao.impl.TrackPiaoDaoImpl;
import com.fshl.xy.logo.entity.Gupiao;
import com.fshl.xy.logo.entity.TrackPiao;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;
import com.ujigu.secure.db.service.impl.BaseServiceImpl;

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