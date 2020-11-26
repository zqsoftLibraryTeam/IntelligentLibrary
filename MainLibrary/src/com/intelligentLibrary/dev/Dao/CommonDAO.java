package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookBorrowNum;

public interface CommonDAO {

	/**
	 * ��ȡ�鱾�ļ�¼��
	 * @return
	 */
	public long getAllBookRecall();
	/**
	 * ��ҳ��ѯ�鱾
	 * @return
	 */
	public List<Book> getSubBooks(int num);
	/**
	 * ��ȡ�����鱾�Ľ�����
	 * @return ����һ��ObjectList
	 */
	public List<BookBorrowNum> getAllBookBorrowNumList();
	/**
	 * ͨ��ID�����鱾
	 * @param id
	 * @return
	 */
	public Book getBookById(Integer id);
}

