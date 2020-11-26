package com.intelligentLibrary.dev.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.dev.Dao.AdminManageUserDAO;
import com.intelligentLibrary.dev.Dao.BorrowAndReturnDAO;
import com.intelligentLibrary.dev.Dao.UserDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.OrderBook;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	UserDAO userDAO;

	@Resource
	BorrowAndReturnDAO borrowAndReturnDAO;

	@Resource
	AdminManageBookDAO adminManageBookDAO;

	@Resource
	AdminManageUserDAO adminManageUserDAO;

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBookBywhat(String bywhat, String thevalue) {
		if ("author".equals(bywhat)) {
			if (userDAO.findBookByauthor(thevalue) == null) {
				return Collections.EMPTY_LIST;
			} else {
				return userDAO.findBookByauthor(thevalue);
			}
		} else if ("bookname".equals(bywhat)) {
			if (userDAO.findBookByBookName(thevalue) == null) {
				return Collections.EMPTY_LIST;
			} else {
				return userDAO.findBookByBookName(thevalue);
			}
		} else if ("isbn".equals(bywhat)) {
			if (userDAO.findBookByIsbn(thevalue) == null) {
				return Collections.EMPTY_LIST;
			} else {
				return userDAO.findBookByIsbn(thevalue);
			}
		}
		return Collections.emptyList();

	}

	@Override
	public boolean borrowBook(Integer userid, String bookid) {
		if (userid == 0) {
			return false;
		}
		BorrowInfo borrowInfo = new BorrowInfo(
				  adminManageUserDAO.getById(userid),
				adminManageBookDAO.getById(Integer.parseInt(bookid)),
				new Date(), new Date(new Date().getTime()
						+ adminManageUserDAO.getById(userid).getTimelimit()
						* 24L * 60L * 60L * 1000L));
		borrowAndReturnDAO.save(borrowInfo);
		return true;
	}

	@Override
	public List<BorrowInfo> borrowInfo(User user) {
		return userDAO.UserBorrowInfo(user);
	}

	@Override
	public boolean changepassword(String oldpass, String newpass,User user) {
		if(user.getPassword().equals(oldpass)){
			user.setPassword(newpass);
			userDAO.update(user);
			return true;
		}
		return false;
	}

	@Override
	public List<BorrowInfo> BookborrowInfo(String bookid) {
		return userDAO.BookBorrowInfo(userDAO.getBook(bookid));
	}

	@Override
	public Book getBook(String bookid) {
		return userDAO.getBook(bookid);
	}

	@Override
	public boolean orderBook(int uid, String bid) {
		if (uid == 0) {
			return false;
		}
		OrderBook orderInfo = new OrderBook(
				  adminManageUserDAO.getById(uid),
				adminManageBookDAO.getById(Integer.parseInt(bid)),
				new Date(), new Date(new Date().getTime()
						+ adminManageUserDAO.getById(uid).getTimelimit()
						* 24L * 60L * 60L * 1000L), true);
		borrowAndReturnDAO.saveOrderBook(orderInfo);
		return true;
	}

	@Override
	public boolean cancelorderbook(int oid) {
		return borrowAndReturnDAO.delOrderBook(oid);
	}

}
