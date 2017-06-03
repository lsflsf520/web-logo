package com.fshl.xy.logo.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fshl.xy.logo.dao.impl.GupiaoDaoImpl;
import com.fshl.xy.logo.dao.impl.Latest5dPriceDaoImpl;
import com.fshl.xy.logo.entity.Gupiao;
import com.fshl.xy.logo.entity.Latest5dPrice;
import com.fshl.xy.logo.util.PiaoUtil;
import com.ujigu.secure.common.utils.DateUtil;
import com.ujigu.secure.db.dao.impl.BaseDaoImpl;
import com.ujigu.secure.db.service.impl.BaseServiceImpl;

@Service
public class Latest5dPriceServiceImpl extends BaseServiceImpl<String, Latest5dPrice> {
    @Resource
    private Latest5dPriceDaoImpl latest5dPriceDaoImpl;

    @Resource
    private GupiaoDaoImpl gupiaoDaoImpl;

    @Override
    protected BaseDaoImpl<String, Latest5dPrice> getBaseDaoImpl() {
        return latest5dPriceDaoImpl;
    }

    public List<Latest5dPrice> findDownByDays(int days){
        return latest5dPriceDaoImpl.findDownByDays(days);
    }

    public void resetLatest5dayPiaos(){
        List<String> days = genLatestWeekdays();
        List<Gupiao> piaos = gupiaoDaoImpl.findPiaoByDays(days);
        if(piaos != null && !piaos.isEmpty()){
            latest5dPriceDaoImpl.deleteAll();
            Map<String/*code*/, List<Gupiao>> code2PiaoMap = new HashMap<>();
            for(Gupiao piao : piaos){
                List<Gupiao> currPiaos = code2PiaoMap.get(piao.getCode());
                if(currPiaos == null){
                    currPiaos = new ArrayList<>();
                    code2PiaoMap.put(piao.getCode(), currPiaos);
                }
                currPiaos.add(piao);
            }

            //按照日期倒序
            List<Latest5dPrice> priceList = new ArrayList<>();
            for(Map.Entry<String,List<Gupiao>> entry : code2PiaoMap.entrySet()){
                Collections.sort(entry.getValue(), new Comparator<Gupiao>() {
                    @Override
                    public int compare(Gupiao o1, Gupiao o2) {
                        return Long.valueOf(o2.getDay().getTime()).compareTo(o1.getDay().getTime());
                    }
                });

                Latest5dPrice price = new Latest5dPrice();
                price.setCode(entry.getKey());
                Map<String, Gupiao> day2PiaoMap = groupByDay(entry.getValue());
                Gupiao piaoToday = day2PiaoMap.get(days.get(0));
                if(piaoToday != null){
                    price.setName(piaoToday.getName());
                    price.setOpenPrice(piaoToday.getOpenPrice());
                    price.setPrice(piaoToday.getCurrPrice());
                }

                Gupiao piao1Day = day2PiaoMap.get(days.get(1));
                if(piao1Day != null){
                    price.setPrice1(piao1Day.getCurrPrice());
                }
                Gupiao piao2Day = day2PiaoMap.get(days.get(2));
                if(piao2Day != null){
                    price.setPrice2(piao2Day.getCurrPrice());
                }
                Gupiao piao3Day = day2PiaoMap.get(days.get(3));
                if(piao3Day != null){
                    price.setPrice3(piao3Day.getCurrPrice());
                }
                Gupiao piao4Day = day2PiaoMap.get(days.get(4));
                if(piao4Day != null){
                    price.setPrice4(piao4Day.getCurrPrice());
                }

                priceList.add(price);
            }
            latest5dPriceDaoImpl.insertBatch(priceList);
        }

    }

    private Map<String/*dayStr*/, Gupiao> groupByDay(List<Gupiao> piaos){
        Map<String, Gupiao> day2PiaoMap = new HashMap<>();
        for(Gupiao piao : piaos){
            day2PiaoMap.put(piao.getDayStr(), piao);
        }

        return day2PiaoMap;
    }

    private List<String> genLatestWeekdays(){
        List<String> days = new ArrayList<>();
        String latestWeekDayStr = PiaoUtil.getLatestWeekDay();
        Date latestWeekDay = DateUtil.parseDate(latestWeekDayStr);

        days.add(latestWeekDayStr);
        for(int i = 1; i < 5; i++){
            String dayStr = DateUtil.timeAddToStr(latestWeekDay, i * -1, TimeUnit.DAYS);
            days.add(dayStr.substring(0, 10));
        }

        return days;
    }
}