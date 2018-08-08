package com.fshl.xy.logo.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.fshl.xy.logo.dao.BusiLogoDao;
import com.fshl.xy.logo.entity.BusiLogo;
import com.xyz.tools.common.utils.DateUtil;
import com.xyz.tools.db.dao.IBaseDao;
import com.xyz.tools.db.service.AbstractBaseService;

@Service
public class BusiLogoService extends AbstractBaseService<Integer, BusiLogo> {
    
    @Resource
    private BusiLogoDao busiLogoDao;
    
    @Override
    protected IBaseDao<Integer, BusiLogo> getBaseDao() {
    	return busiLogoDao;
    }
    
    public List<BusiLogo> queryBusiLogo(String yearMonth, String keyword, String partner, Integer status){
    	Date startDate = null;
    	Date endDate = null;
    	if(StringUtils.isNotBlank(yearMonth)){
    		String startDateStr = yearMonth + "-01";
    			
    		startDate = DateUtil.parseDate(startDateStr);
    		endDate = DateUtil.timeAddByMonth(startDate, 1);
    	}
    	
    	
    	return queryBusiLogo(startDate, endDate, keyword, partner, status);
    }
    
    public List<BusiLogo> queryBusiLogo(Date startDate, Date endDate, String keyword, String partner, Integer status){
    	if(StringUtils.isNotBlank(keyword)){
    		keyword = "%" + keyword + "%";
    	}else{
    		keyword = null;
		}
    	if(StringUtils.isBlank(partner)){
    		partner = null;
    	}
    	return busiLogoDao.queryBusiLogo(startDate, endDate, keyword, partner, status);
    }
}