package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.Occupyinfo;
import com.intelligentLibrary.dev.domain.User;

 public interface  UserDAO  extends BaseDAO<User>{
	 
	/**
	 * 查询书通过名字
	 * @param bookName
	 * @return
	 */
	List<Book> findBookByBookName(String bookname);
	/**
	 * 寻找书通过国际标准书号
	 * @param isbn
	 * @return
	 */
	List<Book> findBookByIsbn(String isbn);
	/**
	 * 通过作者获取信息
	 * @param author
	 * @return
	 */
	List<Book> findBookByauthor(String author);
	/**
	 * 通过得知是那个用户来查找所借的书
	 * @param user 用户
	 * @return 返回用户借阅的所有书籍
	 */
	public List<BorrowInfo> UserBorrowInfo(User user);
	/**
	 * 获取用户
	 * @return 返回用户
	 */
	public User getUser(String username);
	/**
	 * 查询某本书的所有借阅信息
	 * @param bookid
	 * @return
	 */
	public List<BorrowInfo> BookBorrowInfo(Book book);
	/**
	 * 查询书本通过ID
	 * @param bookid
	 * @return
	 */
	public Book getBook(String bookid);
	
	/**
	 * 增加占座信息
	 * @param userid 用户id
	 * @param seatid 座位id
	 */
	public void addOccupiedinfo(int userid,int seatid);
	/**
	 * 删除占座信息
	 * @param userid
	 * @param seatid
	 */
	void deleteOccupiedinfo(Integer userid, Integer seatid);
	
	List<Occupyinfo> getOccpyInfo(User user);
}
