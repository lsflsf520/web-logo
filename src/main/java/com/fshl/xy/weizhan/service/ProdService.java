package com.fshl.xy.weizhan.service;

import com.fshl.xy.weizhan.dao.ProdDao;
import com.fshl.xy.weizhan.entity.Prod;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

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
}