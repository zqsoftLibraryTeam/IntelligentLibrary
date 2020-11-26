package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.Recommandinfo;
import com.intelligentLibrary.dev.domain.User;

public interface RecommandDAO extends BaseDAO<Recommandinfo>{
	/**
	 * 查询单个人的推荐图书信息
	 */
	List<Book> BooksToRecommand(User user);
	
	/**
	 * 计算置信度
	 * @param target
	 * @param condition
	 * @return
	 */
	double ConfidenceCount(String target,String condition);
	
	int getRecommandInfoByuserandbook(User user,Book book);
	
}
