package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.Latest5dPriceDao;
import com.fshl.xy.logo.entity.Latest5dPrice;
import com.yisi.stiku.db.dao.BaseDao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class Latest5dPriceDaoImpl extends BaseDaoImpl<String, Latest5dPrice> {
    @Resource
    private Latest5dPriceDao latest5dPriceDao;

    @Override
    protected BaseDao<String, Latest5dPrice> getProxyBaseDao() {
        return latest5dPriceDao;
    }
}