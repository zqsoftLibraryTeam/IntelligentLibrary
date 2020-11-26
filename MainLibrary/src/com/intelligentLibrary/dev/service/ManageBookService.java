package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookShell;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface ManageBookService {
	/**
	 * ����鼮
	 * @param book
	 */
	public boolean addBook(Book book);
	/**
	 * ���������
	 * @return
	 */
	public List<Book> getBooks();
	/**
	 * ͨ��ID��ȡ�鱾
	 * @return
	 */
	public Book getBookById(Integer id);
	/**
	 * ɾ���鼮ͨ��id
	 * @param id
	 */
	public void deleteBook(Integer id);
	/**
	 * Ѱ����ͨ��id
	 * @param id
	 * @return
	 */
	public Book findBookByid(Integer id);
	/**
	 * �༭��
	 * @param book
	 */
	public boolean editBook(Book book);
	/**
	 * ����ɾ���鱾  ͨ���鱾id
	 * @param bookidlist �鱾id����
	 * @return
	 */
	public boolean deleteBooks(List<Integer> bookidlist);
	/**
	 * �����鱾���ͨ������
	 * @param Sortname
	 * @return
	 */
	public BookSort getBookSortBySortName(String Sortname);
	/**
	 * ͨ����ܵ�λ�ñ�Ż�����
	 * @param loc λ�ñ��
	 * @return
	 */
	public BookShell getBookShellByLoc(String loc);
	/**
	 * ��ҳ��ȡͼ��
	 * @param p	��ҳ����
	 * @return
	 */
	public List<Book> getsubBook(SplitPage p);
	/**
	 * ������Ҫ��ʾ���ٷ�ҳ��ť
	 * @param p
	 * @return
	 */
	public void gettotalPageNum(SplitPage p);
	/**
	 * ��ȡ���ж�������
	 * @return ���ط�������
	 */
	public List<BookSort> getAllTopSortName();
	/**
	 * ���ݸ�����idѰ�������ӷ���
	 * @param pid ������id
	 * @return �������з�������
	 */
	public List<BookSort> getSubSortByParId(Integer pid);
}
