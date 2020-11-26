package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.Recommandinfo;
import com.intelligentLibrary.dev.domain.User;

public interface RecommandDAO extends BaseDAO<Recommandinfo>{
	/**
	 * ��ѯ�����˵��Ƽ�ͼ����Ϣ
	 */
	List<Book> BooksToRecommand(User user);
	
	/**
	 * �������Ŷ�
	 * @param target
	 * @param condition
	 * @return
	 */
	double ConfidenceCount(String target,String condition);
	
	int getRecommandInfoByuserandbook(User user,Book book);
	
}
