package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BorrowInfo;
import com.intelligentLibrary.dev.domain.User;

public interface UserService {
	/**
	 * ��ѯͼ�� 
	 * @param bywhat ͨ��ʲô��ѯ
	 * @param thevalue ֵ
	 * @return ����һ����ļ���
	 */
	List<Book> findBookBywhat(String bywhat,String thevalue);
	
	/**
	 * �����鱾
	 * @param id �û�id
	 * @param bookid �鱾id
	 * @return �����Ƿ�ɹ�
	 */
	boolean borrowBook(Integer id, String bookid);
	/**
	 * ��ѯ����ʲô��
	 * @param userid �û�
	 * @return �����û����ĵ��鼮����
	 */
	public List<BorrowInfo> borrowInfo(User user);
	/**
	 * �û��޸�����
	 * @param oldpass ������
	 * @param newpass ������
	 * @return �޸������Ƿ�ɹ�
	 */
	public boolean changepassword(String oldpass,String newpass,User user);
	/**
	 * ��ѯ�Ȿ�鱻��Щ�˽�
	 * @param book
	 * @return �鱾���н�����Ϣ
	 */
	public List<BorrowInfo> BookborrowInfo(String bookid);
	/**
	 * ͨ��id��ȡbook
	 * @param bookid
	 * @return
	 */
	public Book getBook(String bookid);
	/**
	 * �����鱾
	 * @param uid
	 * @param bid
	 * @return
	 */
	public boolean orderBook(int uid,String bid);
	/**
	 * ɾ��������Ϣ
	 * @param oid
	 * @return
	 */
	public boolean cancelorderbook(int oid);
	
}
