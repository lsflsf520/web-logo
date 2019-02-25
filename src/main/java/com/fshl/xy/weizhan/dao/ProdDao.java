package com.fshl.xy.weizhan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fshl.xy.weizhan.entity.Prod;
import com.fshl.xy.weizhan.vo.ProdListVO;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.xyz.tools.db.dao.IBaseDao;

@Repository
public interface ProdDao extends IBaseDao<Integer, Prod> {
    Integer insertReturnPK(Prod prod);
    
    List<ProdListVO> loadProdListVOByPage(Prod query, PageBounds pageBounds);
}