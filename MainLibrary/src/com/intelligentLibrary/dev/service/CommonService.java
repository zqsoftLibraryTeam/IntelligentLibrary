package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;

public interface CommonService {
	/**
	 * ��ҳ��ȡͼ��
	 * @param int num �鱾�ܹ��ж��ٱ�
	 * @return
	 */
	public List<Book> getsubBook(int num);
	/**
	 * ��ȡ���ж���ͼ��
	 */
	public int getBookAllNum();
	/**
	 * ��ȡ��ǰ����ͼ������Topͼ��
	 * @param num ǰ������
	 * @return ͼ�鼯��
	 */
	public List<Book> getHotBooks(int num); 
}
