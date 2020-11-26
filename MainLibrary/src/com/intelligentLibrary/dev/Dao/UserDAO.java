package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.Occupyinfo;
import com.intelligentLibrary.dev.domain.User;

 public interface  UserDAO  extends BaseDAO<User>{
	 
	/**
	 * ��ѯ��ͨ������
	 * @param bookName
	 * @return
	 */
	List<Book> findBookByBookName(String bookname);
	/**
	 * Ѱ����ͨ�����ʱ�׼���
	 * @param isbn
	 * @return
	 */
	List<Book> findBookByIsbn(String isbn);
	/**
	 * ͨ�����߻�ȡ��Ϣ
	 * @param author
	 * @return
	 */
	List<Book> findBookByauthor(String author);
	/**
	 * ͨ����֪���Ǹ��û��������������
	 * @param user �û�
	 * @return �����û����ĵ������鼮
	 */
	public List<BorrowInfo> UserBorrowInfo(User user);
	/**
	 * ��ȡ�û�
	 * @return �����û�
	 */
	public User getUser(String username);
	/**
	 * ��ѯĳ��������н�����Ϣ
	 * @param bookid
	 * @return
	 */
	public List<BorrowInfo> BookBorrowInfo(Book book);
	/**
	 * ��ѯ�鱾ͨ��ID
	 * @param bookid
	 * @return
	 */
	public Book getBook(String bookid);
	
	/**
	 * ����ռ����Ϣ
	 * @param userid �û�id
	 * @param seatid ��λid
	 */
	public void addOccupiedinfo(int userid,int seatid);
	/**
	 * ɾ��ռ����Ϣ
	 * @param userid
	 * @param seatid
	 */
	void deleteOccupiedinfo(Integer userid, Integer seatid);
	
	List<Occupyinfo> getOccpyInfo(User user);
}
