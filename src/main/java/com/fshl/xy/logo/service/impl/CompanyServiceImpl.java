package com.fshl.xy.logo.service.impl;

import com.fshl.xy.logo.dao.impl.CompanyDaoImpl;
import com.fshl.xy.logo.entity.Company;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Integer, Company> {
    @Resource
    private CompanyDaoImpl companyDaoImpl;

    @Override
    protected BaseDaoImpl<Integer, Company> getBaseDaoImpl() {
        return companyDaoImpl;
    }
}