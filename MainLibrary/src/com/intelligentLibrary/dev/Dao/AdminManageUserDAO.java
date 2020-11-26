package com.intelligentLibrary.dev.Dao;

import java.util.List;

import com.intelligentLibrary.dev.base.BaseDAO;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface AdminManageUserDAO extends BaseDAO<User>{
	/**
	 * ɾ�������û�
	 */
	public boolean deleteUser(String username);
	
	/**
	 * ����û�
	 */
	public boolean addUser(User user);
	/**
	 * �޸��û�
	 * @return
	 */
	public boolean alterUser(User user);
	/**
	 * �����û�
	 * @return
	 */
	public List<User> queryUser();
	/**
	 * �����û�ͨ��id
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id);
	/**
	 * ��ȡ�û��ܼ�¼
	 * @return
	 */
	public long getAllUserRecall();
	/**
	 * ��ҳ��ѯ�û�����
	 * @param p
	 * @return
	 */
	public List<User> getSubUsers(SplitPage p);

	/**
	 * �����û��������û�
	 * @param username
	 * @return
	 */
	public User getUserByUserName(String username);
}
