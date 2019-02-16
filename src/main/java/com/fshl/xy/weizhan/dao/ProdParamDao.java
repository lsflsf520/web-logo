package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.ProdParam;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdParamDao extends IBaseDao<Integer, ProdParam> {
    Integer insertReturnPK(ProdParam prodParam);
}