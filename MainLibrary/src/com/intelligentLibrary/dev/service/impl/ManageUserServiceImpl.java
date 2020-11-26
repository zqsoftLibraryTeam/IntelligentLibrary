package com.intelligentLibrary.dev.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intelligentLibrary.dev.Dao.AdminManageUserDAO;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.ManageUserService;
import com.intelligentLibrary.dev.utils.SplitPage;

@Service
public class ManageUserServiceImpl implements ManageUserService{

	@Resource
	AdminManageUserDAO adminManageUserDAO;
	
	/**
	 * ����û�
	 */
	@Override
	public boolean addUser(User user) {
		return adminManageUserDAO.addUser(user);
		
	}

	/**
	 * ��ȡ���е��û�
	 */
	@Override
	public List<User> getUsers() {
		return adminManageUserDAO.findAll();
		
	}

	/**
	 * ɾ��ѡ���û� ͨ��id
	 */
	@Override
	public void deleteUser(Integer id) {
		adminManageUserDAO.delete(id);
	}

	/**
	 * ͨ���û���id��ȡ��ǰ�û�
	 */
	@Override
	public User findUserByid(Integer id) {
		return adminManageUserDAO.getById(id);
	}
	
	/**
	 * �޸��û���Ϣ
	 */
	@Override
	public boolean editUser(User user) {
		return adminManageUserDAO.alterUser(user);
		
	}

	@Override
	public User getUserById(Integer userid) {
		return adminManageUserDAO.getUserById(userid);
	}

	@Override
	public boolean deleteUsers(List<Integer> useridlist) {
		try {
			for(int i=0;i<useridlist.size();i++){
				deleteUser((Integer)useridlist.get(i));
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> getsubBook(SplitPage p) {
		p.setTotalnum((adminManageUserDAO.getAllUserRecall()/(double)p.getObjnum()));
		return adminManageUserDAO.getSubUsers(p);
	}

	@Override
	public void gettotalPageNum(SplitPage p) {
		p.setTotalnum((adminManageUserDAO.getAllUserRecall()/(double)p.getObjnum()));
	}
	

	
}
