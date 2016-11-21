package com.fshl.xy.logo.service.impl;

import com.fshl.xy.logo.dao.impl.ZijinDaoImpl;
import com.fshl.xy.logo.entity.Zijin;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ZijinServiceImpl extends BaseServiceImpl<Integer, Zijin> {
    @Resource
    private ZijinDaoImpl zijinDaoImpl;

    @Override
    protected BaseDaoImpl<Integer, Zijin> getBaseDaoImpl() {
        return zijinDaoImpl;
    }
    
    public void deleteByDay(String day){
    	zijinDaoImpl.deleteByDay(day);
    }
    
    public List<Zijin> findByCodeOrName(String codeOrName){
    	return zijinDaoImpl.findByCodeOrName(codeOrName);
    }
}