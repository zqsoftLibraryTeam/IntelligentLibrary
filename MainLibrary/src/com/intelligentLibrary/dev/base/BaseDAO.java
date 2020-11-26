package com.intelligentLibrary.dev.base;

import java.util.List;

public interface  BaseDAO<T> {
	/**
	 * 保存实体
	 * @param entity
	 */
	void save(T entity);
	/**
	 * 通过id删除实体
	 * @param id
	 */
	void delete(int id);
	/**
	 * 更新实体
	 * @param entity
	 */
	void update(T entity);
	/**
	 * 通过id查询实体
	 * @param id
	 * @return
	 */
	T getById(int id);
	/**
	 * 查询实体//暂时不知道怎么用
	 * @param roleIds
	 * @return
	 */
	List<T> getByIds(Integer[] roleIds);
	
	/**
	 *查询所有实体
	 * @return
	 */
	List<T> findAll();
}
