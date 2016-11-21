package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.ZijinDao;
import com.fshl.xy.logo.entity.Zijin;
import com.yisi.stiku.db.dao.BaseDao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class ZijinDaoImpl extends BaseDaoImpl<Integer, Zijin> {
    @Resource
    private ZijinDao zijinDao;

    @Override
    protected BaseDao<Integer, Zijin> getProxyBaseDao() {
        return zijinDao;
    }
    
    public void deleteByDay(String day){
    	this.getSqlSessionTemplate().delete(this.getMapperNamespace() + ".deleteByDay", day);
    }
    
    public List<Zijin> findByCodeOrName(String codeOrName){
    	Map<String, String> paramMap = new HashMap<>();
    	paramMap.put("codeOrName", codeOrName);
    	return this.getSqlSessionTemplate().selectList(this.getMapperNamespace() + ".findByCodeOrName", paramMap);
    }
    
}