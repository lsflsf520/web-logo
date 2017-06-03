package com.fshl.xy.logo.dao;

import com.fshl.xy.logo.entity.Latest5dPrice;
import com.ujigu.secure.db.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public interface Latest5dPriceDao extends BaseDao<String, Latest5dPrice> {
}