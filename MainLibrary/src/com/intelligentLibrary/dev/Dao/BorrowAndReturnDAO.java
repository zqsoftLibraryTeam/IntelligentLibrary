package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.OrderBook;
import com.intelligentLibrary.dev.domain.User;

public interface BorrowAndReturnDAO extends BaseDAO<BorrowInfo>{
	List<Book> getBooksWhichUserHaveRead(User user);
	
	/**
	 * 根据书本唯一的编号删除借阅信息
	 * @param bookid
	 */
	void deleteBorrowInfoByBookIdAndUserName(int bookid,String username);
	/**
	 * 保存书本订阅信息
	 * @param orderbook
	 * @return
	 */
	public boolean saveOrderBook(OrderBook orderbook);
	/**
	 * 删除预定
	 * @param oid
	 * @return
	 */
	public boolean delOrderBook(int oid);
}
