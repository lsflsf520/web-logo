package com.fshl.xy.logo.dao;

import com.fshl.xy.logo.entity.BusiLogo;
import com.yisi.stiku.db.dao.BaseDao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BusiLogoDao extends BaseDao<Integer, BusiLogo> {
	
	/**
	 * 
	 * @param startDate
	 * @param endDate
	 * @param keyword
	 * @param status
	 * @return
	 */
	public List<BusiLogo> queryBusiLogo(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("keyword") String keyword, @Param("partner") String partner, @Param("status") Integer status);
	
}