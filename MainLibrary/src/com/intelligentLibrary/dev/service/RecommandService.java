package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.User;

public interface RecommandService {
	String requesthandle(String input,int userid);

	void writeintorecommand(User user);
	
	
	/**
	 * ��controller���ֱ�ӵ������ݿ����
	 * @param user
	 * @return
	 */
	List<Book> recommand(User user);
	
	
	
	/**
	 * ΪApriori�㷨�ṩ����
	 * @return
	 */
	List<BorrowInfo> AprioriArithmeticProvider();
	
	/**
	 * ΪApriori�㷨�ṩ����
	 * @return
	 */
	List<Book> AprioriArithmeticProvider_Books();
	
	/**
	 * ΪApriori�㷨�ṩ����
	 * @return
	 */
	List<User> AprioriArithmeticProvider_Users();
	
}
