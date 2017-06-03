package com.fshl.xy.logo.dao.impl;

import com.fshl.xy.logo.dao.CompanyDao;
import com.fshl.xy.logo.entity.Company;
import com.ujigu.secure.db.dao.BaseDao;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;

import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDaoImpl extends BaseDaoImpl<Integer, Company> {
    @Resource
    private CompanyDao companyDao;

    @Override
    protected BaseDao<Integer, Company> getProxyBaseDao() {
        return companyDao;
    }
}