package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;

public interface CommonService {
	/**
	 * 分页获取图书
	 * @param int num 书本总共有多少本
	 * @return
	 */
	public List<Book> getsubBook(int num);
	/**
	 * 获取共有多少图书
	 */
	public int getBookAllNum();
	/**
	 * 获取当前借阅图书数量Top图书
	 * @param num 前多少名
	 * @return 图书集合
	 */
	public List<Book> getHotBooks(int num); 
}
