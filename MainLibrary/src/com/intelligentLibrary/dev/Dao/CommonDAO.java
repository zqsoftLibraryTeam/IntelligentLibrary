package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookBorrowNum;

public interface CommonDAO {

	/**
	 * 获取书本的记录数
	 * @return
	 */
	public long getAllBookRecall();
	/**
	 * 分页查询书本
	 * @return
	 */
	public List<Book> getSubBooks(int num);
	/**
	 * 获取所有书本的借阅数
	 * @return 返回一个ObjectList
	 */
	public List<BookBorrowNum> getAllBookBorrowNumList();
	/**
	 * 通过ID查找书本
	 * @param id
	 * @return
	 */
	public Book getBookById(Integer id);
}

