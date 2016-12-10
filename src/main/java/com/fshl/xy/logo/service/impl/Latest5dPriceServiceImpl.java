package com.fshl.xy.logo.service.impl;

import com.fshl.xy.logo.dao.impl.Latest5dPriceDaoImpl;
import com.fshl.xy.logo.entity.Latest5dPrice;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class Latest5dPriceServiceImpl extends BaseServiceImpl<String, Latest5dPrice> {
    @Resource
    private Latest5dPriceDaoImpl latest5dPriceDaoImpl;

    @Override
    protected BaseDaoImpl<String, Latest5dPrice> getBaseDaoImpl() {
        return latest5dPriceDaoImpl;
    }
}