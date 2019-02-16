package com.fshl.xy.weizhan.dao;

import com.fshl.xy.weizhan.entity.ImgText;
import com.xyz.tools.db.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgTextDao extends IBaseDao<Integer, ImgText> {
    int updateByPrimaryKeyWithBLOBs(ImgText record);

    Integer insertReturnPK(ImgText imgText);
}