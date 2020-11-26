package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.BookSort;

public interface BookSortDAO extends BaseDAO<BookSort>{

	/**
	 * ��ȡ���ж�������
	 * @return ���ط�������
	 */
	public List<BookSort> getAllTopSortName();
	/**
	 * ���ݸ�����idѰ�������ӷ���
	 * @param pid ������id
	 * @return �������з�������
	 */
	public List<BookSort> getSubSortByParId(Integer pid);
	
	public BookSort getBookSortById(int id);
}
