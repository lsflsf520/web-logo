package com.fshl.xy.logo.dao.impl;

//
//@Repository
//public class ZijinDaoImpl extends BaseDaoImpl<Integer, Zijin> {
//    @Resource
//    private ZijinDao zijinDao;
//
//    @Override
//    protected BaseDao<Integer, Zijin> getProxyBaseDao() {
//        return zijinDao;
//    }
//    
//    public void deleteByDay(String day){
//    	this.getSqlSessionTemplate().delete(this.getMapperNamespace() + ".deleteByDay", day);
//    }
//    
//    public void deleteLatestMainPureInByDay(String latestWeekDay){
//    	this.getSqlSessionTemplate().delete(this.getMapperNamespace() + ".deleteLatestMainPureInByDay", latestWeekDay);
//    }
//    
//    public void insertLatestMainPureIn(String latestWeekDay, int daysAgo){
//    	Map<String, Object> paramMap = new HashMap<>();
//    	paramMap.put("latestWeekDay", latestWeekDay);
//    	paramMap.put("dayCnt", daysAgo);
//    	this.getSqlSessionTemplate().insert(this.getMapperNamespace() + ".insertLatestMainPureIn", paramMap);
//    }
//    
//    public List<Zijin> findByCodeOrName(String codeOrName){
//    	Map<String, String> paramMap = new HashMap<>();
//    	paramMap.put("codeOrName", codeOrName);
//    	return this.getSqlSessionTemplate().selectList(this.getMapperNamespace() + ".findByCodeOrName", paramMap);
//    }
//    
//    public List<MainPureIn> findLatestMainPureIn(String codeOrName){
//    	Map<String, String> paramMap = new HashMap<>();
//    	paramMap.put("codeOrName", codeOrName);
//    	return this.getSqlSessionTemplate().selectList(this.getMapperNamespace() + ".findLatestMainPureIn", paramMap);
//    }
//    
//}