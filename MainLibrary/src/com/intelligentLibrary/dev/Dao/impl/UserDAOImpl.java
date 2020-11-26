package com.intelligentLibrary.dev.Dao.impl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.intelligentLibrary.dev.Dao.SeatDAO;
import com.intelligentLibrary.dev.Dao.UserDAO;
import com.intelligentLibrary.dev.base.BaseDAOImpl;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.Occupyinfo;
import com.intelligentLibrary.dev.domain.User;

@Repository
public class UserDAOImpl extends BaseDAOImpl<User> implements UserDAO {

	@Resource
	SeatDAO seatDao;
	
	public User getUser(String username) {
		try {
			Query query = getSession().createQuery("from User s where s.username = :username");
			query.setString("username", username);
			User user = (User)query.uniqueResult();
			return user;
		} catch (Exception e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBookByBookName(String bookName) {
		 
		
		 return getSession().createQuery("from Book book where book.bookname like ?")
				 .setParameter(0, "%"+bookName+"%").list();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBookByIsbn(String isbn) {
		return getSession().createQuery("from Book book where book.isbn like ?")
				 .setParameter(0, isbn).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBookByauthor(String author) {
		return getSession().createQuery("from Book book where book.author like ?")
				 .setParameter(0, "%"+author+"%").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BorrowInfo> UserBorrowInfo(User user) {
		return getSession().createQuery("from BorrowInfo b where b.ilUser = ?").setParameter(0, user).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BorrowInfo> BookBorrowInfo(Book book) {
		return getSession().createQuery("from BorrowInfo b where b.ilBook = ?").setParameter(0, book).list();
	}

	@Override
	public Book getBook(String bookid) {
		try {
			Query query = getSession().createQuery("from Book b where b.bookid = :id");
			query.setString("id", bookid);
			Book book = (Book)query.uniqueResult();
			return book;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void addOccupiedinfo(int userid, int seatid) {
		
		Occupyinfo occupyinfo=new Occupyinfo();
		occupyinfo.setOrdertime(new Timestamp(System.currentTimeMillis()));
		occupyinfo.setUser(getById(userid));;
		occupyinfo.setSeat(seatDao.getSeatById(seatid));
		getSession().save(occupyinfo);
	}

	@Override
	public void deleteOccupiedinfo(Integer userid, Integer seatid) {
		Occupyinfo occupyinfo=(Occupyinfo) getSession().createQuery("from Occupyinfo where userid = ? and seatid = ?").setParameter(0, userid)
				.setParameter(1, seatid).uniqueResult();
		getSession().delete(occupyinfo);
	}

	@Override
	public List<Occupyinfo> getOccpyInfo(User user) {
		
		return getSession().createQuery("from Occupyinfo where user = ?").setParameter(0, user).list();
	}
	


}
