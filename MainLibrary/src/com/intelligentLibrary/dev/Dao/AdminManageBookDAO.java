package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookShell;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface AdminManageBookDAO extends BaseDAO<Book>{
	/**
	 * �޸��鱾
	 * @param book
	 * @return
	 */
	public boolean alertBook(Book book);
	/**
	 * ����鱾
	 * @param book
	 * @return
	 */
	public boolean addBook(Book book);
	/**
	 * ͨ��ID�����鱾
	 * @param id
	 * @return
	 */
	public Book getBookById(Integer id);
	/**
	 * ͨ������������鱾����
	 * @param Sortname	�鱾����
	 * @return 
	 */
	public BookSort getBookSortBySortName(String sortname);
	/**
	 * ͨ����ܵ�λ�ñ�Ų������
	 * @param loc	��ܱ��
	 * @return
	 */
	public BookShell getBookShellByShellName(String loc);
	/**
	 * ��ȡ�鱾�ļ�¼��
	 * @return
	 */
	public long getAllBookRecall();
	/**
	 * ��ҳ��ѯ�鱾
	 * @param p page����
	 * @return
	 */
	public List<Book> getSubBooks(SplitPage p);
	
	/**
	 * ������������
	 * @param bookname
	 * @return
	 */
	public Book getBookByBookName(String bookname);

	/**
	 * �����鱾��id�ı�����״̬
	 * @param id
	 */
	public void changeStatusByID(int id);
}
