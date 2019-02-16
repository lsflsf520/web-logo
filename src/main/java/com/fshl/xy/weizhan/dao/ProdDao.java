package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.Prod;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdDao extends IBaseDao<Integer, Prod> {
    Integer insertReturnPK(Prod prod);
}