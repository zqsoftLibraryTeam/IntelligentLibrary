package com.intelligentLibrary.dev.Dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.intelligentLibrary.dev.Dao.RecommandDAO;
import com.intelligentLibrary.dev.base.BaseDAOImpl;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.Recommandinfo;
import com.intelligentLibrary.dev.domain.User;
@Transactional
@Repository
public class RecommandDAOImpl extends BaseDAOImpl<Recommandinfo> implements RecommandDAO {

	@Override
	public List<Book> BooksToRecommand(User user) {
		return getSession().createQuery("from Recommandinfo where user = ?").setParameter(0, user).list();
	}

	@Override
	public double ConfidenceCount(String target, String condition) {
		BigInteger number=(BigInteger) getSession().createSQLQuery("SELECT count(*) FROM il_borrowinfo "
				+ "WHERE userid IN (SELECT DISTINCT(userid) FROM il_borrowinfo "
				+ "WHERE bookid = (SELECT bookid FROM il_book WHERE bookname = ?))"
				+ "AND bookid = (SELECT bookid FROM il_book WHERE bookname = ?);").setParameter(0, condition)
				.setParameter(1, target).uniqueResult();
		BigInteger mother=(BigInteger)getSession().createSQLQuery("select count(*) from il_borrowinfo where bookid = "
				+ "(select bookid from il_book where bookname = ?)").setParameter(0, condition).uniqueResult();
		if(mother.intValue()==0||number.intValue()==0)
		{
			return 0;
		}
		return (double)number.doubleValue()/mother.doubleValue();
	}

	@Override
	public int getRecommandInfoByuserandbook(User user, Book book) {
		BigInteger result=(BigInteger) getSession().createSQLQuery("select count(*) from il_recommand where userid ="
				+ "? and bookid =?").setParameter(0, user.getUserid()).setParameter(1, book.getBookid()).uniqueResult();
		return result.intValue();
 	}

}
