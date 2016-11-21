package com.fshl.xy.logo.util;

import java.util.Date;

import com.yisi.stiku.common.utils.DateUtil;

public class PiaoUtil {

	public static String getLatestWeekDay(){
		Date weekday = new Date();
		Date monday = DateUtil.getWeekMondayDate(weekday);
		Date friday = DateUtil.timeAddByDays(monday, 4);
		if(weekday.getTime() > friday.getTime()){
			weekday = friday;
		}
		
		return DateUtil.getDateStr(weekday);
	}
	
}
