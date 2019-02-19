package com.fshl.xy.weizhan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.weizhan.dao.ProdParamDao;
import com.fshl.xy.weizhan.entity.ProdParam;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

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
    
    public List<ProdParam> loadByProdId(int prodId) {
    	ProdParam query = new ProdParam();
    	query.setProdId(prodId);
    	
    	return this.findByEntity(query, "priority.asc");
    }
    
    public List<ProdParam> loadMainProdParams(Integer... prodIds) {
    	if(prodIds == null || prodIds.length <= 0) {
    		return new ArrayList<>();
    	}
    	ProdParam query = new ProdParam();
    	query.addQueryParam("prodIds", prodIds);
    	
    	return this.findByEntity(query, "priority.asc");
    }
    
    public Map<Integer/*prodId*/, List<ProdParam>> loadMainProdParamMap(Integer... prodIds) {
    	Map<Integer, List<ProdParam>> paramMap = new HashMap<>();
    	List<ProdParam> params = loadMainProdParams(prodIds);
    	for(ProdParam param : params) {
    		List<ProdParam> currParams = paramMap.get(param.getProdId());
    		if(currParams == null) {
    			currParams = new ArrayList<>();
    			paramMap.put(param.getProdId(), currParams);
    		}
    		
    		currParams.add(param);
    	}
    	
    	return paramMap;
    }
}