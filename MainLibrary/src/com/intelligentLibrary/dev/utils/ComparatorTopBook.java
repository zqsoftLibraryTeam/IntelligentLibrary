package com.intelligentLibrary.dev.utils;

import java.util.Comparator;

import com.intelligentLibrary.dev.domain.BookBorrowNum;

public abstract class ComparatorTopBook implements Comparator {
	
	/**
	 * int compare(Student o1, Student o2) 返回一个基本类型的整型，
	 * 返回负数表示：o1 小于o2，
	 * 返回0 表示：o1和o2相等，
	 * 返回正数表示：o1大于o2。
	 */
	@Override
	public int compare(Object o1, Object o2) {
		BookBorrowNum Tone = (BookBorrowNum)o1;
		BookBorrowNum Ttwo = (BookBorrowNum)o2;
		if(Tone.getNum()>Ttwo.getNum()){
			return 1;
		}else if(Tone.getNum()==Ttwo.getNum()){
//			return Tone.getBookname().compareTo(Ttwo.getBookname());
			return 0;
		}
		return -1;
	}

}
