package com.fshl.xy.logo.dao;

import com.fshl.xy.logo.entity.Company;
import com.yisi.stiku.db.dao.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyDao extends BaseDao<Integer, Company> {
}