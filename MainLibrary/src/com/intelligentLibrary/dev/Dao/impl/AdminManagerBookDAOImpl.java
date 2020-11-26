package com.intelligentLibrary.dev.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.intelligentLibrary.dev.Dao.AdminManageBookDAO;
import com.intelligentLibrary.dev.base.BaseDAOImpl;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookShell;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.utils.SplitPage;

@Repository
public class AdminManagerBookDAOImpl extends BaseDAOImpl<Book> implements AdminManageBookDAO {


	@Override
	public boolean alertBook(Book book) {
		try {
			Query query = getSession().createQuery("update Book b set b.bookname=?,b.isbn=?,b.author=?,b.press=?,b.picture=?,b.status=?,b.booknum=? where b.bookid=?");
			query.setParameter(0, book.getBookname());
			query.setParameter(1, book.getIsbn());
			query.setParameter(2, book.getAuthor());
			query.setParameter(3, book.getPress());
			query.setParameter(4, book.getPicture());
			query.setParameter(5, book.getStatus());
			query.setParameter(6, book.getBooknum());
			query.setParameter(7, book.getBookid());
			query.executeUpdate(); 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addBook(Book book) {
		try {
			getSession().save(book);
			return true;
		} catch (Exception e) {
			System.out.println("ÃÌº” È±æ ß∞‹");
			e.printStackTrace();
			return false;
		}
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

	@Override
	public BookSort getBookSortBySortName(String sortname) {
		try {
			Query query = getSession().createQuery("from BookSort bs where bs.sortname = :sortname");
			query.setString("sortname", sortname);
			BookSort booksort = (BookSort)query.uniqueResult();
			return booksort;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public BookShell getBookShellByShellName(String loc) {
		try {
			Query query = getSession().createQuery("from BookShell bs where bs.loc = :loc");
			query.setString("loc", loc);
			BookShell bookshell = (BookShell)query.uniqueResult();
			return bookshell;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public long getAllBookRecall() {
		Query query = getSession().createQuery("select count(*) from Book");
		return (long) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getSubBooks(SplitPage p) {
		Query query = getSession().createQuery("from Book");
		query.setFirstResult(p.getStarindex());
		query.setMaxResults(p.getObjnum());
		return query.list();
	}

	@Override
	public Book getBookByBookName(String bookname) {
		Book book=(Book) getSession().createQuery("from Book where bookname = ?").setParameter(0, bookname).uniqueResult();
		return book;
	}

	@Override
	public void changeStatusByID(int id) {
		Book book=getById(id);
		if("1".equals(book.getStatus()))
		{
			book.setStatus("0");
			
		}
		else
		{
			book.setStatus("1");
		}
		getSession().update(book);
	}


}
