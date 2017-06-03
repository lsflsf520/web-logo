package com.fshl.xy.logo.util;

import java.util.Date;

import com.ujigu.secure.common.utils.DateUtil;


public class PiaoUtil {

	public static String getLatestWeekDay(){
		Date weekday = new Date();
		
		return getLatestWeekDay(weekday);
	}
	
	public static String getLatestWeekDay(String dateStr){
		Date date = DateUtil.parseDate(dateStr);
		
		return getLatestWeekDay(date);
	}
	
	public static String getLatestWeekDay(Date date){
		Date monday = DateUtil.getWeekMondayDate(date);
		Date friday = DateUtil.timeAddByDays(monday, 4);
		if(date.getTime() > friday.getTime()){
			date = friday;
		}
		
		return DateUtil.getDateStr(date);
	}
	
}
