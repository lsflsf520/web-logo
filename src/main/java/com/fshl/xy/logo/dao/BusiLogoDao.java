package com.fshl.xy.logo.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.fshl.xy.logo.entity.BusiLogo;
import com.xyz.tools.db.dao.IBaseDao;

@Repository
public interface BusiLogoDao extends IBaseDao<Integer, BusiLogo> {
	
	/**
	 *
	 * @param saleUid 销售人员uid
	 * @param startDate
	 * @param endDate
	 * @param keyword
	 * @param status
	 * @return
	 */
	public List<BusiLogo> queryBusiLogo(@Param("saleUid") Integer saleUid, @Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("keyword") String keyword, @Param("ordType") Integer ordType, @Param("partner") String partner, @Param("status") Integer status);
	
}