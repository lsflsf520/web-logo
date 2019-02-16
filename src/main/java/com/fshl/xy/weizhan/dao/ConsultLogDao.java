package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.ConsultLog;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultLogDao extends IBaseDao<Integer, ConsultLog> {
    Integer insertReturnPK(ConsultLog consultLog);
}