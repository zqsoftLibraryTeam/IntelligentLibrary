package com.intelligentLibrary.dev.Dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.intelligentLibrary.dev.Dao.BookSortDAO;
import com.intelligentLibrary.dev.base.BaseDAOImpl;
import com.intelligentLibrary.dev.domain.BookSort;

@Repository
public class BookSortDAOImpl extends BaseDAOImpl<BookSort> implements BookSortDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<BookSort> getAllTopSortName() {
		Query query = getSession().createQuery("from BookSort where parentid IS NULL");
		return query.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BookSort> getSubSortByParId(Integer pid) {
		Query query = getSession().createQuery("from BookSort where parentid = ?");
		query.setParameter(0, pid);
		return query.list();
	}

	@Override
	public BookSort getBookSortById(int id) {
		Query query = getSession().createQuery("select * from BookSort where sortid = ?");
		query.setParameter(0, id);
		return (BookSort) query.uniqueResult();
	}

}
