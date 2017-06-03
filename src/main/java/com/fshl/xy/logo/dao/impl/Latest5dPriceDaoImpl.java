package com.fshl.xy.logo.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.fshl.xy.logo.dao.Latest5dPriceDao;
import com.fshl.xy.logo.entity.Latest5dPrice;
import com.ujigu.secure.db.dao.BaseDao;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;

@Repository
public class Latest5dPriceDaoImpl extends BaseDaoImpl<String, Latest5dPrice> {
    @Resource
    private Latest5dPriceDao latest5dPriceDao;

    @Override
    protected BaseDao<String, Latest5dPrice> getProxyBaseDao() {
        return latest5dPriceDao;
    }

    public void deleteAll(){
        this.getSqlSessionTemplate().update(this.getMapperNamespace() + ".deleteAll");
    }

    public List<Latest5dPrice> findDownByDays(int days){
        if(days > 4){
            days = 4;
        }else if(days < 1){
            days = 1;
        }

        return this.getSqlSessionTemplate().selectList(this.getMapperNamespace() +".findDownBy"+ days + "Day");
    }
}