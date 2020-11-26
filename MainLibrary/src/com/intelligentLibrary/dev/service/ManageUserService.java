package com.intelligentLibrary.dev.service;

import java.util.List;

import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.utils.SplitPage;

public interface ManageUserService {
	/**
	 * ����û�
	 * @param user
	 */
	boolean addUser(User user);
	/**
	 * ��ȡ�����û�
	 * @return
	 */
	List<User> getUsers();
	/**
	 * ����û�ͨ��id
	 * @param userid
	 * @return
	 */
	User getUserById(Integer userid);
	/**
	 * ɾ���û�
	 * @param id
	 */
	void deleteUser(Integer id);
	/**
	 * �õ������û�
	 * @param id
	 * @return
	 */
	User findUserByid(Integer id);
	/**
	 * �༭�û�
	 * @param user
	 */
	boolean editUser(User user);
	/**
	 * ����ɾ���û�
	 * @param useridlist �û�id����
	 * @return
	 */
	boolean deleteUsers(List<Integer> useridlist);
	/**
	 * ��ҳ��ȡͼ��
	 * @param p
	 * @return
	 */
	public List<User> getsubBook(SplitPage p);
	/**
	 * ������Ҫ����ҳ
	 * @param p
	 */
	public void gettotalPageNum(SplitPage p);
	
}
