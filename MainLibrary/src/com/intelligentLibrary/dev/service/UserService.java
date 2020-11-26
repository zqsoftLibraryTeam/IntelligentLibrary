package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.User;

public interface UserService {
	/**
	 * 查询图书 
	 * @param bywhat 通过什么查询
	 * @param thevalue 值
	 * @return 返回一个书的集合
	 */
	List<Book> findBookBywhat(String bywhat,String thevalue);
	
	/**
	 * 借阅书本
	 * @param id 用户id
	 * @param bookid 书本id
	 * @return 借书是否成功
	 */
	boolean borrowBook(Integer id, String bookid);
	/**
	 * 查询借了什么书
	 * @param userid 用户
	 * @return 返回用户借阅的书籍集合
	 */
	public List<BorrowInfo> borrowInfo(User user);
	/**
	 * 用户修改密码
	 * @param oldpass 旧密码
	 * @param newpass 新密码
	 * @return 修改密码是否成功
	 */
	public boolean changepassword(String oldpass,String newpass,User user);
	/**
	 * 查询这本书被哪些人借
	 * @param book
	 * @return 书本所有借阅信息
	 */
	public List<BorrowInfo> BookborrowInfo(String bookid);
	/**
	 * 通过id获取book
	 * @param bookid
	 * @return
	 */
	public Book getBook(String bookid);
	/**
	 * 借阅书本
	 * @param uid
	 * @param bid
	 * @return
	 */
	public boolean orderBook(int uid,String bid);
	/**
	 * 删除订阅信息
	 * @param oid
	 * @return
	 */
	public boolean cancelorderbook(int oid);
	
}
