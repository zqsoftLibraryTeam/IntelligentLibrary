package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.BookShell;
import com.intelligentLibrary.dev.domain.BookSort;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface ManageBookService {
	/**
	 * 添加书籍
	 * @param book
	 */
	public boolean addBook(Book book);
	/**
	 * 获得所有书
	 * @return
	 */
	public List<Book> getBooks();
	/**
	 * 通过ID获取书本
	 * @return
	 */
	public Book getBookById(Integer id);
	/**
	 * 删除书籍通过id
	 * @param id
	 */
	public void deleteBook(Integer id);
	/**
	 * 寻找书通过id
	 * @param id
	 * @return
	 */
	public Book findBookByid(Integer id);
	/**
	 * 编辑书
	 * @param book
	 */
	public boolean editBook(Book book);
	/**
	 * 批量删除书本  通过书本id
	 * @param bookidlist 书本id集合
	 * @return
	 */
	public boolean deleteBooks(List<Integer> bookidlist);
	/**
	 * 查找书本类别通过名字
	 * @param Sortname
	 * @return
	 */
	public BookSort getBookSortBySortName(String Sortname);
	/**
	 * 通过书架的位置编号获得书架
	 * @param loc 位置编号
	 * @return
	 */
	public BookShell getBookShellByLoc(String loc);
	/**
	 * 分页获取图书
	 * @param p	分页对象
	 * @return
	 */
	public List<Book> getsubBook(SplitPage p);
	/**
	 * 计算需要显示多少分页按钮
	 * @param p
	 * @return
	 */
	public void gettotalPageNum(SplitPage p);
	/**
	 * 获取所有顶级分类
	 * @return 返回分类数组
	 */
	public List<BookSort> getAllTopSortName();
	/**
	 * 根据父分类id寻找所有子分类
	 * @param pid 父分类id
	 * @return 返回所有分类数组
	 */
	public List<BookSort> getSubSortByParId(Integer pid);
}
