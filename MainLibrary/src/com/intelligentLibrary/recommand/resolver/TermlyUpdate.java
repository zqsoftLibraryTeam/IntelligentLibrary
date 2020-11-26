package com.intelligentLibrary.recommand.resolver;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import org.springframework.stereotype.Repository;


/**
 * ���ڸ������ݿ���Ƽ���Ϣ
 * @author 12146
 *
 */

public class TermlyUpdate {
	//ʱ���������뼶��
	private static final long PERIOD_DAY=24*60*60*1000;

	public TermlyUpdate() {
	
	}
	
	//���ӻ��������
	public Date addDay(Date date,int num)
	{
		Calendar startDT=Calendar.getInstance();
		startDT.setTime(date);
		startDT.add(Calendar.DAY_OF_MONTH, num);
		return startDT.getTime();
	}
	
}
