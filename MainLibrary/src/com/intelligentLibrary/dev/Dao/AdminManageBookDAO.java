package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookShell;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface AdminManageBookDAO extends BaseDAO<Book>{
	/**
	 * 修改书本
	 * @param book
	 * @return
	 */
	public boolean alertBook(Book book);
	/**
	 * 添加书本
	 * @param book
	 * @return
	 */
	public boolean addBook(Book book);
	/**
	 * 通过ID查找书本
	 * @param id
	 * @return
	 */
	public Book getBookById(Integer id);
	/**
	 * 通过类别名搜索书本类型
	 * @param Sortname	书本类型
	 * @return 
	 */
	public BookSort getBookSortBySortName(String sortname);
	/**
	 * 通过书架的位置编号查找书架
	 * @param loc	书架编号
	 * @return
	 */
	public BookShell getBookShellByShellName(String loc);
	/**
	 * 获取书本的记录数
	 * @return
	 */
	public long getAllBookRecall();
	/**
	 * 分页查询书本
	 * @param p page对象
	 * @return
	 */
	public List<Book> getSubBooks(SplitPage p);
	
	/**
	 * 根据书名找书
	 * @param bookname
	 * @return
	 */
	public Book getBookByBookName(String bookname);

	/**
	 * 根据书本的id改变该书的状态
	 * @param id
	 */
	public void changeStatusByID(int id);
}
