package com.fshl.xy.logo.dao;

import com.fshl.xy.logo.entity.LogoClassify;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface LogoClassifyDao extends IBaseDao<Integer, LogoClassify> {
    Integer insertReturnPK(LogoClassify logoClassify);
}