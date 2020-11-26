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
	 * �����鱾Ψһ�ı��ɾ��������Ϣ
	 * @param bookid
	 */
	void deleteBorrowInfoByBookIdAndUserName(int bookid,String username);
	/**
	 * �����鱾������Ϣ
	 * @param orderbook
	 * @return
	 */
	public boolean saveOrderBook(OrderBook orderbook);
	/**
	 * ɾ��Ԥ��
	 * @param oid
	 * @return
	 */
	public boolean delOrderBook(int oid);
}
