package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.BusiLogoDao;
import com.fshl.xy.logo.entity.BusiLogo;
import com.yisi.stiku.db.dao.BaseDao;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class BusiLogoDaoImpl extends BaseDaoImpl<Integer, BusiLogo> {
    @Resource
    private BusiLogoDao busiLogoDao;

    @Override
    protected BaseDao<Integer, BusiLogo> getProxyBaseDao() {
        return busiLogoDao;
    }
}