package com.fshl.xy.logo.service.impl;

//@Service
//public class ZijinServiceImpl extends BaseServiceImpl<Integer, Zijin> {
//    @Resource
//    private ZijinDaoImpl zijinDaoImpl;
//
//    @Override
//    protected BaseDaoImpl<Integer, Zijin> getBaseDaoImpl() {
//        return zijinDaoImpl;
//    }
//    
//    public void deleteByDay(String day){
//    	zijinDaoImpl.deleteByDay(day);
//    }
//    
//    public List<Zijin> findByCodeOrName(String codeOrName){
//    	return zijinDaoImpl.findByCodeOrName(codeOrName);
//    }
//    
//    public void statLatestMainPureIn(String latestWeekDay){
//    	zijinDaoImpl.deleteLatestMainPureInByDay(latestWeekDay);
//    	zijinDaoImpl.insertLatestMainPureIn(latestWeekDay, PiaoConst.LATEST_WEEK_DAY_CNT); //统计最近7天的主力净流入
//    	zijinDaoImpl.insertLatestMainPureIn(latestWeekDay, PiaoConst.LATEST_MONTH_DAY_CNT);//统计最近30天的主力净流入
//    }
//    
//    public List<MainPureIn> findLatestMainPureIn(String codeOrName){
//    	List<MainPureIn> pureIns = zijinDaoImpl.findLatestMainPureIn(codeOrName);
//    	return pureIns;
//    }
//}