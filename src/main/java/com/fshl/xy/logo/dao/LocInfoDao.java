package com.fshl.xy.logo.dao;

import com.fshl.xy.logo.entity.LocInfo;
import com.ujigu.secure.db.dao.BaseDao;

import org.springframework.stereotype.Repository;

@Repository
public interface LocInfoDao extends BaseDao<Integer, LocInfo> {
}