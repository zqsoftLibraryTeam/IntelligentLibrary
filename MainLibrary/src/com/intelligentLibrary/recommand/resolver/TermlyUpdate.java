package com.intelligentLibrary.recommand.resolver;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.springframework.stereotype.Repository;


/**
 * 定期更新数据库的推荐信息
 * @author 12146
 *
 */

public class TermlyUpdate {
	//时间间隔（毫秒级别）
	private static final long PERIOD_DAY=24*60*60*1000;

	public TermlyUpdate() {
	
	}
	
	//增加或减少天数
	public Date addDay(Date date,int num)
	{
		Calendar startDT=Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
}
