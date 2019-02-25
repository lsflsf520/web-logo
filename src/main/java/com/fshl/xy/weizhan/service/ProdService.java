package com.fshl.xy.weizhan.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.weizhan.dao.ProdDao;
import com.fshl.xy.weizhan.entity.Prod;
import com.fshl.xy.weizhan.vo.ProdListVO;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.xyz.tools.common.constant.CommonStatus;
import com.xyz.tools.db.bean.PageData;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class ProdService extends AbstractBaseService<Integer, Prod> {
    @Resource
    private ProdDao prodDao;

    @Override
    protected IBaseDao<Integer, Prod> getBaseDao() {
        return prodDao;
    }

    public Integer insertReturnPK(Prod prod) {
        prodDao.insertReturnPK(prod);
        return prod.getPK();
    }

    public Integer doSave(Prod prod) {
        if (prod.getPK() == null) {
            return this.insertReturnPK(prod);
        }
        this.update(prod);
        return prod.getPK();
    }
    
    public PageData<ProdListVO> loadProdListVOByPage(int siteId, int currPage) {
    	Prod query = new Prod();
    	query.setSiteId(siteId);
    	query.setStatus(CommonStatus.Normal);
    	
    	PageBounds bounds = new PageBounds(currPage, 10, Order.formString("priority.asc"));
    	
    	List<ProdListVO> dataPage = this.prodDao.loadProdListVOByPage(query, bounds);
    	
    	return new PageData<ProdListVO>((PageList<ProdListVO>)dataPage);
    }
}