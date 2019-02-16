package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.ConsultConfig;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultConfigDao extends IBaseDao<Integer, ConsultConfig> {
    Integer insertReturnPK(ConsultConfig consultConfig);
}