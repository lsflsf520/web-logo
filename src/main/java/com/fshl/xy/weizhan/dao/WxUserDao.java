package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.WxUser;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface WxUserDao extends IBaseDao<Integer, WxUser> {
    Integer insertReturnPK(WxUser wxUser);
}