package com.fshl.xy.logo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.logo.dao.impl.GupiaoDaoImpl;
import com.fshl.xy.logo.entity.Gupiao;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;
import com.ujigu.secure.db.service.impl.BaseServiceImpl;

@Service
public class GupiaoServiceImpl extends BaseServiceImpl<Integer, Gupiao> {
    @Resource
    private GupiaoDaoImpl gupiaoDaoImpl;

    @Override
    protected BaseDaoImpl<Integer, Gupiao> getBaseDaoImpl() {
        return gupiaoDaoImpl;
    }
    
    public void deleteByDay(String day){
    	gupiaoDaoImpl.deleteByDay(day);
    }
    
    public List<Gupiao> findGapPiaos(String day){
    	return gupiaoDaoImpl.findGapPiaos(day);
    }

}