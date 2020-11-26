package com.intelligentLibrary.dev.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.dev.Dao.AdminManageUserDAO;
import com.intelligentLibrary.dev.Dao.BorrowAndReturnDAO;
import com.intelligentLibrary.dev.base.BaseDAOImpl;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.OrderBook;
import com.intelligentLibrary.dev.domain.User;
@Repository
public class BorrowAndReturnDAOImpl extends BaseDAOImpl<BorrowInfo> implements BorrowAndReturnDAO {
	
	@Resource
	AdminManageBookDAO adminManageBookDAO;
	@Resource
	AdminManageUserDAO adminManageUserDAO;
	
	@Override
	public List<Book> getBooksWhichUserHaveRead(User user) {
		List<BorrowInfo> borrowinfos = getSession().createQuery("from BorrowInfo where ilUser = ?").setParameter(0, user).list();
		List<Book> returns=new ArrayList<Book>();
		for(BorrowInfo borrowinfo:borrowinfos)
		{
			
			returns.add(borrowinfo.getIlBook());
			
		}
		return returns;
		
	}

	@Override
	public void deleteBorrowInfoByBookIdAndUserName(int bookid, String username) {
		User user = adminManageUserDAO.getUserByUserName(username);
		getSession().createSQLQuery("delete from il_borrowinfo where bookid = ? and userid = ?").setParameter(0, bookid).setParameter(1, user.getUserid()).
		executeUpdate();
		
	}

	@Override
	public boolean saveOrderBook(OrderBook orderbook) {
		try {
			getSession().save(orderbook);
			return true;
		} catch (Exception e) {
			System.out.println("‘§∂® È±æ ß∞‹");
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean delOrderBook(int oid) {
		try {
			Object obj = getSession().get(OrderBook.class, oid);
			getSession().delete(obj);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}





}
