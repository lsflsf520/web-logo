package com.fshl.xy.weizhan.service;

import com.fshl.xy.weizhan.dao.ProdDetailDao;
import com.fshl.xy.weizhan.entity.ProdDetail;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ProdDetailService extends AbstractBaseService<Integer, ProdDetail> {
    @Resource
    private ProdDetailDao prodDetailDao;

    @Override
    protected IBaseDao<Integer, ProdDetail> getBaseDao() {
        return prodDetailDao;
    }

    public Integer insertReturnPK(ProdDetail prodDetail) {
        prodDetailDao.insertReturnPK(prodDetail);
        return prodDetail.getPK();
    }

    public Integer doSave(ProdDetail prodDetail) {
        if (prodDetail.getPK() == null) {
            return this.insertReturnPK(prodDetail);
        }
        this.update(prodDetail);
        return prodDetail.getPK();
    }
}