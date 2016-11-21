package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.GupiaoDao;
import com.fshl.xy.logo.entity.Gupiao;
import com.yisi.stiku.db.dao.BaseDao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class GupiaoDaoImpl extends BaseDaoImpl<Integer, Gupiao> {
    @Resource
    private GupiaoDao gupiaoDao;

    @Override
    protected BaseDao<Integer, Gupiao> getProxyBaseDao() {
        return gupiaoDao;
    }
    
    public void deleteByDay(String day){
    	this.getSqlSessionTemplate().delete(this.getMapperNamespace() + ".deleteByDay", day);
    }
    
    public List<Gupiao> findGapPiaos(String day){
    	Map<String, String> paramMap = new HashMap<>();
    	paramMap.put("day", day);
    	return this.getSqlSessionTemplate().selectList(this.getMapperNamespace() + ".findGapPiaos", paramMap);
    }
}