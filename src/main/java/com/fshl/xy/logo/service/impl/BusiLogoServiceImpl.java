package com.fshl.xy.logo.service.impl;

import com.fshl.xy.logo.dao.BusiLogoDao;
import com.fshl.xy.logo.dao.impl.BusiLogoDaoImpl;
import com.fshl.xy.logo.entity.BusiLogo;
import com.yisi.stiku.common.utils.DateUtil;
import com.yisi.stiku.db.dao.impl.BaseDaoImpl;
import com.yisi.stiku.db.service.impl.BaseServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class BusiLogoServiceImpl extends BaseServiceImpl<Integer, BusiLogo> {
    @Resource
    private BusiLogoDaoImpl busiLogoDaoImpl;
    
    @Resource
    private BusiLogoDao busiLogoDao;

    @Override
    protected BaseDaoImpl<Integer, BusiLogo> getBaseDaoImpl() {
        return busiLogoDaoImpl;
    }
    
    public List<BusiLogo> queryBusiLogo(String yearMonth, String keyword, Integer status){
    	Date startDate = null;
    	Date endDate = null;
    	if(StringUtils.isNotBlank(yearMonth)){
    		String startDateStr = yearMonth + "-01";
    			
    		startDate = DateUtil.parseDate(startDateStr);
    		endDate = DateUtil.timeAddByMonth(startDate, 1);
    	}
    	
    	
    	return queryBusiLogo(startDate, endDate, keyword, status);
    }
    
    public List<BusiLogo> queryBusiLogo(Date startDate, Date endDate, String keyword, Integer status){
    	if(StringUtils.isNotBlank(keyword)){
    		keyword = "%" + keyword + "%";
    	}else{
    		keyword = null;
		}
    	
    	return busiLogoDao.queryBusiLogo(startDate, endDate, keyword, status);
    }
}