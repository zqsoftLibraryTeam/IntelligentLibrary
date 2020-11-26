package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface AdminManageUserDAO extends BaseDAO<User>{
	/**
	 * 删除单个用户
	 */
	public boolean deleteUser(String username);
	
	/**
	 * 添加用户
	 */
	public boolean addUser(User user);
	/**
	 * 修改用户
	 * @return
	 */
	public boolean alterUser(User user);
	/**
	 * 查找用户
	 * @return
	 */
	public List<User> queryUser();
	/**
	 * 查找用户通过id
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id);
	/**
	 * 获取用户总记录
	 * @return
	 */
	public long getAllUserRecall();
	/**
	 * 分页查询用户对象
	 * @param p
	 * @return
	 */
	public List<User> getSubUsers(SplitPage p);

	/**
	 * 根据用户名查找用户
	 * @param username
	 * @return
	 */
	public User getUserByUserName(String username);
}
