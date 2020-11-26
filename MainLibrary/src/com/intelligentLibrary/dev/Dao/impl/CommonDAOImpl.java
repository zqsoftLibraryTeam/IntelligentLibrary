package com.intelligentLibrary.dev.Dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.intelligentLibrary.dev.Dao.CommonDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookBorrowNum;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@Resource
	private SessionFactory sessionFactory;
	
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	public void closeSession() {
		getSession().close();
	}
	
	@Override
	public long getAllBookRecall() {
		Query query = getSession().createQuery("select count(*) from Book");
		return (long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getSubBooks(int num) {
		Query query = getSession().createQuery("from Book");
		query.setFirstResult(num-6);
		query.setMaxResults(6);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<BookBorrowNum> getAllBookBorrowNumList(){
		return getSession().createSQLQuery("SELECT bookid,count(bookid)as num from il_borrowinfo GROUP BY bookid ORDER BY num DESC").addEntity(BookBorrowNum.class).list();
	}
	@Override
	public Book getBookById(Integer id) {
		try {
			Query query = getSession().createQuery("from Book b where b.bookid = :id");
			query.setLong("id", id);
			Book book = (Book)query.uniqueResult();
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
