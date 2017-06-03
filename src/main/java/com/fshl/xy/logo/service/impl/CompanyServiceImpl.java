package com.fshl.xy.logo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.logo.dao.impl.CompanyDaoImpl;
import com.fshl.xy.logo.entity.Company;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;
import com.ujigu.secure.db.service.impl.BaseServiceImpl;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Integer, Company> {
    @Resource
    private CompanyDaoImpl companyDaoImpl;

    @Override
    protected BaseDaoImpl<Integer, Company> getBaseDaoImpl() {
        return companyDaoImpl;
    }
}