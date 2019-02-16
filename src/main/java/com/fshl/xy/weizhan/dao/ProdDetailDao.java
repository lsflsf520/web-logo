package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.ProdDetail;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdDetailDao extends IBaseDao<Integer, ProdDetail> {
    int updateByPrimaryKeyWithBLOBs(ProdDetail record);

    Integer insertReturnPK(ProdDetail prodDetail);
}