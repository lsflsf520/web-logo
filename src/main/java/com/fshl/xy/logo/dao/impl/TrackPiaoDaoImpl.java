package com.fshl.xy.logo.dao.impl;

//@Repository
//public class TrackPiaoDaoImpl extends BaseDaoImpl<String, TrackPiao> {
//    @Resource
//    private TrackPiaoDao trackPiaoDao;
//
//    @Override
//    protected BaseDao<String, TrackPiao> getProxyBaseDao() {
//        return trackPiaoDao;
//    }
//
//    public void addTrackPiao(String code, String name){
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("code", code);
//        paramMap.put("name", name);
//        paramMap.put("currDay", DateUtil.getCurrentDateStr());
//        this.getSqlSessionTemplate().update(this.getMapperNamespace() + ".addTrackPiao", paramMap);
//    }
//
//    public List<TrackPiao> findTrackPiao(){
//        return this.getSqlSessionTemplate().selectList(this.getMapperNamespace() + ".findTrackPiao");
//    }
//}