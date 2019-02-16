package com.fshl.xy.weizhan.service;

import com.fshl.xy.weizhan.dao.ProdParamDao;
import com.fshl.xy.weizhan.entity.ProdParam;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProdParamService extends AbstractBaseService<Integer, ProdParam> {
    @Resource
    private ProdParamDao prodParamDao;

    @Override
    protected IBaseDao<Integer, ProdParam> getBaseDao() {
        return prodParamDao;
    }

    public Integer insertReturnPK(ProdParam prodParam) {
        prodParamDao.insertReturnPK(prodParam);
        return prodParam.getPK();
    }

    public Integer doSave(ProdParam prodParam) {
        if (prodParam.getPK() == null) {
            return this.insertReturnPK(prodParam);
        }
        this.update(prodParam);
        return prodParam.getPK();
    }
}