package com.intelligentLibrary.dev.utils;

import java.util.Comparator;

import com.intelligentLibrary.dev.domain.BookBorrowNum;

public abstract class ComparatorTopBook implements Comparator {
	
	/**
	 * int compare(Student o1, Student o2) ����һ���������͵����ͣ�
	 * ���ظ�����ʾ��o1 С��o2��
	 * ����0 ��ʾ��o1��o2��ȣ�
	 * ����������ʾ��o1����o2��
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
