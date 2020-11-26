package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface ManageUserService {
	/**
	 * 添加用户
	 * @param user
	 */
	boolean addUser(User user);
	/**
	 * 获取所有用户
	 * @return
	 */
	List<User> getUsers();
	/**
	 * 获得用户通过id
	 * @param userid
	 * @return
	 */
	User getUserById(Integer userid);
	/**
	 * 删除用户
	 * @param id
	 */
	void deleteUser(Integer id);
	/**
	 * 得到所有用户
	 * @param id
	 * @return
	 */
	User findUserByid(Integer id);
	/**
	 * 编辑用户
	 * @param user
	 */
	boolean editUser(User user);
	/**
	 * 批量删除用户
	 * @param useridlist 用户id集合
	 * @return
	 */
	boolean deleteUsers(List<Integer> useridlist);
	/**
	 * 分页获取图书
	 * @param p
	 * @return
	 */
	public List<User> getsubBook(SplitPage p);
	/**
	 * 计算需要多少页
	 * @param p
	 */
	public void gettotalPageNum(SplitPage p);
	
}
