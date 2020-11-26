package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.BookSort;

public interface BookSortDAO extends BaseDAO<BookSort>{

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
	
	public BookSort getBookSortById(int id);
}
