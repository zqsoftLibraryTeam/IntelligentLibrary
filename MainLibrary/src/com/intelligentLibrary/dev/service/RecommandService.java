package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.User;

public interface RecommandService {
	String requesthandle(String input,int userid);

	void writeintorecommand(User user);
	
	
	/**
	 * 与controller相接直接调用数据库里的
	 * @param user
	 * @return
	 */
	List<Book> recommand(User user);
	
	
	
	/**
	 * 为Apriori算法提供数据
	 * @return
	 */
	List<BorrowInfo> AprioriArithmeticProvider();
	
	/**
	 * 为Apriori算法提供数据
	 * @return
	 */
	List<Book> AprioriArithmeticProvider_Books();
	
	/**
	 * 为Apriori算法提供数据
	 * @return
	 */
	List<User> AprioriArithmeticProvider_Users();
	
}
