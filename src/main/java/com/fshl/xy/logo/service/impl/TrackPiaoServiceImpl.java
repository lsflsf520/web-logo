package com.fshl.xy.logo.service.impl;

//
//@Service
//public class TrackPiaoServiceImpl extends BaseServiceImpl<String, TrackPiao> {
//    @Resource
//    private TrackPiaoDaoImpl trackPiaoDaoImpl;
//
//    @Resource
//    private GupiaoDaoImpl gupiaoDaoImpl;
//
//    @Override
//    protected BaseDaoImpl<String, TrackPiao> getBaseDaoImpl() {
//        return trackPiaoDaoImpl;
//    }
//
//    public void addTrackPiao(String code){
//        Gupiao query = new Gupiao();
//        query.setCode(code);
//        Gupiao gupiao = gupiaoDaoImpl.findOneByEntity(query);
//        if(gupiao != null){
//            trackPiaoDaoImpl.addTrackPiao(code, gupiao.getName());
//        }
//    }
//
//    public List<TrackPiao> findTrackPiao(){
//        return trackPiaoDaoImpl.findTrackPiao();
//    }
//}